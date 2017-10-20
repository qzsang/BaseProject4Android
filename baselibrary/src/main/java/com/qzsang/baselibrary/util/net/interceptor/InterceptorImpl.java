package com.qzsang.baselibrary.util.net.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public abstract class InterceptorImpl implements Interceptor {



    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = interceptRequest(chain);

        return interceptResponse (chain , request);
    }

    /**
     * 拦截中  request的部分
     * @param chain
     * @return
     * @throws IOException
     */
    protected Request interceptRequest (Chain chain) throws IOException {
        return chain.request();
    }

    /**
     * 拦截中  Response的部分
     * @param chain
     * @return
     * @throws IOException
     */
    protected Response interceptResponse (Chain chain, Request request) throws IOException {
        return chain.proceed(request);
    }

    /**
     * 是否拦截request
     * @param request
     * @return
     */
    protected boolean isIntercept (Request request) {
        return false;
    }

    /**
     * 是否拦截response
     * @param response
     * @return
     */
    protected boolean isIntercept (Response response) {
        return false;
    }
}
