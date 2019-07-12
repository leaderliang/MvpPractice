package com.android.core.mvp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.android.core.R;
import com.android.core.mvp.mvp.MvpPresenter;
import com.android.core.mvp.mvp.MvpView;
import com.android.core.utils.NetworkUtil;
import com.android.core.utils.ProgressDialog;
import com.android.core.utils.Trace;
import com.android.core.utils.permission.OnPermission;
import com.android.core.utils.permission.Permission;
import com.android.core.utils.permission.XXPermissions;
import com.android.core.view.statusbar.StatusBarCompat;
import com.android.core.view.toast.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * TODO BaseActivity
 * activity 基类
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 16:38
 */
public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends BaseMvpActivity<V, P> {


    protected Activity mActivity;
    protected String TAG;
    private Unbinder unbinder;

    Toolbar mToolbar;

    TextView mTitle;

    FrameLayout mBaseContentView;

    private int menuId;

    private String menuStr;

    private View.OnClickListener onClickListenerTopLeft;

    private View.OnClickListener onClickListenerTopRight;

    /**
     * 记录上次点击按钮的时间
     */
    private long lastClickTime;

    /**
     * 按钮连续点击最低间隔时间 单位：毫秒
     */
    public final static int CLICK_TIME = 500;

    protected View mEmptyView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*检测卡顿帧率*/
//        SMFrameCallback.getInstance().start();

        TAG = this.getClass().getSimpleName();
        mActivity = this;
        if (isUseToolBar()) {
            this.setContentView(R.layout.activity_top_bar_base);
            StatusBarCompat.translucentStatusBar(this, true);
            mToolbar = findViewById(R.id.toolbar);
            /*set default left button icon*/
            mToolbar.setNavigationIcon(R.mipmap.arrow_left);
            mToolbar.setBackgroundColor(getResources().getColor(R.color.white));
            mTitle = findViewById(R.id.tv_title);
            mBaseContentView = findViewById(R.id.base_view_content);
            /*使用ToolBar控件替代ActionBar控件*/
            setSupportActionBar(mToolbar);
            /*不使用图片占位*/
            getSupportActionBar().setDisplayShowTitleEnabled(false);

            LayoutInflater.from(this).inflate(this.getLayoutId(), mBaseContentView);
        } else {
            this.setContentView(getLayoutId());
            StatusBarCompat.translucentStatusBar(this, true);
        }
        initBind();
        initView(savedInstanceState);
        setColorSchemeResources(getSwipeRefreshLayout(), R.color.colorAccent);
        initOtherView();
        initData(savedInstanceState);
        setViewListener();

    }


    private void initBind() {
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 支持自定义添加 Toolbar
     *
     * @return
     */
    protected abstract boolean isUseToolBar();

    /**
     * setViewListener
     */
    protected abstract void setViewListener();

    /**
     * 初始化视图
     *
     * @param savedInstanceState savedInstanceState
     */
    protected abstract void initView(@Nullable Bundle savedInstanceState);


    protected void initData(@Nullable Bundle savedInstanceState) {
    }

    /**
     * use butter knife @OnClick()
     * add listener to view
     *
     * @param v
     */
    protected abstract void onViewClick(View v);

    /**
     * 设置布局
     * <p>
     * get layout id, call {@link #setContentView(int)} ()}
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * get refresh view
     *
     * @return
     */
    protected abstract SwipeRefreshLayout getSwipeRefreshLayout();


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
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    /*加载相关 START*/

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void dismissLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(String error) {
        Trace.e(TAG, error);
        dismissLoading();
        showMessageToast(error);
    }

    @Override
    public void onThrowable(Throwable throwable) {
        dismissLoading();
        Trace.e(TAG, throwable);
        if (!NetworkUtil.isNetworkConnected()) {
//            showMessageToast(getStringResources(R.string.check_network_is_ok));
        } else if (throwable instanceof NullPointerException) {
//            showMessageToast(getStringResources(R.string.server_error_try_again));
        }
        // ....

        /*else if (throwable instanceof Exception) {
            Trace.e(TAG, throwable.getMessage());
        } else {
            Trace.e(TAG, throwable.getMessage());
            showMessageToast(getStringResources(R.string.net_error_try_again));
        }*/
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

    /*加载相关 END*/

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
        Intent intent = new Intent(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }

    private void initOtherView() {
        mEmptyView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.empty_view, null);
    }

    /**
     * 设置状态栏字体颜色
     *
     * @param isDark
     */
    public void initStatusBar(boolean isDark) {
//        StatusBarUtil.initStatusBar(this, isDark);
    }

    public void setStatusBarDarkTheme(boolean dark) {
//        StatusBarUtil.setStatusBarDarkTheme(this,dark);
    }

    public String getStringResources(int strId) {
        return getResources().getString(strId);
    }

    public String getStringResources(@StringRes int id, Object... formatArgs) {
        return getResources().getString(id, formatArgs);
    }


    /* 以下 是 Toolbar 相关 START*/

    protected void setToolbarTitle(@Nullable CharSequence title) {
        if (!TextUtils.isEmpty(title)) {
            mTitle.setText(title);
        } else {
            mTitle.setText("");
        }
    }

    /**
     * 默认是 colorPrimaryDark
     *
     * @param color
     */
    protected void setToolbarBackgroundColor(int color) {
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(color);
        }
    }

    protected void setToolbarBackgroundResource(@DrawableRes int resId) {
        if (mToolbar != null) {
            mToolbar.setBackgroundResource(resId);
        }
    }

    protected void setToolbarBackgroundResource(Drawable drawable) {
        if (mToolbar != null) {
            mToolbar.setBackground(drawable);
        }
    }

    protected void setToolbarLeftImg(int iconId) {
        if (mToolbar != null && iconId != 0) {
            mToolbar.setNavigationIcon(iconId);
        }
    }

    /**
     * 添加一个方法设置图标资源id和监听器
     *
     * @param iconId
     * @param listener 自定义一些 返回要执行的事件
     */
    protected void setToolbarLeftButton(int iconId, View.OnClickListener listener) {
        if (mToolbar != null && iconId != 0) {
            mToolbar.setNavigationIcon(iconId);
            this.onClickListenerTopLeft = listener;
        }
    }


    protected void setToolbarRightButton(int menuId, View.OnClickListener rightListener) {
        this.menuStr = "";
        this.menuId = menuId;
        this.onClickListenerTopRight = rightListener;
    }

    /**
     * 设置右侧文本
     *
     * @param menuStr
     * @param rightListener
     */
    protected void setToolbarRightButton(String menuStr, View.OnClickListener rightListener) {
        this.menuStr = menuStr;
        this.menuId = 0;
        this.onClickListenerTopRight = rightListener;
    }


    protected void setToolbarRightButton(String menuStr, int menuId, View.OnClickListener rightListener) {
        this.menuStr = menuStr;
        this.menuId = menuId;
        this.onClickListenerTopRight = rightListener;
    }

    /**
     * 自己处理点击事件
     * 如果要使用返回关闭Activity的功能, 需要在继承的 Activity 的 onClick(View v) 里写上 return super.onOptionsItemSelected(item);
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (onClickListenerTopLeft == null) {
                this.finish();
            } else {
                onClickListenerTopLeft.onClick(item.getActionView());
            }
        } else if (item.getItemId() == R.id.menu_1) {
            if (onClickListenerTopRight != null) {
                onClickListenerTopRight.onClick(item.getActionView());
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (menuId != 0 || !TextUtils.isEmpty(menuStr)) {
            getMenuInflater().inflate(R.menu.menu_activity_base_top_bar, menu);
        }

        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (menuId != 0) {
            menu.findItem(R.id.menu_1).setIcon(menuId);
        }

        if (!TextUtils.isEmpty(menuStr)) {
            menu.findItem(R.id.menu_1).setTitle(menuStr);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /* 以上 是 Toolbar 相关 END*/


    /**
     * 验证上次点击按钮时间间隔，防止重复点击
     */
    public boolean verifyClickTime() {
        if (System.currentTimeMillis() - lastClickTime <= CLICK_TIME) {
            return false;
        }
        lastClickTime = System.currentTimeMillis();
        return true;
    }

    /**
     * 收起键盘
     */
    public void closeInputMethod() {
        // 用于判断虚拟软键盘是否是显示的
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /*权限相关 start*/
    protected void requestPermission() {
        if (!isHasPermission()) {
            XXPermissions.with(mActivity)
                    //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                    .constantRequest()
                    //支持请求6.0悬浮窗权限8.0请求安装权限
//                .permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES)
                    // 不指定权限则自动获取清单中的危险权限
                    .permission(Permission.Group.LOCATION, Permission.Group.STORAGE, Permission.Group.CAMERA)
                    .request(new OnPermission() {
                        @Override
                        public void hasPermission(List<String> granted, boolean isAll) {
                            if (isAll) {
                                showMessageToast("相关权限获取成功");
                            } else {
                                showMessageToast("获取权限成功，部分权限未正常授予");
                            }
                        }

                        @Override
                        public void noPermission(List<String> denied, boolean quick) {
                            if (quick) {
                                ToastUtils.show("被永久拒绝授权，请您手动授予权限");
                                //如果是被永久拒绝就跳转到应用权限系统设置页面
                                XXPermissions.gotoPermissionSettings(mActivity);
                            } else {
                                showMessageToast("获取权限失败");
                            }
                        }
                    });
        }
    }

    protected boolean isHasPermission() {
        if (!XXPermissions.isHasPermission(mActivity, Permission.Group.LOCATION, Permission.Group.STORAGE, Permission.Group.CAMERA)) {
            showMessageToast("您还没有获取到权限或者部分权限未授予");
            return false;
        }
        return true;
    }
    /*权限相关 END*/


    /**
     * @param refreshLayout
     * @param color         getResources().getColor
     */
    public void setSwipeRefreshColor(SwipeRefreshLayout refreshLayout, int... color) {
        if (refreshLayout != null) {
            refreshLayout.setColorSchemeColors(color);
        }
    }

    /**
     * @param refreshLayout
     * @param color         R.color.color
     */
    public void setColorSchemeResources(SwipeRefreshLayout refreshLayout, int... color) {
        if (refreshLayout != null) {
            refreshLayout.setColorSchemeResources(color);
        }
    }

}
