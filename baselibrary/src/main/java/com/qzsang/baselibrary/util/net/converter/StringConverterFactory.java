package com.qzsang.baselibrary.util.net.converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StringConverterFactory extends Converter.Factory {

    GsonConverterFactory gsonConverterFactory;

    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    private StringConverterFactory() {
        gsonConverterFactory = GsonConverterFactory.create();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {

        try {
            if (type == String.class) {
                return new StringResponseBodyConverter();
            }
        }catch (Exception e) {
        }

       return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,
                                                          Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {

       // return new StringRequestBodyConverter();

        return gsonConverterFactory.requestBodyConverter(type,parameterAnnotations,methodAnnotations,retrofit);
    }
}