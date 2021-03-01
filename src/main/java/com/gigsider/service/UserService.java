package com.gigsider.service;

import com.gigsider.vo.UserVO;

public interface UserService {

    //用户登陆
    public UserVO userLogin(String user_name, String passwd);

}
