package com.android.mvp.mvp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.android.mvp.utils.ProgressDialog;
import com.android.mvp.utils.ToastUtil;


/**
 * TODO BaseActivity
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>>  extends BaseMvpActivity<V, P> {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
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
        ToastUtil.show(this, message);
    }
}
