package com.qzsang.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.qzsang.baselibrary.R;
import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;


/**
 * Created by qzsang on 2017/4/28 0028.
 */

public class BBaseActivity<E extends ViewDataBinding> extends AppCompatActivity {
    protected E binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .init(); //初始化，默认透明状态栏和黑色导航栏
        super.setContentView(R.layout.activity_base);
    }

//    private boolean isFirstSetContentView = true;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {

        binding = DataBindingManageUtil.inflate(this, layoutResID, (ViewGroup) findViewById(R.id.base_content), true);
        init ();
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

        ImmersionBar.with(this).destroy(); //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}
