package com.qzsang.baseproject.face.simple;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qzsang.baselibrary.util.LogUtil;
import com.qzsang.baselibrary.view.BAlertDialog;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.databinding.ActivitySimpleDialogBinding;

public class SimpleDialogActivity extends BaseActivity<ActivitySimpleDialogBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dialog);


    }



    public void onAlertDialogClick (View view) {
        new BAlertDialog.Builder(mContext)
                .setTitle("我是一个标题")
                .setMessage("我是一个内容我是一个内容我是一个内容我是一个内容我是一个内容我是一个内容我是一个内容")
                .setPositiveButton("positive", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("点了" + "positive");
                    }
                })
                .setNegativeButton("negative", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("点了" + "negative");
                    }
                })
                .setNeutralButton("neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toast("点了" + "neutral");
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        LogUtil.e(BAlertDialog.class, "onDismiss");
                    }
                })
                .show();
    }



}
