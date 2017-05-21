package com.fancy.lastfm.rx;

/**
 * Created by Oleg on 21.05.2017.
 */

public interface ErrorMessageProvider {
     String getMessage(Throwable throwable);
}
