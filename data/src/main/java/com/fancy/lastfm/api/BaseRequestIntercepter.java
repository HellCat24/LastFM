package com.fancy.lastfm.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Oleg Mazhukin
 */

public class BaseRequestIntercepter implements Interceptor {

    private String apiKey;

    public BaseRequestIntercepter(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        HttpUrl originalHttpUrl = originalRequest.url();

        Request.Builder newRequestBuilder = originalRequest.newBuilder();
        newRequestBuilder.url(originalHttpUrl.toString() + "&api_key=" + apiKey + "&format=json");
        return chain.proceed(newRequestBuilder.build());
    }
}
