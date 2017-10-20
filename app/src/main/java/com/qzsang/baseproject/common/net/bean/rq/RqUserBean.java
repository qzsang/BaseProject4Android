package com.qzsang.baseproject.common.net.bean.rq;

import com.qzsang.baseproject.common.base.BaseRqBean;

/**
 * Created by quezhongsang on 2017/10/14.
 */

public class RqUserBean extends BaseRqBean {
    public String username;
    public String pwd;

    @Override
    public String toString() {
        return "RqUserBean{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
