package com.qzsang.baselibrary.util.image;

import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.qzsang.baselibrary.base.BBaseActivity;
import com.qzsang.baselibrary.base.BBaseFragment;

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


    public static Request with(BBaseActivity activity) {
        return new Request(GlideApp.with(activity));
    }

    public static Request with(BBaseFragment fragment) {
        return new Request(GlideApp.with(fragment));
    }

    public static Request with(Context context) {
        return new Request(GlideApp.with(context));
    }

    /**
     * 为了统一管理
     */
    public static class Request {
        GlideRequests glideRequests;
        GlideRequest glideRequest;

        public Request(GlideRequests glideRequests) {
            this.glideRequests = glideRequests;
        }

        public Request load(Object source) {
            glideRequest = glideRequests
                    .load(source)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(placeholderResId)
                    .error(errorResId)
                    .fitCenter();
            return this;
        }

        public Request asBitmap() {
            glideRequests.asBitmap();
            return this;
        }

        public void into(ImageView view) {
            if (glideRequest != null && view != null) {
                glideRequest.into(view);
            }
        }

        public void into(ImageTarget imageTarget) {
            if (glideRequest != null && imageTarget != null) {
                glideRequest.into(imageTarget);
            }
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


    public abstract static class ImageTarget<Z> extends SimpleTarget<Z>{

    }


}
