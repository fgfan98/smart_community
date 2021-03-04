package com.gigsider.service;

import com.gigsider.po.User;
import com.gigsider.vo.UserVO;

import java.util.List;

public interface UserService {

    //用户登陆
    public UserVO userLogin(String user_name, String passwd);

    //获取所有用户信息
    public List<User> getAllUser();

    //获取用户分页信息
    public List<User> getUserPage(int page, int limit);

}
