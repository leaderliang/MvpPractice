package com.android.core.net;

import com.android.mvp.view.toast.ToastUtils;

/**
 * 网络统一异常处理
 *
 * @author devliang
 * @date 2019-07-11 14:26:40
 */
public class NetworkError {


    /**
     * 可以用于跳转界面等操作
     *
     * @param throwable
     */
    public static void error(Throwable throwable) {
        RetrofitException.ResponseException responeThrowable = RetrofitException.retrofitException(throwable);
        // 此处可以通过判断错误代码来实现根据不同的错误代码做出相应的反应
        switch (responeThrowable.code) {
            case RetrofitException.ServerError.UNKNOWN:
            case RetrofitException.ServerError.PARSE_ERROR:
            case RetrofitException.ServerError.NETWORD_ERROR:
            case RetrofitException.ServerError.HTTP_ERROR:
            case RetrofitException.ServerError.SSL_ERROR:
                ToastUtils.show(responeThrowable.message);
                break;
            case -1:
                // 跳转到登陆页面
                break;
            default:
                ToastUtils.show(responeThrowable.message);
                break;
        }
    }
}
