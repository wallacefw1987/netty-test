package com.fanwei.netty.serialize;

import com.fanwei.netty.serialize.impl.JSONSerializer;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 10:42
 **/
public interface Serializer {

    /**
     * json 序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULTA =  new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java 对象转换成二进制
     */
    byte[] serialize(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
