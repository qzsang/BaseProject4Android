package com.qzsang.baselibrary.util.databinding;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by qzsang on 2017/5/16 0016.
 */

public class DataBindingManageUtil {


    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId) {
        return inflate(inflater, layoutId, null);
    }

    public static <T extends ViewDataBinding> T inflate(Context context, int layoutId) {
        return inflate( LayoutInflater.from(context), layoutId, null);
    }

    public static <T extends ViewDataBinding> T inflate(Context context, int layoutId,
                                                        @Nullable ViewGroup parent) {

        return inflate(LayoutInflater.from(context), layoutId, parent);
    }

    public static <T extends ViewDataBinding> T inflate(LayoutInflater inflater, int layoutId,
                                                        @Nullable ViewGroup parent) {

        return DataBindingUtil.inflate(inflater, layoutId, parent, parent != null);
    }

    public static <T extends ViewDataBinding> T setContentView(Activity activity, int layoutId) {
        return DataBindingUtil.setContentView(activity,layoutId);
    }

    public static <T extends ViewDataBinding> T bind(View root) {
        return DataBindingUtil.bind(root);
    }
}
