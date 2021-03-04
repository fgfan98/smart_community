package com.gigsider.controller;

import com.gigsider.service.UserService;
import com.gigsider.utils.SessionPool;
import com.gigsider.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public UserVO login(String user_name, String passwd, HttpSession httpSession){
        UserVO userVO = userService.userLogin(user_name, passwd);

        if (userVO.getUser() != null &&
                SessionPool.getExistSession(userVO.getUser().getId_num()) == null){
            SessionPool.sessions.put(userVO.getUser().getId_num(),httpSession);
            httpSession.setAttribute("id",userVO.getUser().getId_num());
            httpSession.setAttribute("token","success");
            httpSession.setAttribute("identity","user");
            httpSession.setMaxInactiveInterval(5*60*1000);
        }else if (userVO.getUser() != null){
            System.out.println("账号已登录");
            userVO.setMsg("warning");
        }else
            httpSession.invalidate();

        return userVO;
    }

}
