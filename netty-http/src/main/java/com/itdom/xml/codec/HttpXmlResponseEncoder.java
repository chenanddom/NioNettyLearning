package com.itdom.xml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;

public class HttpXmlResponseEncoder extends AbstractHttpXmlEncoder<HttpXmlResponse> {


    protected void encode(ChannelHandlerContext channelHandlerContext, HttpXmlResponse httpXmlResponse, List<Object> list) throws Exception {
        ByteBuf byteBuf = encode0(channelHandlerContext, httpXmlResponse.getHttpResponse());
        FullHttpResponse httpResponse = httpXmlResponse.getHttpResponse();
        if (httpResponse==null){
            httpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,byteBuf);
        }else {
            httpResponse = new DefaultFullHttpResponse(httpXmlResponse.getHttpResponse().getProtocolVersion(),httpXmlResponse.getHttpResponse().getStatus(),byteBuf);
        }
        httpResponse.headers().set(CONTENT_TYPE,"text/xml");
        setContentLength(httpResponse,byteBuf.readableBytes());
        list.add(httpResponse);
    }
}
