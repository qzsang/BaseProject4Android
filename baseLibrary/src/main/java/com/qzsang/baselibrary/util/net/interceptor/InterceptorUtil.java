package com.qzsang.baselibrary.util.net.interceptor;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public class InterceptorUtil {

    public static final String HEADER_FLAG_DNT_INTERCEPT_RESPONSE_BODY = "dnt_intercept_response_body";

    public static final String HEADER_FLAG_DNT_INTERCEPT_RESPONSE_BODY_CONTENT = HEADER_FLAG_DNT_INTERCEPT_RESPONSE_BODY + ":flag";

    public static Map<String,Integer> tempUrlMap = new HashMap<>();//在改map中存在的url将不拦截
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


    /**
     * 在tempUrlMap
     * @param response
     * @return
     */
    public static boolean isIntercept4Url (Response response) {
        try {
            if (InterceptorUtil.tempUrlMap.get(response.request().url().toString()) != null)
                return false;
        }catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }
}
