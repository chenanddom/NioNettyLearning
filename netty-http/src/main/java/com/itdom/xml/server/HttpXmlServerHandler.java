package com.itdom.xml.server;

import com.itdom.xml.codec.HttpXmlRequest;
import com.itdom.xml.codec.HttpXmlResponse;
import com.itdom.xml.pojo.Address;
import com.itdom.xml.pojo.Customer;
import com.itdom.xml.pojo.Order;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.List;

import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpXmlServerHandler extends SimpleChannelInboundHandler<HttpXmlRequest> {
    protected void messageReceived(ChannelHandlerContext channelHandlerContext, HttpXmlRequest httpXmlRequest) throws Exception {
        FullHttpRequest request = httpXmlRequest.getRequest();
        Order order = (Order)httpXmlRequest.getBody();
        System.out.println("Http server receiver request: "+order);
        dobusiness(order);
        ChannelFuture future = channelHandlerContext.writeAndFlush(new HttpXmlResponse(null, order));
        if (!isKeepAlive(request));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        if (ctx.channel().isActive()){
            sendError(ctx, INTERNAL_SERVER_ERROR);
        }
    }

    private void dobusiness(Order order){
        Customer customer = order.getCustomer();
        customer.setFirstName("张");
        customer.setLastName("三丰");
        List<String> midNames = new ArrayList<String>();
        midNames.add("李元芳");
        customer.setMiddleNames(midNames);
        Address address = order.getBillTo();
        address.setCity("深圳");
        address.setCountry("中国");
        address.setState("广东");
        address.setPostCode("518000");
        order.setBillTo(address);
        order.setShipTo(address);
    }

    private static void sendError(ChannelHandlerContext ctx, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, status, Unpooled.copiedBuffer("失败: " + status.toString() + "\r\n", CharsetUtil.UTF_8));
        response.headers().set(CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
