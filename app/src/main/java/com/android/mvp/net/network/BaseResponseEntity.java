package com.android.mvp.net.network;

/**
 * @author hesen
 * @description
 * @since 2019/7/10 15:21
 */
public class BaseResponseEntity<T> {

//    {"resultcode":"101","reason":"错误的请求KEY","result":null,"error_code":10001}

    public String resultcode;
    public String reason;
    private T result;
    public int error_code;
    /**************************/

    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
