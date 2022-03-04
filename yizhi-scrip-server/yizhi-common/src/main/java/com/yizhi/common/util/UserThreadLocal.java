package com.yizhi.common.util;

import com.yizhi.common.model.pojo.mysql.ApUser;

public class UserThreadLocal {

    private static final ThreadLocal<ApUser> LOCAL = new ThreadLocal<>();

    private UserThreadLocal() {
    }

    public static void set(ApUser user) {
        LOCAL.set(user);
    }

    public static ApUser get() {
        return LOCAL.get();
    }
}
