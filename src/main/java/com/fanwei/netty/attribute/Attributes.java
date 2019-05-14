package com.fanwei.netty.attribute;

import io.netty.util.AttributeKey;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-12 21:13
 **/
public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
}
