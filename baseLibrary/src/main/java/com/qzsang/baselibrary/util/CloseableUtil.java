package com.qzsang.baselibrary.util;

import java.io.Closeable;

/**
 * Created by quezhongsang on 2017/10/17.
 */

public class CloseableUtil {

    public static void close (Closeable... closeables) {

        if (closeables == null)
            return;

        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

    }

}
