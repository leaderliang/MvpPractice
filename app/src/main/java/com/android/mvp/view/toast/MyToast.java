package com.android.mvp.view.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.corelibrary.R;


/**
 * TODO 小米 8 青春版 上面连续点击，只有第一次会提示
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/28 19:31
 */
public enum MyToast {

    INSTANCE;

    private Toast mToast;
    private TextView mTvToast;

    public void showToast(Context context, String content) {
        if (mToast == null) {
            mToast = new Toast(context);
            //设置toast显示的位置，这是居中
            mToast.setGravity(Gravity.CENTER, 0, 0);
            //设置toast显示的时长
            mToast.setDuration(Toast.LENGTH_SHORT);
            //自定义样式，自定义布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.view_toast, null);
            mTvToast = view.findViewById(R.id.tvCustomToast);
            //设置自定义的view
            mToast.setView(view);
        }
        mTvToast.setText(content);
        mToast.show();
    }

    public void showToast(Context context, int stringId) {
        showToast(context, context.getString(stringId));
    }

    public void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
            mToast = null;
            mTvToast = null;
        }
    }

}
