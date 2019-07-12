package com.android.core.net;

import com.android.core.bean.BaseResponse;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 描述：Retrofit Subscriber 封装
 * <p>
 * Activity 界面 Observer 类型接口处理
 *
 * @author devliang
 * @date 2019-07-11 15:00:39
 */
public abstract class BaseFlowable<T> implements Subscriber<BaseResponse<T>> {


    public BaseFlowable() {
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);  //注意这句代码
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

    @Override
    public void onError(Throwable throwable) {
        onNetError(throwable);
    }

    @Override
    public void onComplete() {

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
