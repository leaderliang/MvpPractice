package com.android.mvp.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TODO
 *
 * 小米青春8 toast 会显示应用名 + toast message
 *
 * 这个地方第二个参数需要为null,但是小米会报一个错误
 * Toast.makeText(context, null, Toast.LENGTH_LONG);
 *  ava.lang.NullPointerException: Attempt to invoke interface method 'java.lang.String java.lang.CharSequence.toString()' on a null object reference
 *
 * @author dev.liang <a href="mailto:dev.liang@outlook.com">Contact me.</a>
 * @version 1.0
 * @since 2019/04/17 21:08
 */
public class ToastUtil {

    private static Toast toast;

    public static void show(Context context, String text) {
        if (toast == null) {
            /*这个地方第二个参数需要为null,但是小米会报一个错误*/
            /*java.lang.NullPointerException: Attempt to invoke interface method 'java.lang.String java.lang.CharSequence.toString()' on a null object reference*/
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
            toast.setText(text);
        } else {
            toast.setText(text);
        }
        toast.show();
    }

    public static void show(Context context, @StringRes int resId) {
        if (toast == null) {
            toast = Toast.makeText(context, resId, Toast.LENGTH_LONG);
            toast.setText(resId);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }


    /**
     * 多行居中显示
     */
    public static void singleCenter(Context context, @StringRes int msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
        } else {
            toast.setText(msg);
        }
        ((TextView) toast.getView().findViewById(android.R.id.message)).setGravity(Gravity.CENTER);
        toast.show();
    }

    /**
     * 多行居中显示
     */
    public static void singleCenter(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setText(msg);
        } else {
            toast.setText(msg);
        }
        ((TextView) toast.getView().findViewById(android.R.id.message)).setGravity(Gravity.CENTER);
        toast.show();
    }

}
