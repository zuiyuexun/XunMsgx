package com.xunyue.xunmsg.xunmsg.bean;

import com.xunyue.xunmsg.xunmsg.bean.CommonBean;

/**
 * Created by Administrator on 2016/12/1.
 */
public class Person extends CommonBean {
    private String name;
    private String address;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
