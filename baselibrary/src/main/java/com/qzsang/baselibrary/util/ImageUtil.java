package com.qzsang.baselibrary.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * Created by quezhongsang on 2017/10/16.
 */

public class ImageUtil {

    private static int placeholderResId;
    private static int errorResId;

    public static void init (Builder builder) {
        ImageUtil.placeholderResId = builder.placeholderResId;
        ImageUtil.errorResId = builder.errorResId;

    }

    /**
     *  统一管理
     * @param source
     * @param imageView
     */
    public static void show (Object source, ImageView imageView) {
        if (source == null || imageView == null)
            return;

        Picasso picasso = Picasso.with(AppUtil.getApplication());

        RequestCreator requestCreator = null;
        if (source instanceof String) {
            requestCreator = picasso.load((String) source);
        } else if (source instanceof File){
            requestCreator = picasso.load((File) source);
        } else if (source instanceof Integer) {
            requestCreator = picasso.load((Integer) source);
        } else if (source instanceof Uri) {
            requestCreator = picasso.load((Uri) source);
        }

        if (requestCreator != null) {
            requestCreator
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .into(imageView);
        }


    }



    public static class Builder {
        int placeholderResId;
        int errorResId;

        public Builder() {

        }

        public Builder setPlaceholderResId(int placeholderResId) {
            this.placeholderResId = placeholderResId;
            return this;
        }

        public Builder setErrorResId(int errorResId) {
            this.errorResId = errorResId;
            return this;
        }


        public void init () {
            ImageUtil.init(this);
        }


    }


}
