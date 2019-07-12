package com.android.core.mvp.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.mvp.R;
import com.android.mvp.mvp.mvp.MvpPresenter;
import com.android.mvp.mvp.mvp.MvpView;
import com.android.mvp.utils.NetworkUtil;
import com.android.mvp.utils.ProgressDialog;
import com.android.mvp.utils.Trace;
import com.android.mvp.utils.permission.OnPermission;
import com.android.mvp.utils.permission.Permission;
import com.android.mvp.utils.permission.XXPermissions;
import com.android.mvp.view.toast.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO MvpFragment
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseFragment<V extends MvpView, P extends MvpPresenter<V>> extends BaseMvpFragment<V, P> {


    private boolean isFirstShow = false;
    private Unbinder unBinder;
    protected String TAG;
    protected Activity mActivity;
    protected View mEmptyView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*检测卡顿帧率*/
//        SMFrameCallback.getInstance().start();
        TAG = this.getClass().getSimpleName();

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        isFirstShow = true;

        Trace.i("BaseMvpFragment", "当前的 Fragment " + getClass().getSimpleName() + " layoutID 为 " + getLayoutId());
        View view = inflater.inflate(this.getLayoutId(), container, false);
        unBinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        initView(view, savedInstanceState);
        setColorSchemeResources(getSwipeRefreshLayout(), R.color.colorAccent);
        initOtherView();
        setViewListener();
        initData();
    }

    private void initOtherView() {
        mEmptyView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.empty_view, null);
    }

    /**
     * get layout id
     *
     * @return
     */
    protected abstract int getLayoutId();

    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();

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
        unBinder.unbind();
        super.onDestroyView();
    }


    @Override
    public void onDestroy() {
//        cancelRequests();

        /*leak watch*/
//        MyApplication.getInstance().getRefWatcher(mActivity).watch(this);

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
    public void onError(String error) {
        Trace.e(TAG, error);
        dismissLoading();
        showMessageToast(error);
    }

    /**
     * 网络
     * 服务器返回 null
     * 其他提示
     * <p>
     * 需要拓展
     *
     * @param throwable
     */
    @Override
    public void onThrowable(Throwable throwable) {
        dismissLoading();
        Trace.e(TAG, throwable);
        if (!NetworkUtil.isNetworkConnected()) {
//            showMessageToast(getStringResources(R.string.check_network_is_ok));
        } else if (throwable instanceof NullPointerException) {
//            showMessageToast(getStringResources(R.string.server_error_try_again));
        }
        // ...
        /*else if (throwable instanceof Exception) {
            Trace.e(TAG, throwable.getMessage());
        } else {
            Trace.e(TAG, throwable.getMessage());
        }*/
    }

    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, false, null);
    }

    /**
     * @param clz
     * @param isCloseCurrentActivity
     */
    public void startActivity(Class<?> clz, boolean isCloseCurrentActivity) {
        startActivity(clz, isCloseCurrentActivity, null);
    }

    /**
     * @param clz
     * @param isCloseCurrentActivity
     * @param bundle
     */
    public void startActivity(Class<?> clz, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            getActivity().finish();
        }
    }


    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void dismissLoading() {
        ProgressDialog.getInstance().dismiss();
    }


    @Override
    public void showMessageToast(int resId) {
        showMessageToast(getStringResources(resId));
    }

    @Override
    public void showMessageToast(CharSequence message) {
        ToastUtils.show(message);
    }

    @Override
    public void showMessageToast(Object message) {
        ToastUtils.show(message);
    }

    public String getStringResources(int strId) {
        return getResources().getString(strId);
    }

    public String getStringResources(int strId, Object... formatArgs) {
        return getResources().getString(strId, formatArgs);
    }

    /**
     * setViewListener
     */
    protected abstract void setViewListener();

    /**
     * 初始化视图
     *
     * @param view
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(View view, @Nullable Bundle savedInstanceState);

    /**
     * use butter knife @OnClick()
     * add listener to view
     *
     * @param v
     */
    protected abstract void onViewClick(View v);

    protected abstract void initData();

    /*权限相关 start*/
    protected void requestPermission() {
        if (!isHasPermission()) {
            XXPermissions.with(getActivity())
                    //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                    .constantRequest()
                    //支持请求6.0悬浮窗权限8.0请求安装权限
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                    // 不指定权限则自动获取清单中的危险权限
                    .permission(Permission.Group.LOCATION, Permission.Group.STORAGE)
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            if (isAll) {
                                ToastUtils.show("相关权限获取成功");
                            } else {
                                ToastUtils.show("获取权限成功，部分权限未正常授予");
                            }
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                ToastUtils.show("被永久拒绝授权，请您手动授予权限");
                                //如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.gotoPermissionSettings(getActivity());
                            } else {
                                ToastUtils.show("获取权限失败");
                            }
                        }
                    });
        }
    }

    protected boolean isHasPermission() {
        if (!XXPermissions.isHasPermission(getActivity(), Permission.Group.LOCATION, Permission.Group.STORAGE)) {
            ToastUtils.show("还没有获取到权限或者部分权限未授予");
            return false;
        }
        return true;
    }
    /*权限相关 end*/


    /**
     * @param refreshLayout
     * @param color  getResources().getColor
     */
    public void setSwipeRefreshColor(SwipeRefreshLayout refreshLayout, int... color) {
        if (refreshLayout != null) {
            refreshLayout.setColorSchemeColors(color);
        }
    }

    /**
     * @param refreshLayout
     * @param color   R.color.color
     */
    public void setColorSchemeResources(SwipeRefreshLayout refreshLayout, int... color) {
        if (refreshLayout != null) {
            refreshLayout.setColorSchemeResources(color);
        }
    }

}
