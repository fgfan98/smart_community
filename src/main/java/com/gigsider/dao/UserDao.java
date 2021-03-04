package com.gigsider.dao;

import com.gigsider.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDao {

    //添加用户
    public boolean insertUser(User user);

    //通过主键 id 删除用户
    public boolean deleteUser(@Param("id")int id);

    //修改用户信息
    public boolean updateUser(User user);

    //通过主键 id 查找用户
    public List<User> queryUserById(@Param("id")int id);

    //通过身份证号 id_num 查找用户
    public List<User> queryUserByIdNum(@Param("id_num")String id_num);

    //通过用户名 user_name 查找用户
    public List<User> queryUserByUserName(@Param("user_name")String user_name);

    //获取所有用户
    public List<User> queryAllUser();

    //分页查询
    public List<User> queryUserPage(Map<String, Object> data);

}
