package com.android.core.mvp.mvp;

/**
 * TODO MvpCallback
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 15:22
 */
public interface MvpCallback<V extends MvpView, P extends MvpPresenter<V>>{
    /**
     * Create the presenter instance
     * @return
     */
    P createMvpPresenter();

    P getMvpPresenter();

    void setPresenter(P presenter);

    /**
     * Get the MvpView for the presenter
     *
     * @return The view associated with the presenter
     */
    V getMvpView();
}
