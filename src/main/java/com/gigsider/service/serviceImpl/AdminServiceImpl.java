package com.gigsider.service.serviceImpl;

import com.gigsider.dao.AdminDao;
import com.gigsider.po.Admin;
import com.gigsider.service.AdminService;
import com.gigsider.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;


    @Override
    public AdminVO adminLogin(String job_num, String passwd) {

        AdminVO adminVO = new AdminVO();
        List<Admin> result = adminDao.queryAdminByJobNum(job_num);

        if (result.size() == 0){
            adminVO.setMsg("管理员账号不存在！");
            adminVO.setAdmin(null);
        }else {
            Admin admin = (Admin)result.get(0);
            if (passwd.equals(admin.getPasswd())){
                adminVO.setAdmin(admin);
                adminVO.setMsg("success");
            }else {
                adminVO.setAdmin(null);
                adminVO.setMsg("管理员密码错误！");
            }
        }
        return adminVO;

    }
}
