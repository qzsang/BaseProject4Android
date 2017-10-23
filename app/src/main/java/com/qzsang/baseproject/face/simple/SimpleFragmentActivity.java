package com.qzsang.baseproject.face.simple;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.databinding.ActivitySimpleFragmentBinding;
import com.qzsang.baseproject.face.simple.fragment.SimpleFragment;

public class SimpleFragmentActivity extends BaseActivity<ActivitySimpleFragmentBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_fragment);

    }

    @Override
    protected void init() {
        super.init();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_content, new SimpleFragment());
        fragmentTransaction.commit();


    }
}
