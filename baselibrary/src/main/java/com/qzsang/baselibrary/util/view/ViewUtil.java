package com.qzsang.baselibrary.util.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qzsang.baselibrary.util.AppUtil;

/**
 * Created by quezhongsang on 2017/10/19.
 */

public class ViewUtil {

    public static View inflate(@LayoutRes int resource) {
        return inflate(resource, null);
    }

    public static View inflate(@LayoutRes int resource, ViewGroup root) {
        return inflate(resource, root, false);
    }

    public static View inflate(@LayoutRes int resource, ViewGroup root, boolean attachToRoot) {

        LayoutInflater factory = LayoutInflater.from(AppUtil.getApplication());
        return factory.inflate(resource,root, attachToRoot);
    }

}
