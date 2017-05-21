package com.fancy.lastfm.rx;

import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

/**
 * Created by Oleg on 21.05.2017.
 */
public class ErrorHandler implements ErrorMessageProvider {

    @Inject
    public ErrorHandler() {
    }

    public String getMessage(Throwable throwable) {
        if (isTimeOutException(throwable)) {
            return "Connection Timeout";
        } else if (isNetworkError(throwable)) {
            return "Connection Error";
        } else {
            return "Oops Error Occurred";
        }
    }

    private boolean isTimeOutException(Throwable throwable) {
        return throwable instanceof SocketTimeoutException;
    }

    private boolean isNetworkError(Throwable throwable) {
        return throwable instanceof IOException && !(throwable instanceof MalformedJsonException);
    }
}
