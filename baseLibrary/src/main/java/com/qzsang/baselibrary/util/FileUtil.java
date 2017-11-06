package com.qzsang.baselibrary.util;


import android.database.Cursor;
import android.net.Uri;

import com.qzsang.baselibrary.util.CloseableUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by quezhongsang on 2017/10/17.
 */

public class FileUtil {

    public static boolean writeResponseBodyToDisk(ResponseBody body, File file) {

        if (file == null)
            return false;

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            byte[] fileReader = new byte[4096];

            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            inputStream = body.byteStream();
            outputStream = new FileOutputStream(file);

            while (true) {
                int read = inputStream.read(fileReader);

                if (read == -1) {
                    break;
                }

                outputStream.write(fileReader, 0, read);

                fileSizeDownloaded += read;

                LogUtil.d("FileUtil writeResponseBodyToDisk", "file download: " + fileSizeDownloaded + " of " + fileSize);
            }

            outputStream.flush();

            return true;
        } catch (IOException e) {
            return false;
        } finally {
            CloseableUtil.close(inputStream, outputStream);

        }

    }


}
