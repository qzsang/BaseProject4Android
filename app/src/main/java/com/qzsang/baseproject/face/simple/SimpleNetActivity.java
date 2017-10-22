package com.qzsang.baseproject.face.simple;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;

import com.qzsang.baselibrary.util.FileUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.common.rx.SubscribeTransformer;
import com.qzsang.baseproject.common.rx.NetSubscriber;
import com.qzsang.baseproject.common.bean.rp.RpUserBean;
import com.qzsang.baseproject.common.bean.rq.RqUserBean;
import com.qzsang.baseproject.common.net.service.SimpleService;
import com.qzsang.baseproject.common.util.net.MyNetUtil;
import com.qzsang.baseproject.databinding.ActivitySimpleNetBinding;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SimpleNetActivity extends BaseActivity<ActivitySimpleNetBinding> {


    SimpleService simpleService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_net);
    }


    @Override
    protected void init() {
        super.init();
        simpleService = MyNetUtil.create(SimpleService.class);

        final RqUserBean rqUserBean = new RqUserBean();
        rqUserBean.username = "baseProject";
        rqUserBean.pwd = "123456";

        binding.tvPostField.setText("post field " + rqUserBean.username + " " + rqUserBean.pwd);
        binding.tvPostBody.setText("post body " + rqUserBean);


        binding.btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleService.getuser(rqUserBean.username, rqUserBean.pwd)
                        .compose(new SubscribeTransformer<RpUserBean>(mContext, new NetSubscriber<RpUserBean>() {
                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }

                            @Override
                            public void onNext(RpUserBean response) {
                                super.onNext(response);
                                binding.tvPostField.setText("post field response:" + response);
                            }
                        }));


                simpleService.getUser(rqUserBean)
                        .compose(new SubscribeTransformer<RpUserBean>(mContext, new NetSubscriber<RpUserBean>() {

                            @Override
                            public void onNext(RpUserBean response) {
                                super.onNext(response);
                                binding.tvPostBody.setText("post body response:" + response);
                            }

                            @Override
                            public void onError(Throwable e) {
                                super.onError(e);
                            }

                        }));




            }
        });


        binding.btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleService.download("file/sogo.dmg")
                        .observeOn(Schedulers.io())
                        .map(new Func1<ResponseBody, Boolean>() {
                            @Override
                            public Boolean call(ResponseBody responseBody) {
                                File file = new File(getExternalCacheDir(), "sogo.dmg");
                                return FileUtil.writeResponseBodyToDisk(responseBody, file);
                            }
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(new SubscribeTransformer<Boolean>(mContext, new NetSubscriber<Boolean>() {
                            @Override
                            public void onNext(Boolean response) {
                                super.onNext(response);
                                toast("result:" + response);
                            }
                        }));
//
            }
        });

        binding.btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AndPermission.with(mContext)
                        .requestCode(100)
                        .permission(Permission.STORAGE)
                        .callback(new PermissionListener() {
                            @Override
                            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                                if (requestCode == 100) {
                                    Intent intent = new Intent(
                                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                                    startActivityForResult(intent, 1);
                                }
                            }

                            @Override
                            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

                            }
                        })
                        .start();



            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {

                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();

                    File file = new File(picturePath);
                    RequestBody requestFile =
                            RequestBody.create(MediaType.parse("application/otcet-stream"), file);

                    MultipartBody.Part body =
                            MultipartBody.Part.createFormData("file", file.getName(), requestFile);

                    simpleService.upload(body)
                            .compose(new SubscribeTransformer<String>(mContext, new NetSubscriber<String>() {
                                @Override
                                public void onNext(String response) {
                                    super.onNext(response);
                                    toast("上传成功");
                                }
                            }));
//


                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
