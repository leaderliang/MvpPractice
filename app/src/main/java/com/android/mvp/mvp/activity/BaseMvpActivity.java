package com.android.mvp.mvp.activity;

import android.arch.lifecycle.Lifecycle;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.mvp.mvp.mvp.MvpCallback;
import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO BaseMvpActivity
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseMvpActivity<V extends MvpView, P extends MvpPresenter<V>> extends AppCompatActivity implements MvpView, MvpCallback<V, P> {

    private P mMvpPresenter;

    private Unbinder unbinder;

    /**
     * createMvpPresenter
     *
     * @return
     */
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

        setContentView(this.getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        setListener();
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

    /**
     * 初始化视图
     */
    public abstract void initView();

    /**
     * add listener to view
     */
    protected abstract void setListener();

    /**
     * 设置布局
     * <p>
     * get layout id, call {@link #setContentView(int)} ()}
     *
     * @return
     */
    protected abstract int getLayoutId();

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
        unbinder.unbind();
        super.onDestroy();
    }


    /**
     * 绑定生命周期
     * 防止MVP内存泄漏（解决 RxJava 内存泄漏问题）
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(this, Lifecycle.Event.ON_DESTROY));
    }
}
