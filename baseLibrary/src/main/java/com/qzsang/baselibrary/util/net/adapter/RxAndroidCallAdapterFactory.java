package com.qzsang.baselibrary.util.net.adapter;


import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static retrofit2.CallAdapter.*;

/**
 * Created by quezhongsang on 2017/10/20.
 *
 * 做一层封装 让 网络请求在io 线程  ，  响应在  主线程
 */

public class RxAndroidCallAdapterFactory extends Factory {

    private RxJavaCallAdapterFactory mRxJavaCallAdapterFactory = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

    public static RxAndroidCallAdapterFactory create() {
        return new RxAndroidCallAdapterFactory();
    }



    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {

        return  new MyCallAdapter( mRxJavaCallAdapterFactory.get(returnType, annotations, retrofit));
    }


    public static class MyCallAdapter<T> implements CallAdapter<T>{

        CallAdapter realCallAdapter;
        public MyCallAdapter (CallAdapter callAdapter) {
            realCallAdapter = callAdapter;
        }

        @Override
        public Type responseType() {
            return realCallAdapter.responseType();
        }

        @Override
        public <R> T adapt(Call<R> call) {

            T t = (T) realCallAdapter.adapt(call);
            if (t instanceof Observable) {
                Observable observable = (Observable) t;
                observable = observable.observeOn(AndroidSchedulers.mainThread());
                t = (T) observable;
            }
            return t;
        }
    }
}
