package com.qzsang.baselibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.qzsang.baselibrary.util.databinding.DataBindingManageUtil;


/**
 * Created by qzsang on 2017/4/28 0028.
 */

public class BPBaseActivity<E extends ViewDataBinding> extends AppCompatActivity {
    protected Activity mActivity;
    protected E binding;


    private boolean isFirstSetContentView = true;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (isFirstSetContentView) {//防止无限递归
            isFirstSetContentView = false;
            binding = DataBindingManageUtil.setContentView(this,layoutResID);
            mActivity = this;
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

    private Toast toast;
    public void toast (String string) {
        if (toast == null) {
            toast = Toast.makeText(mActivity, string + "",Toast.LENGTH_SHORT);
        } else {
            toast.setText(string + "");
        }
        toast.show();

    }
}
