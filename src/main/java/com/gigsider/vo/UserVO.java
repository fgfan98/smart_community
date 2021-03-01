package com.gigsider.vo;

import com.gigsider.po.User;
import org.springframework.stereotype.Service;

@Service
public class UserVO {

    private User user;
    private String msg;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
