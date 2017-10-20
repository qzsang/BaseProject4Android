package com.qzsang.baselibrary.base;

import android.databinding.ViewDataBinding;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qzsang.baselibrary.util.AppUtil;
import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;


/**
 * Created by qzsang on 2017/5/16 0016.
 */

public class BPViewHolder<T extends ViewDataBinding> {
    public T binding = null;

    public BPViewHolder(View itemView) {
        binding = DataBindingManageUtil.bind(itemView);
    }

    public BPViewHolder(@LayoutRes int resource) {
        binding = DataBindingManageUtil.inflate(AppUtil.getApplication(), resource);
    }

}
