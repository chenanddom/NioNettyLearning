package com.itdom.file;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedFile;
import io.netty.util.CharsetUtil;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

import static io.netty.handler.codec.http.HttpHeaders.Names.*;

/**
 * 该Hnadler用于文件服务器的业务逻辑的处理
 */
public class HttpFileServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    private final String url;
    private static final Pattern INSECURE_URI = Pattern.compile(".*[<>&\"].*");

    private static final Pattern ALLOWED_FILE_NAME = Pattern.compile("[A-Za-z0-9][-_A-Za-z0-9\\.]*");


    public HttpFileServerHandler(String url) {
        this.url = url;
    }

    protected void messageReceived(final ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        /**
         * 解码失败就跳转到400错误
         */
        if (!fullHttpRequest.getDecoderResult().isSuccess()) {
            /**
             * 返回400错误
             */
            sendError(channelHandlerContext, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        /**
         * 对请求方法进行解析,如果不是GET请求跳转到405
         */
        if (fullHttpRequest.getMethod() != HttpMethod.GET) {
            sendError(channelHandlerContext, HttpResponseStatus.METHOD_NOT_ALLOWED);
            return;
        }
        /**
         * 对请求URI进行解析
         */
        String uri = fullHttpRequest.getUri();
        String path = sanitizeUri(uri);
        if (path == null) {
            sendError(channelHandlerContext, HttpResponseStatus.FORBIDDEN);
        }
        /**
         * 构建文件URI的合法性校验
         */
        File file = new File(path);
        /**
         * 文件是隐藏文件或者文件不存在就返回404
         */
        if (file.isHidden() || !file.exists()) {
            sendError(channelHandlerContext, HttpResponseStatus.NOT_FOUND);
            return;
        }
        /**
         * 文件是目录，发送目录的链接给客户端.
         */
        if (file.isDirectory()) {
            if (uri.endsWith("/")) {
                sendListing(channelHandlerContext, file);
            } else {
                sendRedirect(channelHandlerContext, uri + '/');
            }
            return;
        }

        if (!file.isFile()) {
            sendError(channelHandlerContext, HttpResponseStatus.FORBIDDEN);
            return;
        }
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
        } catch (FileNotFoundException e) {
            sendError(channelHandlerContext, HttpResponseStatus.NOT_FOUND);
            return;
        }
        long fileLength = randomAccessFile.length();
        /**
         * 创建成功的HTTP响应消息，消息投类型伟"text/html;charset=UTF-8"
         */
        HttpResponse response = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        HttpHeaders.setContentLength(response, fileLength);
        setContentTypeHeader(response, file);
        /**
         * 判断是否是Keep-Alive,如果是就在消息头中设置Connection设置伟Keep-Alive
         */
        if (HttpHeaders.isKeepAlive(fullHttpRequest)) {
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        }
        channelHandlerContext.write(response);
        ChannelFuture sendFileFuture;
        /**
         * 将Netty的ChunkedFile对象直接写入到缓冲区中。最后
         */
        sendFileFuture = channelHandlerContext.write(new ChunkedFile(randomAccessFile, 0, fileLength, 8192), channelHandlerContext.newProgressivePromise());
        /**
         * 获取发送响应结果的进度
         */
       sendFileFuture.addListener(new ChannelProgressiveFutureListener() {
           public void operationComplete(ChannelProgressiveFuture channelProgressiveFuture) throws Exception {
               System.err.println(channelProgressiveFuture.channel() + " transfer complete");
           }

           public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long l, long l1) throws Exception {
               if (l1 < 0) {
                   System.err.println(channelProgressiveFuture.channel() + " transfer progress : " + l);
               } else {
                   System.err.println(channelProgressiveFuture.channel() + " transfer progress : " + l + " / " + l1);
               }
           }
       });
        /**
         * 发送一个编码结束的空消息体，
         */
        ChannelFuture lastContentFuture = channelHandlerContext.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT);
        /**
         * 如果不是保持连接的方式，在传输完成的最后将连接关闭
         */
        if (!HttpHeaders.isKeepAlive(fullHttpRequest)) {
            lastContentFuture.addListener(ChannelFutureListener.CLOSE);
        }

    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()) {
            sendError(ctx, HttpResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private String sanitizeUri(String uri) {
        try {
            /**
             * 使用JDK的java.net.URLDecoder对URL进行解码,符合要求的路径方可以访问
             */
            uri = URLDecoder.decode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            try {
                uri = URLDecoder.decode(uri, "ISO-8859-1");
            } catch (UnsupportedEncodingException e1) {
                throw new Error();
            }
        }

        //各种校验
        if (!uri.startsWith(url)) {
            return null;
        }
        if (!uri.startsWith("/")) {
            return null;
        }
        /**
         * 将请求路径的分隔符转换成为操作系统对应的分隔符
         */
        uri = uri.replace('/', File.separatorChar);
        /**
         * 对URI做二次校验
         */
        if (uri.contains(File.separator + '.') || uri.contains('.' + File.separator) || uri.startsWith(".") || uri.endsWith(".") || INSECURE_URI.matcher(uri).matches()) {
            return null;
        }
        /**
         * 当前工厂的目录+请求URI构建绝对路径
         */
        return System.getProperty("user.dir") + uri.replace("/",File.separator);
    }



    private static void sendListing(ChannelHandlerContext ctx, File dir) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        response.headers().set(CONTENT_TYPE, "text/html;charset=UTF-8");

        String dirPath = dir.getPath();
        /**
         * 构建消息HTTP响应体
         */
        //拼装响应体的信息
        StringBuilder builder = new StringBuilder();

        builder.append("<!DOCTYPE html>\r\n");
        builder.append("<html><link rel=\"shortcut icon\" href=\"http://192.168.10.226:8088/netty-http/src/main/java/com/itdom/favicon.ico\" type=\"image/x-icon\" /><head><title>");
        builder.append(dirPath);
        builder.append("目录:");
        builder.append("</title></head><body>\r\n");

        builder.append("<h3>");
        builder.append(dirPath).append(" 目录：");
        builder.append("</h3>\r\n");
        builder.append("<ul>");
        builder.append("<li>链接：<a href=\"../\">..</a></li>\r\n");
        for (File f : dir.listFiles()) {
            if (f.isHidden() || !f.canRead()) {
                continue;
            }
            String name = f.getName();
            if (!ALLOWED_FILE_NAME.matcher(name).matches()) {
                continue;
            }

            builder.append("<li>链接：<a href=\"");
            builder.append(name);
            builder.append("\">");
            builder.append(name);
            builder.append("</a></li>\r\n");
        }
        builder.append("</ul></body></html>\r\n");
        /**
         * 将消息放到缓冲区中，最后将响应消息存放到HTTP应答消息中,然后释放缓冲区,
         */
        ByteBuf buffer = Unpooled.copiedBuffer(builder, CharsetUtil.UTF_8);
        response.content().writeBytes(buffer);
        buffer.release();
        /**
         * 将下响应消息发送到缓冲区并刷新到SocketChannel中.在传输完成的最后将连接关闭
         */
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);

    }

    private static void sendRedirect(ChannelHandlerContext ctx, String newUri) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND);
        response.headers().set(LOCATION, newUri);
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 返回错误响应
      * @param ctx
     * @param status
     */
    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status,
                Unpooled.copiedBuffer("Failure: " + status.toString(),
                        CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /**
     * 设置内容头
     * @param response
     * @param file
     */
    private static void setContentTypeHeader(HttpResponse response, File file) {
        MimetypesFileTypeMap mimeTypeMap = new MimetypesFileTypeMap();
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, mimeTypeMap.getContentType(file.getPath()));
    }

}
