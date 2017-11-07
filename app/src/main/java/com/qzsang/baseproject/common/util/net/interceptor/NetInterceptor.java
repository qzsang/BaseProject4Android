package com.qzsang.baseproject.common.util.net.interceptor;

import com.qzsang.baselibrary.util.LogUtil;
import com.qzsang.baselibrary.util.net.interceptor.InterceptorImpl;
import com.qzsang.baselibrary.util.net.interceptor.InterceptorUtil;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * Created by quezhongsang on 2017/10/15.
 * 这里可以根据自己的业务进行封装
 *   比如加密 解密  比如 添加头文件等等
 *
 */

public class NetInterceptor extends InterceptorImpl {
//    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");



    /**
     * 拦截请求     根据自己的业务进行封装
     * @param chain
     * @return
     * @throws IOException
     */
    public Request interceptRequest (Chain chain) throws IOException {
        Request request = chain.request();


//        LogUtil.e("NetInterceptor request", isIntercept(request) + "");
       /* if (isIntercept(request)) {//   根据自己的业务进行封装
            Buffer buffer = new Buffer();
            request.body().writeTo(buffer);//得到数据

            RequestBody formBody = new FormBody.Builder()
                    .add("data", buffer.readUtf8())
                    .build();

            //新建request
            request = request.newBuilder()
                    .post(formBody)
                    .build();
        }*/


        return request;

    }


    /**
     * 响应拦截  根据自己的业务进行封装
     * @param chain
     * @param request
     * @return
     * @throws IOException
     */
    public Response interceptResponse (Chain chain, Request request) throws IOException {
        Response response = chain.proceed(request);

        LogUtil.e("NetInterceptor response", isIntercept(response) + "");
      /*  if (isIntercept(response)) {//   根据自己的业务进行封装
            BufferedSource source = response.body().source();
            source.request(Long.MAX_VALUE); // Buffer the entire body.
            Buffer buffer = source.buffer();

            ResponseBody responseBody = ResponseBody.create(TEXT, buffer.readUtf8());
            response = response.newBuilder()
                    .body(responseBody)
                    .build();
        }*/

        return response;
    }


    //文件上传不拦截
    @Override
    protected boolean isIntercept(Request request) {
        if (request.body() == null )
            return false;
        if (InterceptorUtil.requestTypeIsMultipart(request))
            return false;
        return super.isIntercept(request);
    }

    @Override
    public boolean isIntercept(Response response) {
        if (response.body() == null || response.body().source() == null)
            return false;

        return super.isIntercept(response);
    }
}
