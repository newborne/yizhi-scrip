package com.yizhi.common.util;

import com.yizhi.common.model.pojo.mysql.ApUser;

/**
 * The type User thread local.
 */
public class UserThreadLocal {
    private static final ThreadLocal<ApUser> LOCAL = new ThreadLocal<>();
    private UserThreadLocal() {
    }
    /**
     * Set.
     *
     * @param user the user
     */
    public static void set(ApUser user) {
        LOCAL.set(user);
    }
    /**
     * Get ap user.
     *
     * @return the ap user
     */
    public static ApUser get() {
        return LOCAL.get();
    }
}
