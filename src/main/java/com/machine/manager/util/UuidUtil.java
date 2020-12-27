package com.machine.manager.util;

import java.util.UUID;

public class UuidUtil {
    /**
     * 获得一个UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
}
