package com.fancy.lastfm.rx;

/**
 * @author Oleg Mazhukin
 */

public interface ErrorMessageProvider {
     String getMessage(Throwable throwable);
}
