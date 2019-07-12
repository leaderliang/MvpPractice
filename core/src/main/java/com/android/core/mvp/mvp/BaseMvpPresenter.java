package com.android.core.mvp.mvp;

/**
 * TODO BaseMvpPresenter
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public class BaseMvpPresenter<V extends MvpView> implements MvpPresenter<V> {

    protected V mMvpView;

    /**
     * 绑定view，一般在初始化中调用该方法
     *
     * @param view view
     */
    @Override
    public void attachMvpView(V view) {
        this.mMvpView = view;
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */

    @Override
    public void detachMvpView() {
        if (mMvpView != null) {
            this.mMvpView = null;
        }

    }

    /**
     * View是否绑定
     *
     * @return
     */
    public boolean isViewAttached() {
        return mMvpView != null;
    }


}
