package com.qzsang.baselibrary.util;

import android.app.Application;

import com.bumptech.glide.module.AppGlideModule;


/**
 * Created by quezhongsang on 2017/10/17.
 * 为所有的util 提供 上下文环境
 */

public class AppUtil {
    private static Application application;

    public static void init (Application application) {
        AppUtil.application = application;
        AppGlideModule appGlideModule;
    }


    public static Application getApplication() {
        return application;
    }
}
