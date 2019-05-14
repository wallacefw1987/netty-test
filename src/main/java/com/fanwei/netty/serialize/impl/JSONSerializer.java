package com.fanwei.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.fanwei.netty.serialize.Serializer;
import com.fanwei.netty.serialize.SerializerAlogrithm;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 10:44
 **/
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlogrithm() {
        return SerializerAlogrithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes,clazz);
    }
}
