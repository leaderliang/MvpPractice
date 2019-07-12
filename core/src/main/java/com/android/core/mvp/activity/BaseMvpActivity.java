package com.android.core.mvp.activity;

import android.arch.lifecycle.Lifecycle;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.mvp.mvp.mvp.MvpCallback;
import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

/**
 * TODO BaseMvpActivity
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpView, MvpCallback<V, P> {

    private P mMvpPresenter;

    @Override
    public abstract P createMvpPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMvpPresenter = createMvpPresenter();
        if (mMvpPresenter == null) {
            throw new IllegalStateException("BaseMvpActivity, mMvpPresenter is null;");
        }
        getMvpPresenter().attachMvpView(getMvpView());


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }


    @Override
    public P getMvpPresenter() {
        if (mMvpPresenter == null) {
            synchronized (BaseMvpActivity.class) {
                if (mMvpPresenter == null) {
                    mMvpPresenter = createMvpPresenter();
                }
            }
        }
        return mMvpPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mMvpPresenter = presenter;
    }

    @Override
    public V getMvpView() {
        return (V) this;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        getMvpPresenter().detachMvpView();
        super.onDestroy();
    }


    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
