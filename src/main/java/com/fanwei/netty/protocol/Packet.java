package com.fanwei.netty.protocol;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @program: netty-test
 * @description
 * @author: fanwei
 * @create: 2019-05-10 10:00
 **/
@Data
public abstract class Packet {
    /**
     * 协议版本
     */
    @JSONField(deserialize = false,serialize = false)
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
