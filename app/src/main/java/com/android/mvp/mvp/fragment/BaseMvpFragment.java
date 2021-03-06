package com.android.mvp.mvp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp.mvp.mvp.MvpCallback;
import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.android.mvp.utils.ToastUtil;
import com.android.mvp.utils.Trace;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO BaseMvpFragment
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseMvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends Fragment implements MvpView, MvpCallback<V, P> {

    private P mMvpPresenter;

    private Unbinder unBinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Trace.i("BaseMvpFragment", getClass().getSimpleName() + "layoutID" + getLayoutId());
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMvpPresenter = createMvpPresenter();
        if (mMvpPresenter == null) {
            throw new IllegalStateException("BaseMvpFragment, mMvpPresenter is null;");
        }
        getMvpPresenter().attachMvpView(getMvpView());

        initView(view);
        setListener();
    }

    @Override
    public void onDestroyView() {
        getMvpPresenter().detachMvpView();
        unBinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public abstract P createMvpPresenter();

    @Override
    public P getMvpPresenter() {
        if (mMvpPresenter == null) {
            synchronized (BaseMvpFragment.class) {
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
     * init View
     */
    protected abstract void initView(View view);

    /**
     * add listener to view
     */
    protected abstract void setListener();

    /**
     * get layout id
     *
     * @return
     */
    protected abstract int getLayoutId();

    @Override
    public void showMessageToast(int resID) {
        showMessageToast(getString(resID));
    }

    @Override
    public void showMessageToast(String message) {
        ToastUtil.show(getContext(), message);
    }

}
