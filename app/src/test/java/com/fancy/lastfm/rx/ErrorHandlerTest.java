package com.fancy.lastfm.rx;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.SocketTimeoutException;

import static junit.framework.Assert.assertEquals;

/**
 * @author Oleg Mazhukin
 */

public class ErrorHandlerTest {

    private ErrorHandler errorHandler;

    @Before
    public void setUp() {
        errorHandler = new ErrorHandler(new ErrorHandler.ErrorMessageProvider() {
            @Override
            public String getConnectionTimeOut() {
                return "Connection Timeout";
            }

            @Override
            public String getConnectionError() {
                return "Connection Error";
            }

            @Override
            public String getDefaultError() {
                return "Oops Error Occurred";
            }
        });
    }

    @Test
    public void whenConnectionErrorMessageIsAppropriate() {
        assertEquals(errorHandler.getMessage(new IOException()), "Connection Error");
    }

    @Test
    public void whenTimeoutErrorMessageIsAppropriate() {
        assertEquals(errorHandler.getMessage(new SocketTimeoutException()), "Connection Timeout");
    }

    @Test
    public void whenUnknownErrorMessageIsAppropriate() {
        assertEquals(errorHandler.getMessage(new Exception()), "Oops Error Occurred");
    }
}
