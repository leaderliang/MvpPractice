package com.android.mvp.mvp.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.android.mvp.utils.ProgressDialog;
import com.android.mvp.utils.ToastUtil;

import butterknife.Unbinder;

/**
 * TODO MvpFragment
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class MvpFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseMvpFragment<V, P> {

    private Unbinder unBinder;

    private boolean isFirstShow = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mDialog = new CustomProgressDialog(getActivity(), R.style.DialogStyle);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        isFirstShow = true;
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        isFirstShow = false;
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
//        cancelRequests();
        super.onDestroy();
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
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }


    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void showMessageToast(int resId) {
        showMessageToast(getString(resId));
    }

    @Override
    public void showMessageToast(String message) {
        ToastUtil.show(getActivity(), message);
    }
}
