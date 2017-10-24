package com.qzsang.baseproject;

import android.app.Application;

import com.qzsang.baselibrary.util.AppUtil;
import com.qzsang.baselibrary.util.ImgSelUtil;
import com.qzsang.baseproject.common.util.MyImageUtil;
import com.qzsang.baseproject.common.util.net.MyNetUtil;


/**
 * Created by little on 2017/4/27 0027.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        init();
    }


    private void init () {
        AppUtil.init(instance);
        MyNetUtil.init();
        MyImageUtil.init();
        ImgSelUtil.init();


    }
}
