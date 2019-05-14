package com.fanwei.netty.utils;

import com.fanwei.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-12 21:16
 **/
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> result = channel.attr(Attributes.LOGIN);
        return result.get()!= null;
    }
}
