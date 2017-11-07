package com.qzsang.baseproject.common.util.net;


import android.util.Pair;

import com.qzsang.baselibrary.util.net.NetUtil;
import com.qzsang.baseproject.MyApplication;
import com.qzsang.baseproject.common.util.net.interceptor.NetInterceptor;

import java.io.IOException;

import retrofit2.Retrofit;

/**
 * Created by quezhongsang on 2017/10/14.
 */

public class MyNetUtil {

    public final static String BASE_URL = "http://192.168.0.101:8080";

    public final static String DEBUG_BASE_URL_4_HTTPS = "https://kyfw.12306.cn";

    /**
     * first  是否进入调试模式   ；second  调试模式的baseUrl
     */
    private final static Pair<Boolean,String> netModle = new Pair<>(false, DEBUG_BASE_URL_4_HTTPS);

    public static void init () {
        try {
            new NetUtil.Builder()
                    .setBaseUrl(netModle.first ? netModle.second : BASE_URL)
                    .setInterceptors(new NetInterceptor())
                    .setCertificates(MyApplication.instance.getAssets().open("12306srca.cer"))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pair<Boolean, String> getNetModle() {
        return netModle;
    }
}
