package com.example.vote.utils;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @ClassName: UuidUtil
 * @Description: Uuid工具类
 * @Date : 2020-07-18 23:37
 * @Author: ZhangLei
 * Version: 1.0
 **/
public class UuidUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String checkEmptyAndGetUUID(String uuid) {
        if (StringUtils.isEmpty(uuid)) {
            return getUUID();
        }
        return uuid;
    }
}
