package com.gigsider.vo;

import com.gigsider.po.Admin;
import org.springframework.stereotype.Service;

@Service
public class AdminVO {

    private Admin admin;
    private String msg;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
