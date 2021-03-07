package com.gigsider.controller;

import com.alibaba.fastjson.JSONObject;
import com.gigsider.po.House;
import com.gigsider.po.User;
import com.gigsider.service.AdminService;
import com.gigsider.service.HouseService;
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
    @Autowired
    private HouseService houseService;

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
        List<User> data = JSONObject.parseArray(users,User.class);
        for (int i = 0; i < data.size(); i++){
            int id = data.get(i).getId();

            List<User> list = userService.getUserById(id);
            User user = new User();
            if (list.size() != 0)
                user = list.get(0);

            if (!user.getHouse().equals("") || !user.getHouse().equals(null)) {
                House house = houseService.getHouseByHouseId(user.getHouse()).get(0);
                house.setSale(0);
                houseService.upHouse(house);
            }

            if(!userService.delUser(id))
                return false;
        }
        return true;
    }

    @RequestMapping("/delUser.do")
    @ResponseBody
    public boolean delUser(int id) {
        List<User> list = userService.getUserById(id);
        User user = new User();
        if (list.size() != 0)
            user = list.get(0);

        if (!user.getHouse().equals("") || !user.getHouse().equals(null)) {
            House house = houseService.getHouseByHouseId(user.getHouse()).get(0);
            house.setSale(0);
            houseService.upHouse(house);
        }

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

    @RequestMapping("/getHouseOpts.do")
    @ResponseBody
    public List<House> getHouseOpts() {
        return houseService.getAllHouse();
    }

    @RequestMapping("/upUser.do")
    @ResponseBody
    public boolean upUser(User user) {
        List<House> house1 = houseService.getHouseByHouseId(user.getHouse_id());
        List<House> house2 = houseService.getHouseByHouseId(user.getHouse());

        if (house1.size() != 0) {
            House house = house1.get(0);
            house.setSale(0);
            if (!houseService.upHouse(house))
                return false;
        }

        if (house2.size() != 0) {
            House house = house2.get(0);
            house.setSale(1);
            if (!houseService.upHouse(house))
                return false;
        }

        if (!userService.upUser(user)) {
            return false;
        }

        return true;
    }

    @RequestMapping("/addHouse.do")
    @ResponseBody
    public boolean addHouse(House house) {
        return houseService.addHouse(house);
    }

    @RequestMapping("/getHousePage.do")
    @ResponseBody
    public Map<String, Object> getHousePage(int page, int limit) {
        //获取全部住宅信息
        List<House> houses = houseService.getAllHouse();
        //获取分页后的每页住宅信息
        List<House> house = houseService.getHousePage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", houses.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", house);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getHouseIdPage.do")
    @ResponseBody
    public Map<String, Object> getHouseIdPage(int page, int limit, String house_id) {
        //获取全部住宅信息
        List<House> houses = houseService.getHouseByHouseId(house_id);
        //获取分页后的每页住宅信息
        List<House> house = houseService.getHouseIdPage(page, limit, house_id);

        Map<String,Object> tableData =new HashMap<String,Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", houses.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", house);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/delHouses.do")
    @ResponseBody
    public boolean delHouses(String houses) {
        List<House> data = JSONObject.parseArray(houses,House.class);
        for (int i = 0; i < data.size(); i++){
            String house_id = data.get(i).getHouse_id();
            userService.upUserHouse(house_id,"");
            if(!houseService.delHouse(house_id))
                return false;
        }
        return true;
    }

    @RequestMapping("/delHouse.do")
    @ResponseBody
    public boolean delHouse(String house_id) {
        userService.upUserHouse(house_id,"");
        return houseService.delHouse(house_id);
    }

    @RequestMapping("/getHouseByHouseId.do")
    @ResponseBody
    public House getHouseByHouseId(String house_id) {
        List<House> list = houseService.getHouseByHouseId(house_id);
        House house = null;
        if(list.size() > 0) {
            house = list.get(0);
        }
        return house;
    }

    @RequestMapping("/upHouse.do")
    @ResponseBody
    public boolean upHouse(House house) {
        return houseService.upHouse(house);
    }

}
