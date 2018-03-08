package com.er.greentest;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by THTF on 2017/11/20.
 * Desc:
 */

public class MyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        okhttp3.MediaType mediaType = proceed.body().contentType();
        String content = proceed.body().string();
        Response build = proceed.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        return build;
    }
}
