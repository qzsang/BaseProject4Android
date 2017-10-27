package com.qzsang.baselibrary.util.net;



import com.qzsang.baselibrary.util.net.adapter.RxAndroidCallAdapterFactory;
import com.qzsang.baselibrary.util.net.converter.StringConverterFactory;
import com.qzsang.baselibrary.util.net.interceptor.LoggingInterceptor;
import com.qzsang.baselibrary.util.net.interceptor.StartInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by qzsang on 2017/9/25.
 */

public class NetUtil {

    public static final int TIMEOUT_READ = 120;//超时时间设置
    public static final int TIMEOUT_WRITE = 120;//超时时间设置
    public static final int TIMEOUT_CONNECTION = 10;


    private static NetUtil instance;
    private OkHttpClient mOkHttpClient;
    private StringConverterFactory mStringConverterFactory;
    private RxAndroidCallAdapterFactory mRxAndroidCallAdapterFactory;

    private static Retrofit mRetrofit;


    public synchronized static Retrofit init (String baseUrl, Interceptor... interceptors) {

        if (instance == null) {
            instance = new NetUtil();

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(true)      //失败重连
                    .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS) //time out
                    .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT_WRITE, TimeUnit.SECONDS)
                    .addInterceptor(new StartInterceptor())
                    .addInterceptor(new LoggingInterceptor());

            if (interceptors != null) {
                for (Interceptor interceptor : interceptors) {
                    builder.addInterceptor(interceptor);
                }
            }

            instance.mOkHttpClient = builder.build();

            instance.mStringConverterFactory = StringConverterFactory.create();
            instance.mRxAndroidCallAdapterFactory = RxAndroidCallAdapterFactory.create();
        }

        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(instance.mStringConverterFactory)
                .client(instance.mOkHttpClient)//设置客户端
                .addCallAdapterFactory(instance.mRxAndroidCallAdapterFactory)
                .build();

        return mRetrofit;
    }

    private static <T> T create(Retrofit retrofit, Class<?> clazz) {
        if (retrofit == null || clazz == null)
            return null;
        return (T) retrofit.create(clazz);
    }

    public static <T> T create(Class<?> clazz) {
        return NetUtil.create(mRetrofit, clazz);
    }



}
