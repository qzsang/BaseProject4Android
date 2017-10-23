package com.qzsang.baselibrary.util.view;

import android.support.annotation.LayoutRes;
import android.view.ViewGroup;

import com.qzsang.baselibrary.base.BRViewHolder;
import com.qzsang.baselibrary.base.BViewHolder;

/**
 * Created by quezhongsang on 2017/10/19.
 *
 */

public class ViewHolderUtil {



    // for BViewHolder  S

    public static BViewHolder inflate(@LayoutRes int resource) {

        return  new BViewHolder(resource);
    }

    public static BViewHolder inflateAndSetTag(@LayoutRes int resource) {
        BViewHolder viewHolder = new BViewHolder(resource);
        viewHolder.binding.getRoot().setTag(viewHolder);
        return viewHolder;
    }

    // for BViewHolder  E



    // for BRViewHolder  S
    public static BRViewHolder inflate4RV(@LayoutRes int resource) {

        return  inflate4RV(resource, null);
    }

    public static BRViewHolder inflate4RV(@LayoutRes int resource, ViewGroup viewGroup) {

        return  new BRViewHolder<>(ViewUtil.inflate( resource, viewGroup));
    }
    // for BRViewHolder  E



}
