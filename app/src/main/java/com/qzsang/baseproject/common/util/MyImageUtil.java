package com.qzsang.baseproject.common.util;

import android.content.Context;
import android.widget.ImageView;

import com.qzsang.baselibrary.base.BBaseActivity;
import com.qzsang.baselibrary.base.BBaseFragment;
import com.qzsang.baselibrary.util.image.GlideApp;
import com.qzsang.baselibrary.util.image.ImageUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.common.base.BaseFragment;

/**
 * Created by quezhongsang on 2017/10/16.
 */

public class MyImageUtil{


    public static void init() {

        new ImageUtil.Builder()
                .setPlaceholderResId(R.mipmap.ic_launcher_round)
                .setErrorResId(R.mipmap.ic_launcher_round)
                .init();

    }

//    public static void show (Object source, ImageView imageView) {
//        ImageUtil.show(source, imageView);
//    }

    public static ImageUtil.Request with(BaseActivity activity) {
        return ImageUtil.with(activity);
    }

    public static ImageUtil.Request with(BaseFragment fragment) {
        return ImageUtil.with(fragment);
    }

    public static ImageUtil.Request with(Context context) {
        return ImageUtil.with(context);
    }
}
