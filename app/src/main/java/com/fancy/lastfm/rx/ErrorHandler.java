package com.fancy.lastfm.rx;

import com.fancy.lastfm.R;
import com.google.gson.stream.MalformedJsonException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.inject.Inject;

/**
 * @author Oleg Mazhukin
 */
public class ErrorHandler implements ErrorMessageProvider {

    public interface ErrorMessageProvider{
        String getConnectionTimeOut();
        String getConnectionError();
        String getDefaultError();
    }

    ErrorMessageProvider provider;

    public ErrorHandler(ErrorMessageProvider provider) {
        this.provider = provider;
    }

    public String getMessage(Throwable throwable) {
        if (isTimeOutException(throwable)) {
            return provider.getConnectionTimeOut();
        } else if (isNetworkError(throwable)) {
            return provider.getConnectionError();
        } else {
            return provider.getDefaultError();
        }
    }

    private boolean isTimeOutException(Throwable throwable) {
        return throwable instanceof SocketTimeoutException;
    }

    private boolean isNetworkError(Throwable throwable) {
        return throwable instanceof IOException && !(throwable instanceof MalformedJsonException);
    }
}
