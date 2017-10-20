package com.qzsang.baseproject.face.simple;

import android.os.Bundle;
import android.view.View;

import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.databinding.ActivitySimpleBinding;

public class SimpleActivity extends BaseActivity<ActivitySimpleBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
    }


    public void clickBtn (View view) {
        binding.tvContent.setText("文字已改变：" + (int)(Math.random()*1000));
    }
}
