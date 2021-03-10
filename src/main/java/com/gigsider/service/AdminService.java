package com.gigsider.service;

import com.gigsider.po.Admin;
import com.gigsider.vo.AdminVO;

import java.util.List;

public interface AdminService {

    //管理员登陆
    public AdminVO adminLogin(String job_num, String passwd);

    //根据 job_num 查找管理员信息
    public List<Admin> getAdminByJobNum(String job_num);

    //更新管理员信息
    public boolean upAdmin(Admin admin);

}
