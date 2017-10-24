package com.qzsang.baselibrary.util;

import android.app.Activity;
import android.content.Intent;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.qzsang.baselibrary.R;

import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by quezhongsang on 2017/10/23.
 * 图片选择
 *
 */

public class ImgSelUtil {

    private CallBack callBack;


    public static void init() {

    }

    public ImgSelUtil startActivity (Activity activity,int maxNum, CallBack callBack) {
        this.callBack = callBack;
        // 进入相册 以下是例子：用不到的api可以不写
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()
                .theme(R.style.my_picture_sel_tyle)//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(maxNum)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(maxNum > 1 ? PictureConfig.MULTIPLE : PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
                .previewVideo(false)// 是否可预览视频 true or false
                .enablePreviewAudio(false) // 是否可播放音频 true or false
//                .compressGrade()// luban压缩档次，默认3档 Luban.THIRD_GEAR、Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮 true or false
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .compressMode(PictureConfig.LUBAN_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .withAspectRatio(1, 1)// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
                .isGif(false)// 是否显示gif图片 true or false
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(true)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
//                .compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效 int
//                .compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效  int
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
        return this;
    }



    public ImgSelUtil onActivityResult(int requestCode, int resultCode, Intent data) {
        // 图片选择结果回调
        if (requestCode == PictureConfig.CHOOSE_REQUEST && resultCode == RESULT_OK && data != null) {
            List<LocalMedia> pathList = PictureSelector.obtainMultipleResult(data);
            if (callBack != null && pathList != null && pathList.size() != 0) {

                callBack.onCallBack(pathList);
            }
        }

        return this;
    }


    public static String getPath (LocalMedia localMedia) {
        if (localMedia == null)
            return "";
        if (localMedia.isCompressed())
            return localMedia.getCompressPath();
        if (localMedia.isCut())
            return localMedia.getCutPath();
        return localMedia.getPath();
    }

    public static void deleteCacheDirFile () {
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(AppUtil.getApplication());
    }

    public interface CallBack {
        /**
         *  // 1.media.getPath(); 为原图path
         // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
         // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
         // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

         * @param pathList
         */
        void onCallBack (List<LocalMedia> pathList) ;
    }



}
