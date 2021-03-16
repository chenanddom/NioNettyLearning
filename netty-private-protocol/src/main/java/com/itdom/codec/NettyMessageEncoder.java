package com.itdom.codec;

import com.itdom.struct.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.Map;

public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    private MarshallingEncoder marshallingEncoder;


    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }


    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, NettyMessage nettyMessage, ByteBuf byteBuf) throws Exception {
        if (nettyMessage == null || nettyMessage.getHeader() == null) {
            throw new Exception("The encoder message is null");
        }
        byteBuf.writeInt(nettyMessage.getHeader().getCrcCode());
        byteBuf.writeInt(nettyMessage.getHeader().getLength());
        byteBuf.writeLong(nettyMessage.getHeader().getSessionID());
        byteBuf.writeByte(nettyMessage.getHeader().getType());
        byteBuf.writeByte(nettyMessage.getHeader().getPriority());
        byteBuf.writeInt(nettyMessage.getHeader().getAttachment().size());
        String key = null;
        byte[] keyArray = null;
        Object value = null;
        for (Map.Entry<String, Object> param : nettyMessage.getHeader().getAttachment().entrySet()) {
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            byteBuf.writeInt(keyArray.length);
            byteBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value, byteBuf);
        }
        key = null;
        keyArray = null;
        value = null;
        if (nettyMessage.getBody() != null) {
            marshallingEncoder.encode(nettyMessage.getBody(), byteBuf);
        } else {
            byteBuf.writeInt(0);
            byteBuf.setInt(4, byteBuf.readableBytes() - 8);
        }
    }
}
