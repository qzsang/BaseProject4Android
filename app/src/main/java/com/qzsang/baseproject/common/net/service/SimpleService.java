package com.qzsang.baseproject.common.net.service;

import com.qzsang.baselibrary.util.net.interceptor.InterceptorUtil;
import com.qzsang.baseproject.common.net.bean.rp.RpUserBean;
import com.qzsang.baseproject.common.net.bean.rq.RqUserBean;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


public interface SimpleService {

    //username=baseProject&pwd=123456
    @FormUrlEncoded
    @POST("getUser.do")
    Observable<RpUserBean> getuser(@Field("username") String username, @Field("pwd") String pwd);

    //{"pwd":"123456","username":"baseProject"}
    @POST("getUser.do")
    Observable<RpUserBean> getUser(@Body RqUserBean userBean);

    //下载

    @Streaming
    @GET
    @Headers(InterceptorUtil.HEADER_FLAG_DNT_INTERCEPT_RESPONSE_BODY_CONTENT)  //让response不拦截
    Observable<ResponseBody> download(@Url String fileUrl);

    //上传
    @Multipart
    @POST("upload.do")
    Observable<String> upload(@Part MultipartBody.Part file);

}