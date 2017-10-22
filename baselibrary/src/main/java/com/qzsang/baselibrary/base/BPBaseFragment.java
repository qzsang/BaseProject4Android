package com.qzsang.baselibrary.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


/**
 * Created by qzsang on 2017/5/10 0010.
 */

public abstract class BPBaseFragment<E extends ViewDataBinding> extends Fragment {
    protected E binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutResID = setContentView();
        binding = DataBindingManageUtil.inflate(LayoutInflater.from(getContext()),layoutResID);
        return binding.getRoot();
    }

    public abstract int setContentView();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    protected void init () {}

    private Toast toast;

    public void toast (String string) {
        if (toast == null) {
            toast = Toast.makeText(getActivity(), string + "",Toast.LENGTH_SHORT);
        } else {
            toast.setText(string + "");
        }
        toast.show();

    }


    //订阅者管理
    protected List<Subscription> subscriptions = new ArrayList<>();
    public void addSubscription (Subscription subscription) {
        if (subscription != null)
            subscriptions.add(subscription);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
        subscriptions.clear();

    }
}
