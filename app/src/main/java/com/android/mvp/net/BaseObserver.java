package com.android.mvp.net;

import com.android.mvp.bean.BaseResponse;
import com.android.mvp.mvp.activity.BaseActivity;
import com.android.mvp.utils.Trace;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 描述：Retrofit Subscriber 封装
 * <p>
 * Activity 界面 Observer 类型接口处理
 *
 * @author devliang
 * @date 2019-07-11 15:00:39
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {



    public BaseObserver() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        onNetError(e);
    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if (response.resultOk()) {
            onSuccess(response);
        } else {
            // 网络请求正常，code 错误
            onFailure(response);
        }
    }

    /**
     * 请求成功并且服务器未下发异常
     *
     * @param response
     */
    protected abstract void onSuccess(BaseResponse<T> response);

    /**
     * 返回失败
     *
     * @param errorResponse
     */
    protected abstract void onFailure(BaseResponse<T> errorResponse);


    /**
     * 请求成功, 返回非 JavaBean
     *
     * @param response
     */
    protected void onOtherSuccess(BaseResponse<T> response) {

    }

    /**
     * 网络异常、或者一些数据返回格式问题
     *
     * @param throwable
     */
    protected void onNetError(Throwable throwable) {
//        Trace.e(mContent.getClass().getSimpleName(), throwable);
//        if (mContent.get() != null) {
            NetworkError.error(throwable);
//        }
    }
}
