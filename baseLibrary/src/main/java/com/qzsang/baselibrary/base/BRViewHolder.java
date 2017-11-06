package com.qzsang.baselibrary.base;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qzsang.baselibrary.util.AppUtil;
import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;


/**
 * Created by qzsang on 2017/5/16 0016.
 */

public class BRViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T binding = null;

    public BRViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingManageUtil.bind(itemView);

    }



}
