package com.qzsang.baseproject.face.simple.fragment;

import android.view.View;

import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseFragment;
import com.qzsang.baseproject.databinding.FragmentSimpleBinding;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public class SimpleFragment extends BaseFragment<FragmentSimpleBinding> {
    @Override
    public int setContentView() {
        return R.layout.fragment_simple;
    }

    @Override
    protected void init() {
        super.init();

        binding.btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.tvContent.setText("文字已改变：" + (int)(Math.random()*1000));
            }
        });

    }


}
