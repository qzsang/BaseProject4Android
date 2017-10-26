package com.qzsang.baseproject.face.simple;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.qzsang.baselibrary.util.image.ImageUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.common.util.MyImageUtil;
import com.qzsang.baseproject.databinding.ActivityBannerBinding;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends BaseActivity<ActivityBannerBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
    }

    @Override
    protected void init() {
        super.init();

        List<String> list = new ArrayList<>();
        list.add("http://i.imgur.com/DvpvklR.png");
        list.add("http://i.imgur.com/DvpvklR.png");
        list.add("http://i.imgur.com/DvpvklR.png");

        binding.banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                MyImageUtil.with(context)
                        .load(o)
                        .into(imageView);

            }
        });
        binding.banner.setImages(list);

        binding.banner.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.banner.startAutoPlay();
    }


    @Override
    protected void onStop() {
        super.onStop();
        binding.banner.stopAutoPlay();
    }
}
