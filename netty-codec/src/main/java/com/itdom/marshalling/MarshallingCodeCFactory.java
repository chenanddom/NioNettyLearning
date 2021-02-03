package com.itdom.marshalling;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeCFactory {
    /**
     * 创建Jboss Marshalling 解码器MarshallingDecoder
     * @return
     */
    public static MarshallingDecoder buildMarshallingDecoder(){
        final MarshallerFactory factory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        DefaultUnmarshallerProvider defaultUnmarshallerProvider = new DefaultUnmarshallerProvider(factory, configuration);
        MarshallingDecoder marshallingDecoder = new MarshallingDecoder(defaultUnmarshallerProvider);
        return marshallingDecoder;
    }

    /**
     * 创建Jboss Marshalling 编码器MarshallingEncoder
     * @return
     */
    public static MarshallingEncoder buildMarshallingEncoder(){
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);
        DefaultMarshallerProvider defaultMarshallerProvider = new DefaultMarshallerProvider(marshallerFactory, configuration);
        MarshallingEncoder marshallingEncoder = new MarshallingEncoder(defaultMarshallerProvider);
        return marshallingEncoder;
    }



}
