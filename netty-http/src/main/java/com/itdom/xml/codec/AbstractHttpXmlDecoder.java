package com.itdom.xml.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import java.io.StringReader;
import java.nio.charset.Charset;

public abstract class AbstractHttpXmlDecoder<T,O> extends MessageToMessageCodec<T,O> {

    private final static String CHARSET_NAME = "UTF-8";

    private final static Charset UTF_8 = Charset.forName(CHARSET_NAME);

    private IBindingFactory factory;


    private StringReader reader;

    private Class<?> clazz;

    private boolean isPrint;

    protected AbstractHttpXmlDecoder(Class<?> clazz) {
        this(clazz, false);
    }

    protected AbstractHttpXmlDecoder(Class<?> clazz, boolean isPrint) {
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    protected Object decode0(ChannelHandlerContext arg0, ByteBuf body) throws JiBXException {
        factory = BindingDirectory.getFactory(clazz);
        String content = body.toString(UTF_8);
        if (isPrint) {
            System.out.println("The body is :" + content);
        }
        reader = new StringReader(content);
        IUnmarshallingContext uctx = factory.createUnmarshallingContext();
        Object result = uctx.unmarshalDocument(reader);
        reader.close();
        reader = null;
        return result;
    }

    @Skip
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (reader != null) {
            reader.close();
            reader = null;
        }
    }
}
