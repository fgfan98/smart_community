package com.gigsider.dao;

import com.gigsider.po.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {

    //添加管理员
    public boolean insertAdmin(Admin admin);

    //通过主键 id 删除管理员
    public boolean deleteAdmin(@Param("id")int id);

    //修改管理员信息
    public boolean updateAdmin(Admin admin);

    //通过主键 id 查找管理员
    public List<Admin> queryAdminById(@Param("id")int id);

    //通过身份证号 id_num 查找管理员
    public List<Admin> queryAdminByIdNum(@Param("id_num")String id_num);

    //通过工号 job_num 查找管理员
    public List<Admin> queryAdminByJobNum(@Param("job_num")String job_num);

    //获取所有管理员
    public List<Admin> queryAllAdmin();

}
