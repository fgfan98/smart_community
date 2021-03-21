package com.gigsider.service.serviceImpl;

import com.gigsider.dao.UserDao;
import com.gigsider.po.User;
import com.gigsider.service.UserService;
import com.gigsider.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserVO userLogin(String user_name, String passwd) {

        UserVO userVO = new UserVO();
        List<User> result = userDao.queryUserByUserName(user_name);

        if (result.size() == 0){
            userVO.setMsg("用户不存在！");
            userVO.setUser(null);
        }else {
            User user = (User)result.get(0);
            if (passwd.equals(user.getPasswd())){
                userVO.setUser(user);
                userVO.setMsg("success");
            }else {
                userVO.setUser(null);
                userVO.setMsg("密码错误！");
            }
        }
        return userVO;

    }

    @Override
    public List<User> getAllUser() {
        return userDao.queryAllUser();
    }

    @Override
    public List<User> getUserByName(String real_name) {
        return userDao.queryUserByName(real_name);
    }

    @Override
    public List<User> getUserByUserName(String user_name) {
        return userDao.queryUserByUserName(user_name);
    }

    @Override
    public List<User> getUserByIdNum(String id_num) {
        return userDao.queryUserByIdNum(id_num);
    }

    @Override
    public List<User> getUserNamePage(int page, int limit, String real_name) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit",limit);
        data.put("real_name",real_name);
        return userDao.queryUserNamePage(data);
    }

    @Override
    public List<User> getUserPage(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit",limit);
        return userDao.queryUserPage(data);
    }

    @Override
    public boolean addUser(User user) {
        return userDao.insertUser(user);
    }

    @Override
    public boolean upUserHouse(String old_house, String new_house) {
        Map<String, Object> data = new HashMap<>();
        data.put("old_house", old_house);
        data.put("new_house", new_house);
        return userDao.updateUserHouse(data);
    }

    @Override
    public boolean delUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    public List<User> getUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public boolean upUser(User user) {
        return userDao.updateUser(user);
    }
}
