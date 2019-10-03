package com.tw.sell.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式:时间＋随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random r = new Random();
        Integer number = r.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
