package com.qzsang.baselibrary.util.net.interceptor;



import com.qzsang.baselibrary.util.StringUtil;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by quezhongsang on 2017/10/15.
 *
 * 作为开始的拦截器
 */

public final class StartInterceptor extends InterceptorImpl {


    @Override
    public Request interceptRequest(Chain chain) throws IOException {
        Request request = super.interceptRequest(chain);


        String flag = InterceptorUtil.HEADER_FLAG_DNT_INTERCEPT_RESPONSE_BODY;
        if (!StringUtil.isEmpty(request.header(flag))) {
            String url = request.url().toString();

            int count = 1;

            synchronized (InterceptorUtil.tempUrlMap) {
                Integer value = InterceptorUtil.tempUrlMap.get(url);
                if (value != null) {
                    count += value;
                }

                InterceptorUtil.tempUrlMap.put(url, count);
            }


            //清除标记的header
            request = request
                    .newBuilder()
                    .removeHeader(flag)
                    .build();
        }

        return request;
    }


    @Override
    protected Response interceptResponse(Chain chain, Request request) throws IOException {
        Response response = super.interceptResponse(chain, request);
        String url = request.url().toString();

        synchronized (InterceptorUtil.tempUrlMap) {
            Integer value = InterceptorUtil.tempUrlMap.get(url);
            if (value != null) {
                if ( (--value) > 0 ) {
                    InterceptorUtil.tempUrlMap.put(url, value);
                } else {
                    InterceptorUtil.tempUrlMap.remove(url);
                }
            }
        }


        return response;
    }
}
