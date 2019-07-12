package com.android.core.net;

/**
 *
 * @author devliang
 */
public class ServerException extends RuntimeException {

    public int code;

    public ServerException(int code, String message) {
        super(message);
        this.code = code;
    }
}
