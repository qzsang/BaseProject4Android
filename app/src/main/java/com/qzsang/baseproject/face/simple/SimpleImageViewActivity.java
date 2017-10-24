package com.qzsang.baseproject.face.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.common.util.MyImageUtil;
import com.qzsang.baseproject.databinding.ActivitySimpleImageViewBinding;

public class SimpleImageViewActivity extends BaseActivity<ActivitySimpleImageViewBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_image_view);
    }


    @Override
    protected void init() {
        super.init();

        binding.btnShowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyImageUtil.with(mContext)
                        .load("http://i.imgur.com/DvpvklR.png")
                        .into(binding.ivNetImage);
                MyImageUtil.with(mContext)
                        .load(R.mipmap.ic_launcher)
                        .into(binding.ivLocalImage);

            }
        });
    }
}
