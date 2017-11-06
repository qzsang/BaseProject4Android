package com.qzsang.baselibrary.util;

import android.util.Log;

/**
 * Created by qzsang on 2017/4/27 0027.
 */

public class LogUtil {
    public static final String TAG = "Log-";
    private static boolean isOpen = true;

    public static void setIsOpen(boolean isOpen) {
        LogUtil.isOpen = isOpen;
        i(LogUtil.class + "", "setIsOpen " + isOpen);
    }

    public static void e (Class tag, String msg) {
        LogUtil.e(tag.getSimpleName() + "", msg + "");
    }

    public static void i (Class tag, String msg) {
        LogUtil.i(tag.getSimpleName() + "", msg + "");
    }


    public static void e (String tag, String msg) {
        if (isOpen) {
            Log.e(TAG + tag, msg + "");
        }
    }
    public static void i (String tag, String msg) {
        if (isOpen) {
            Log.i(TAG + tag , msg + "");
        }
    }

    public static void d (String tag, String msg) {
        if (isOpen) {
            Log.d(TAG + tag , msg + "");
        }
    }


}
