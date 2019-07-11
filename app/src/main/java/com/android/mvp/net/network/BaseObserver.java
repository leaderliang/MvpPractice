package com.android.mvp.net.network;

import android.accounts.NetworkErrorException;
import android.text.TextUtils;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author hesen
 * @description
 * @since 2019/7/10 15:30
 */
public abstract class BaseObserver<T> implements Observer<BaseResponseEntity<T>> {

    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        onRequestStart();
    }

    @Override
    public void onNext(BaseResponseEntity<T> tBaseEntity) {
        onRequestEnd();
        //TODO 根据接口协议处理
        String message_common = "Oops, something went wrong. Please try again.";
        if (tBaseEntity.getErrorCode() == 0) {//成功
            try {
                onSuccess(tBaseEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                if (!TextUtils.isEmpty(tBaseEntity.getErrorMsg())) {
                    onFailure(tBaseEntity.getErrorMsg());
                } else {
                    onFailure(message_common);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        onRequestEnd();
        String message_common = "Oops, something went wrong. Please try again.";
        String msg_timeout = "Oops, connection timeout, please try again later";
        try {
            if (e instanceof ConnectException
                    || e instanceof TimeoutException
                    || e instanceof NetworkErrorException
                    || e instanceof UnknownHostException) {
                onFailure(msg_timeout);
            } else {
                onFailure(message_common);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 返回成功
     *
     * @param tBaseEntity
     */
    protected abstract void onSuccess(BaseResponseEntity<T> tBaseEntity);

    /**
     * 返回失败
     *
     * @param errorMessage
     */
    protected abstract void onFailure(String errorMessage);

    /**
     * 请求开始
     */
    protected void onRequestStart() {
    }


    /**
     * 请求结束
     */
    public void onRequestEnd() {
    }

}