package com.qzsang.baselibrary.util.net.interceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public class InterceptorUtil {


    /**
     * 请求的内容类型是 multipart
     * @param request
     * @return
     */
    public static boolean requestTypeIsMultipart (Request request) {
        try {

            if (request.body().contentType().type().equalsIgnoreCase("multipart")) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
