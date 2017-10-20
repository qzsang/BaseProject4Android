package com.qzsang.baseproject.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.qzsang.baselibrary.util.ImageUtil;
import com.qzsang.baseproject.R;

/**
 * Created by quezhongsang on 2017/10/16.
 */

public class MyImageUtil {


    public static void init() {

        new ImageUtil.Builder()
                .setPlaceholderResId(R.mipmap.ic_launcher_round)
                .setErrorResId(R.mipmap.ic_launcher_round)
                .init();

    }

    public static void show (Object source, ImageView imageView) {
        ImageUtil.show(source, imageView);
    }
}
