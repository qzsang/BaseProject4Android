package com.qzsang.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


/**
 * Created by qzsang on 2017/4/28 0028.
 */

public class BBaseActivity<E extends ViewDataBinding> extends AppCompatActivity {
    protected E binding;

    private boolean isFirstSetContentView = true;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isFirstSetContentView) {//防止无限递归
            isFirstSetContentView = false;
            binding = DataBindingManageUtil.setContentView(this,layoutResID);
            init ();
        } else {
            super.setContentView(layoutResID);
        }
    }

    protected void init () {}


    //公用部分

    public void startActivity(Class clas) {
        Intent intent = new Intent(this,clas);
        super.startActivity(intent);
    }


    //toast
    private Toast toast;
    public void toast (String string) {
        if (toast == null) {
            toast = Toast.makeText(this, string + "",Toast.LENGTH_SHORT);
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
    protected void onDestroy() {
        super.onDestroy();
        for (Subscription subscription : subscriptions) {
            if (subscription != null && !subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
        subscriptions.clear();

    }
}
