package com.android.core.mvp.mvp;


import com.uber.autodispose.AutoDisposeConverter;


/**
 * TODO MvpView
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public interface MvpView {

    /**
     * 显示加载中
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void dismissLoading();

    /**
     * 数据获取失败
     *
     * @param error
     */
    void onError(String error);

    void onThrowable(Throwable throwable);

    void showMessageToast(int resId);

    void showMessageToast(CharSequence message);

    void showMessageToast(Object message);

    /**
     * 绑定Android生命周期 防止RxJava内存泄漏
     *
     * @param <T>
     * @return
     */
    <T> AutoDisposeConverter<T> bindAutoDispose();

}
