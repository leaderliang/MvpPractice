package com.android.mvp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.imageloader.config.ScaleMode;
import com.android.imageloader.loader.ImageLoader;
import com.android.mvp.R;
import com.android.mvp.bean.Repo;
import com.android.mvp.constant.AppConstant;
import com.android.mvp.contract.UserReposContract;
import com.android.mvp.mvp.activity.BaseActivity;
import com.android.mvp.presenter.UserReposPresenter;
import com.android.mvp.utils.CollectionUtils;
import com.android.mvp.utils.JsonFormat;
import com.android.mvp.utils.Trace;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;

/**
 * TODO
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/23 12:25
 */
public class MainActivity extends BaseActivity<UserReposContract.View, UserReposContract.Presenter> implements UserReposContract.View {

    private final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.horizontal_layout)
    LinearLayout mHorizontalLayout;


    @Override
    public UserReposContract.Presenter createMvpPresenter() {
        return new UserReposPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected boolean isUseToolBar() {
        return false;
    }

    @Override
    protected void setViewListener() {

    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        getMvpPresenter().getUserRepo();
    }

    @Override
    protected void initData(@Nullable Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setHorizontalViewData();
    }

    @Override
    protected void onViewClick(View v) {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return null;
    }


    @Override
    public void onSuccess(List<Repo> bean) {
        Trace.e(TAG, JsonFormat.format(new Gson().toJson(bean)));
//        Log.e(TAG,"----->"+new Gson().toJson(bean));
    }

    private void setHorizontalViewData() {
        mHorizontalLayout.removeAllViews();
        /*将需要滑动的布局动态添加到 HorizontalScrollView 包裹的布局中来实现滑动效果*/
        for (int i = 0; i < AppConstant.getImageUrl().size(); i++) {
            FrameLayout mHorizontalItem = (FrameLayout) LayoutInflater.from(this).inflate(R.layout.view_horizontal_scrollview_item, null);
            ImageView ivHorizontalScrollview = mHorizontalItem.findViewById(R.id.iv_horizontal_scrollview);

            ImageLoader.with(this)
                    .url(AppConstant.getImageUrl().get(i))
                    .scale(ScaleMode.FIT_CENTER)
                    .rectRoundCorner(2)
                    .into(ivHorizontalScrollview);

           /* RequestOptions myOptions = new RequestOptions().transform(new GlideRoundTransform(30));
            Glide.with(this).load(AppConstant.getImageUrl().get(i)).placeholder(R.mipmap.login_bg).apply(myOptions).into(ivHorizontalScrollview);*/

            mHorizontalLayout.addView(mHorizontalItem);
        }
    }


}
