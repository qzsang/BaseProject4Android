package com.qzsang.baselibrary.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/** 
 * 软键盘显示隐藏 
 * http://blog.csdn.net/u012527802/article/details/54908577
 * @time 2016年10月31日10:58:44 
 */  
public class SoftInputUtil {  
  
    /** 
     * 显示软键盘 
     * 
     * @param context 
     */  
    public static void showSoftInput(Context context) {  
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘  
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);  
    }  
  
    /** 
     * 显示软键盘 
     * 
     * @param context 
     */  
    public static void showSoftInput(Context context, View view) {  
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘  
        imm.showSoftInput(view,0);  
    }  
  
    /** 
     * 隐藏软键盘 
     * 
     * @param context 
     * @param view
     */
    public static void hideSoftInput(Context context, View view) {
        InputMethodManager immHide = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 隐藏软键盘  
        immHide.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideSoftInput(Activity activity) {
        hideSoftInput(activity, activity.getWindow().getDecorView());
    }

}  