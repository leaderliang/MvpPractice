package com.android.mvp.net.network;

/**
 * @author hesen
 * @description response基类
 * @since 2019/7/10 15:30
 */
public class BaseRequestEntity<T> {

    private HeaderEntity header;
    private T data;

    public HeaderEntity getHeader() {
        return header;
    }

    public void setHeader(HeaderEntity header) {
        this.header = header;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
