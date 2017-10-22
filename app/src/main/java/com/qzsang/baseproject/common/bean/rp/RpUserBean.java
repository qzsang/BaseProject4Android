package com.qzsang.baseproject.common.bean.rp;

import com.qzsang.baseproject.common.base.BaseRpBean;

/**
 * Created by quezhongsang on 2017/10/19.
 */

public class RpUserBean extends BaseRpBean{
    public String username;
    public String pwd;


    @Override
    public String toString() {
        return "RpUserBean{" +
                "username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
