package com.qzsang.baseproject.face.simple;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luck.picture.lib.entity.LocalMedia;
import com.qzsang.baselibrary.util.image.ImageUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baselibrary.util.ImgSelUtil;
import com.qzsang.baseproject.common.util.MyImageUtil;
import com.qzsang.baseproject.databinding.ActivitySimpleImageSelBinding;

import java.util.List;

public class SimpleImageSelActivity extends BaseActivity<ActivitySimpleImageSelBinding> {

    ImgSelUtil imgSelUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_image_sel);
    }

    @Override
    protected void init() {
        super.init();
    }

    public void onClick1 (View view) {
        imgSelUtil = new ImgSelUtil();
        imgSelUtil.startActivity(mContext, 1, new ImgSelUtil.CallBack() {
            @Override
            public void onCallBack(List<String> pathList) {
                showImages(pathList);
            }
        });

    }


    public void onClick3 (View view) {
        imgSelUtil = new ImgSelUtil();
        imgSelUtil.startActivity(mContext, 3, new ImgSelUtil.CallBack() {
            @Override
            public void onCallBack(List<String> pathList) {
                showImages(pathList);

            }
        });

    }

    public void showImages (List<String> pathList) {
        binding.tvTxt.setText("");
        binding.llImgs.removeAllViews();
        for (String path : pathList) {
            binding.tvTxt.append(path + "\n");
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,400));
            ImageUtil
                    .with(mContext)
                    .load(path)
                    .into(imageView);
            binding.llImgs.addView(imageView);

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (imgSelUtil != null) {
            imgSelUtil.onActivityResult(requestCode, resultCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImgSelUtil.deleteCacheDirFile();
    }
}
