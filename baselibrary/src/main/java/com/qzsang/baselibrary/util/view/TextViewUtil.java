package com.qzsang.baselibrary.util.view;

import android.widget.TextView;

import com.qzsang.baselibrary.util.StringUtil;

/**
 * Created by qzsang on 2017/9/24.
 */

public class TextViewUtil {

    public static void setText (TextView textView, String string) {
        if (textView == null)
            return;
        textView.setText(StringUtil.isEmpty(string) ? "" : string);

    }

}
