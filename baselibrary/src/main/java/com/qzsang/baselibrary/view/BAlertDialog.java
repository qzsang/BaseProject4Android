package com.qzsang.baselibrary.view;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;

import com.qzsang.baselibrary.util.AppUtil;

/**
 * Created by quezhongsang on 2017/10/23.
 */

public class BAlertDialog implements DialogInterface{
    AlertDialog alertDialog;

    public BAlertDialog(AlertDialog alertDialog) {
        this.alertDialog = alertDialog;
    }

    public static class Builder {
        android.support.v7.app.AlertDialog.Builder realBuidler ;
        public Builder(Context context) {
            realBuidler = new android.support.v7.app.AlertDialog.Builder(context);
        }



        public Builder setTitle(@Nullable CharSequence title) {
            realBuidler.setTitle(title);
            return this;
        }

        public Builder setMessage(@Nullable CharSequence message) {
            realBuidler.setMessage(message);
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            realBuidler.setNegativeButton(text, listener);
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            realBuidler.setPositiveButton(text, listener);
            return this;
        }

        public Builder setNeutralButton(CharSequence text, final DialogInterface.OnClickListener listener) {
            realBuidler.setNeutralButton(text, listener);
            return this;
        }


        public Builder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
            realBuidler.setOnDismissListener(onDismissListener);
            return this;
        }

        public BAlertDialog show() {
            return new BAlertDialog(realBuidler.show());
        }
    }

    public void show() {
        if (alertDialog != null)
            alertDialog.show();


    }


    @Override
    public void cancel() {
        if (alertDialog != null)
            alertDialog.cancel();
    }
    @Override
    public void dismiss() {
        if (alertDialog != null)
            alertDialog.dismiss();
    }



}
