package com.qzsang.baselibrary.util.net.interceptor;



import com.qzsang.baselibrary.util.LogUtil;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by quezhongsang on 2017/10/15.
 */

public final class LoggingInterceptor extends InterceptorImpl {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    public static boolean isOpen = false;
    public final static long MAX_RP_BODY_PRINT_SIZE = 1024 * 1024 / 2;//0.5M  response body  超过这个大小 将不再打印日志


    @Override
    public Response intercept(Chain chain) throws IOException {
        if (isOpen) {
            return chain.proceed(chain.request());
        }

        return super.intercept(chain);
    }


    @Override
    public Request interceptRequest(Chain chain) throws IOException {
        Request request = super.interceptRequest(chain);

        String requestInfo = "--> " + request.url() + ' ' + request.method();

        if (isIntercept(request)) {
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);

            requestInfo += '\n' + buffer.readString(UTF8);
        }

        LogUtil.d("net rq", requestInfo);

        return request;
    }

    @Override
    public Response interceptResponse(Chain chain, Request request) throws IOException {
        Response response = super.interceptResponse(chain, request);

        String responseInfo = "<-- " + response.request().url() + ' ' + response.code();

        if (isIntercept(response)) {

            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();
            responseInfo +=  '\n'
                    + buffer.clone().readString(UTF8);
        }

        LogUtil.d("net rp", responseInfo);
        return response;
    }



    @Override
    public boolean isIntercept(Request request) {
        if (request.body() == null)
            return false;

        if (InterceptorUtil.requestTypeIsMultipart(request))
            return false;

        return true;
    }

    @Override
    public boolean isIntercept(Response response) {
        if (response.body() == null || response.body().contentLength() > MAX_RP_BODY_PRINT_SIZE || response.body().source() == null)
            return false;

        return true;
    }

}
