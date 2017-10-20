package com.qzsang.baselibrary.util.view;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qzsang.baselibrary.base.BPRViewHolder;
import com.qzsang.baselibrary.base.BPViewHolder;

/**
 * Created by quezhongsang on 2017/10/19.
 *
 */

public class ViewHolderUtil {



    // for BPViewHolder  S

    public static BPViewHolder inflate(@LayoutRes int resource) {

        return  new BPViewHolder(resource);
    }

    public static BPViewHolder inflateAndSetTag(@LayoutRes int resource) {
        BPViewHolder viewHolder = new BPViewHolder(resource);
        viewHolder.binding.getRoot().setTag(viewHolder);
        return viewHolder;
    }

    // for BPViewHolder  E



    // for BPRViewHolder  S
    public static BPRViewHolder inflate4RV(@LayoutRes int resource) {

        return  inflate4RV(resource, null);
    }

    public static BPRViewHolder inflate4RV(@LayoutRes int resource, ViewGroup viewGroup) {

        return  new BPRViewHolder<>(ViewUtil.inflate( resource, viewGroup));
    }
    // for BPRViewHolder  E



}
