package com.fancy.lastfm.api;

import java.io.IOException;

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
        Request request = chain.request();
        Response originalResponse = chain.proceed(request);
        return originalResponse.newBuilder()
                .addHeader("api_key", apiKey)
                .addHeader("format", "json")
                .build();
    }
}
