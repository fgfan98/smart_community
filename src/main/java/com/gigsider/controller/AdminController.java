package com.gigsider.controller;

import com.alibaba.fastjson.JSONObject;
import com.gigsider.po.User;
import com.gigsider.service.AdminService;
import com.gigsider.service.UserService;
import com.gigsider.utils.SessionPool;
import com.gigsider.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    public AdminVO login(String job_num, String passwd, HttpSession httpSession){
        AdminVO adminVO = adminService.adminLogin(job_num, passwd);

        if (adminVO.getAdmin() != null &&
                SessionPool.getExistSession(adminVO.getAdmin().getJob_num()) == null){
            httpSession.setAttribute("id", adminVO.getAdmin().getJob_num());
            httpSession.setAttribute("token", "success");
            httpSession.setAttribute("identity", "admin");
            httpSession.setMaxInactiveInterval(5*60*1000);
            SessionPool.addToSessionPool(adminVO.getAdmin().getJob_num(), httpSession);
        }else if (adminVO.getAdmin() != null){
            System.out.println("管理员账号已被登录");
            adminVO.setMsg("warning");
        }else
            httpSession.invalidate();

        return adminVO;
    }

    @RequestMapping("/exit.do")
    @ResponseBody
    public void exit(String job_num) {
        SessionPool.destroyExistSession(job_num);
    }

    @RequestMapping("/getAllUser.do")
    @ResponseBody
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @RequestMapping("/getUserPage.do")
    @ResponseBody
    public Map<String, Object> getUserPage(int page, int limit) {
        //获取全部学生信息
        List<User> users = userService.getAllUser();
        //获取分页后的每页学生信息
        List<User> user = userService.getUserPage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", users.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", user);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getUserNamePage.do")
    @ResponseBody
    public Map<String, Object> getUserNamePage(int page, int limit, String real_name) {
        //获取全部学生信息
        List<User> users = userService.getUserByName(real_name);
        //获取分页后的每页学生信息
        List<User> user = userService.getUserNamePage(page, limit, real_name);

        Map<String,Object> tableData =new HashMap<String,Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", users.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", user);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/addUser.do")
    @ResponseBody
    public boolean addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping("/delUsers.do")
    @ResponseBody
    public boolean delUsers(String users) {
        List<User> data = new ArrayList<>();
        data = JSONObject.parseArray(users,User.class);
        for (int i = 0; i < data.size(); i++){
            if(!userService.delUser(data.get(i).getId()))
                return false;
        }
        return true;
    }

    @RequestMapping("/delUser.do")
    @ResponseBody
    public boolean delUser(int id) {
        return userService.delUser(id);
    }

    @RequestMapping("/getUserById.do")
    @ResponseBody
    public User getUserById(int id) {
        List<User> list = userService.getUserById(id);
        User user = null;
        if(list.size() > 0) {
            user = list.get(0);
        }
        return user;
    }

}
