package com.qzsang.baselibrary.util.net;



import com.qzsang.baselibrary.util.CloseableUtil;
import com.qzsang.baselibrary.util.StringUtil;
import com.qzsang.baselibrary.util.net.adapter.RxAndroidCallAdapterFactory;
import com.qzsang.baselibrary.util.net.converter.StringConverterFactory;
import com.qzsang.baselibrary.util.net.interceptor.LoggingInterceptor;
import com.qzsang.baselibrary.util.net.interceptor.StartInterceptor;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

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


    private synchronized static Retrofit init (String baseUrl,SSLSocketFactory sslSocketFactory, Interceptor... interceptors) {

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
            if (sslSocketFactory != null) {
                builder.sslSocketFactory(sslSocketFactory);
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


    /**
     * 设置https的信任证书
     *
     *  参考： http://blog.csdn.net/lmj623565791/article/details/48129405
     * @param certificates
     */
    private static SSLSocketFactory getSSL(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                CloseableUtil.close(certificate);
            }

            SSLContext sslContext = SSLContext.getInstance("TLS");

            TrustManagerFactory trustManagerFactory =
                    TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());

            trustManagerFactory.init(keyStore);
            sslContext.init
                    (
                            null,
                            trustManagerFactory.getTrustManagers(),
                            new SecureRandom()
                    );

            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    private static <T> T create(Retrofit retrofit, Class<?> clazz) {
        if (retrofit == null || clazz == null)
            return null;
        return (T) retrofit.create(clazz);
    }

    public static <T> T create(Class<?> clazz) {
        return NetUtil.create(mRetrofit, clazz);
    }




    public static class Builder {
        String baseUrl;
        Interceptor[] interceptors;
        InputStream[] certificates;

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setInterceptors(Interceptor... interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder setCertificates(InputStream... certificates) {
            this.certificates = certificates;
            return this;
        }

        public boolean build () {
            if (StringUtil.isEmpty(baseUrl)) {
                return false;
            }

            NetUtil.init(
                    baseUrl,
                    certificates == null || certificates.length == 0 ? null : getSSL(certificates),
                    interceptors);
            return true;
        }
    }
}
