package com.gigsider.service;

import com.gigsider.po.User;
import com.gigsider.vo.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

    //用户登陆
    public UserVO userLogin(String user_name, String passwd);

    //获取所有用户信息
    public List<User> getAllUser();

    //获取用户分页信息
    public List<User> getUserPage(int page, int limit);

    //通过 id 查找用户
    public List<User> getUserById(int id);

    //通过姓名查找用户
    public List<User> getUserByName(String real_name);

    //通过用户名查找用户
    public List<User> getUserByUserName(String user_name);

    //获取用户姓名查找分页信息
    public List<User> getUserNamePage(int page, int limit, String real_name);

    //通过 id_num 查找用户
    public List<User> getUserByIdNum(String id_num);

    //添加用户
    public boolean addUser(User user);

    //删除用户
    public boolean delUser(int id);

    //更新用户信息
    public boolean upUser(User user);

    //更新用户住宅编号信息
    public boolean upUserHouse(String old_house, String new_house);

    //通过 license_num 查找用户
    public List<User> getUserByLicenseNum(String license_num);

    //更新权限
    public boolean upUserAuthority(int authority, int id);

    //获取所有已激活的用户
    public List<User> getActivatedUser();

    //已激活的用户分页
    public List<User> getActivatedUserPage(int page, int limit);

    //在已激活的用户中通过姓名查找
    public List<User> getActivatedUserByName(String real_name);

    //已激活用户姓名查找分页
    public List<User> getActivatedUserNamePage(int page, int limit, String real_name);

}
