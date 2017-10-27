package com.qzsang.baseproject.common.util.net;


import com.qzsang.baselibrary.util.net.NetUtil;
import com.qzsang.baseproject.common.util.net.interceptor.NetInterceptor;

import retrofit2.Retrofit;

/**
 * Created by quezhongsang on 2017/10/14.
 */

public class MyNetUtil {
    public final static String BASE_URL = "http://192.168.0.101:8080";


    public static void init () {
         NetUtil.init(BASE_URL, new NetInterceptor());
    }


}
