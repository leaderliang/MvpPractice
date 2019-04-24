package com.android.mvp.mvp.mvp;

import android.support.annotation.UiThread;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:29
 */
public interface MvpPresenter<V extends MvpView> {
    /**
     * attach the mvpview to this presenter
     * @param view
     */
    @UiThread
    void attachMvpView(V view);

    /**
     * detach the mvpview from this presenter,will be callec if the view has been destroyed
     * Activity.detachView or fragment.onDestoryView()
     */
    @UiThread
    void detachMvpView();
}
