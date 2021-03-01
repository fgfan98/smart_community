package com.gigsider.service.serviceImpl;

import com.gigsider.dao.UserDao;
import com.gigsider.po.User;
import com.gigsider.service.UserService;
import com.gigsider.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
