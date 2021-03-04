package com.gigsider.service;

import com.gigsider.vo.AdminVO;

public interface AdminService {

    //管理员登陆
    public AdminVO adminLogin(String job_num, String passwd);

}
