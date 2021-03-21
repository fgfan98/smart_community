package com.gigsider.controller;

import com.alibaba.fastjson.JSONObject;
import com.gigsider.po.*;
import com.gigsider.service.*;
import com.gigsider.utils.SessionPool;
import com.gigsider.vo.AdminVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private BulletinService bulletinService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private ComunitiService comunitiService;
    @Autowired
    private ReplyService replyService;

    @RequestMapping("/login.do")
    @ResponseBody
    public AdminVO login(String job_num, String passwd, HttpSession httpSession){
        AdminVO adminVO = adminService.adminLogin(job_num, passwd);

        if (adminVO.getAdmin() != null &&
                SessionPool.getExistSession(adminVO.getAdmin().getJob_num()) == null){
            httpSession.setAttribute("id", adminVO.getAdmin().getJob_num());
            httpSession.setAttribute("login_status", "success");
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

    @RequestMapping("/getAdminByJobNum.do")
    @ResponseBody
    public Admin getAdminByJobNum(String job_num) {
        List<Admin> list = adminService.getAdminByJobNum(job_num);
        Admin admin = null;
        if (list.size() > 0)
            admin = list.get(0);
        return admin;
    }

    @RequestMapping("/upAdmin.do")
    @ResponseBody
    public boolean upAdmin(Admin admin) {
        return adminService.upAdmin(admin);
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
            List<Parking> parkings = new ArrayList<>();
            User user = new User();
            if (list.size() != 0) {
                user = list.get(0);
                parkings = parkingService.getParkingByLicenseNum(user.getLicense_num());
            }

            //将该业主的车位置为未售出状态
            if(parkings.size() != 0) {
                Parking parking = parkings.get(0);
                parking.setStatus(0);
                parking.setLicense_num(null);
                parkingService.upParking(parking);
            }

            //将该业主的住宅置为未售出状态
            if (!user.getHouse().equals("") && user.getHouse() != null) {
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
        List<Parking> parkings = new ArrayList<>();
        User user = new User();
        if (list.size() != 0) {
            user = list.get(0);
            parkings = parkingService.getParkingByLicenseNum(user.getLicense_num());
        }

        //将该业主的车位置为未售出状态
        if(parkings.size() != 0) {
            Parking parking = parkings.get(0);
            parking.setStatus(0);
            parking.setLicense_num(null);
            parkingService.upParking(parking);
        }

        //将该业主的住宅置为未售出状态
        if (!user.getHouse().equals("") && user.getHouse() != null) {
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
            userService.upUserHouse(house_id,null);
            if(!houseService.delHouse(house_id))
                return false;
        }
        return true;
    }

    @RequestMapping("/delHouse.do")
    @ResponseBody
    public boolean delHouse(String house_id) {
        userService.upUserHouse(house_id,null);
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

    @RequestMapping("/getParkingPage.do")
    @ResponseBody
    public Map<String, Object> getParkingPage(int page, int limit) {
        //获取全部车位信息
        List<Parking> parkings = parkingService.getAllParking();
        //获取分页后的每页车位信息
        List<Parking> parking = parkingService.getParkingPage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", parkings.size());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", parking);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getParkingIdPage.do")
    @ResponseBody
    public Map<String, Object> getParkingIdPage(int page, int limit, String parking_id) {
        List<Parking> parkings = parkingService.getParkingByParkingId(parking_id);
        List<Parking> parking = parkingService.getParkingIdPage(page, limit, parking_id);

        Map<String,Object> tableData =new HashMap<String,Object>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", parkings.size());
        tableData.put("data", parking);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getParkingByParkingId.do")
    @ResponseBody
    public Parking getParkingByParkingId(String parking_id) {
        List<Parking> list = parkingService.getParkingByParkingId(parking_id);
        Parking parking = null;
        if(list.size() > 0) {
            parking = list.get(0);
        }
        return parking;
    }

    @RequestMapping("/upParking.do")
    @ResponseBody
    public boolean upParking(Parking parking) {
        return parkingService.upParking(parking);
    }

    @RequestMapping("/addParking.do")
    @ResponseBody
    public boolean addParking(Parking parking) {
        return parkingService.addParking(parking);
    }

    @RequestMapping("/delParkings.do")
    @ResponseBody
    public boolean delParkings(String parkings) {
        List<Parking> data = JSONObject.parseArray(parkings,Parking.class);
        for (int i = 0; i < data.size(); i++){
            String parking_id = data.get(i).getParking_id();
            if(!parkingService.delParking(parking_id))
                return false;
        }
        return true;
    }

    @RequestMapping("/delParking.do")
    @ResponseBody
    public boolean delParking(String parking_id) {
        return parkingService.delParking(parking_id);
    }

    @RequestMapping("/addBulletin.do")
    @ResponseBody
    public boolean addBulletin(Bulletin bulletin) {
        return bulletinService.addBulletin(bulletin);
    }

    @RequestMapping("/delBulletin.do")
    @ResponseBody
    public boolean delBulletin(int id) {
        return bulletinService.delBulletin(id);
    }

    @RequestMapping("/delBulletins.do")
    @ResponseBody
    public boolean delBulletins(String bulletins) {
        List<Bulletin> data = JSONObject.parseArray(bulletins,Bulletin.class);
        for (int i = 0; i < data.size(); i++){
            int id = data.get(i).getId();
            if(!bulletinService.delBulletin(id))
                return false;
        }
        return true;
    }

    @RequestMapping("/upBulletin.do")
    @ResponseBody
    public boolean upBulletin(Bulletin bulletin) {
        return bulletinService.upBulletin(bulletin);
    }

    @RequestMapping("/getAllBulletin.do")
    @ResponseBody
    public List<Bulletin> getAllBulletin() {
        return bulletinService.getAllBulletin();
    }

    @RequestMapping("/getBulletinById.do")
    @ResponseBody
    public Bulletin getBulletinById(int id) {
        List<Bulletin> list = bulletinService.getBulletinById(id);
        Bulletin bulletin = null;
        if (list.size() > 0)
            bulletin = list.get(0);
        return bulletin;
    }

    @RequestMapping("/getBulletinPage.do")
    @ResponseBody
    public Map<String,Object> getBulletinPage(int page, int limit) {
        List<Bulletin> bulletins = bulletinService.getAllBulletin();
        List<Bulletin> bulletin = bulletinService.getBulletinPage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", bulletins.size());
        tableData.put("data", bulletin);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/upFeedback.do")
    @ResponseBody
    public boolean upFeedback(Feedback feedback) {
        return feedbackService.upFeedback(feedback);
    }

    @RequestMapping("/getAllFeedback.do")
    @ResponseBody
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    @RequestMapping("/getFeedbackPage.do")
    @ResponseBody
    public Map<String,Object> getFeedbackPage(int page, int limit) {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        List<Feedback> feedback = feedbackService.getFeedbackPage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", feedbacks.size());
        tableData.put("data", feedback);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getFeedbackLike.do")
    @ResponseBody
    public List<Feedback> getFeedbackLike(String data) {
        return feedbackService.getFeedbackLike(data);
    }

    @RequestMapping("/getFeedbackNull.do")
    @ResponseBody
    public List<Feedback> getFeedbackNull() {
        return feedbackService.getFeedbackNull();
    }

    @RequestMapping("/getFeedbackNNull.do")
    @ResponseBody
    public List<Feedback> getFeedbackNNull() {
        return feedbackService.getFeedbackNNull();
    }

    @RequestMapping("/getComunitiPage.do")
    @ResponseBody
    public Map<String,Object> getComunitiPage(int page, int limit) {
        List<Comuniti> comunitis = comunitiService.getAllComuniti();
        List<Comuniti> comuniti = comunitiService.getComunitiPage(page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", comunitis.size());
        tableData.put("data", comuniti);
        //返回给前端
        return tableData;
    }

    @RequestMapping("getComunitiById.do")
    @ResponseBody
    public Comuniti getComunitiById(int id) {
        Comuniti comuniti = null;

        List<Comuniti> list = comunitiService.getComunitiById(id);
        if (list.size() > 0)
            comuniti = list.get(0);
        else
            return null;

        List<Reply> replies = replyService.getReplyByComunitiId(id);
        comuniti.setReplies(replies);

        if (comuniti.getOfficial() == 1) {
            List<Admin> adminList = adminService.getAdminByJobNum(comuniti.getPost_id());
            if (adminList.size() > 0)
                comuniti.setAdmin(adminList.get(0));
            else
                comuniti.setAdmin(null);
        } else {
            List<User> userList = userService.getUserByUserName(comuniti.getPost_id());
            if(userList.size() > 0)
                comuniti.setUser(userList.get(0));
            else
                comuniti.setUser(null);
        }

        return comuniti;
    }

    @RequestMapping("/addReply.do")
    @ResponseBody
    public boolean addReply(Reply reply) {
        return replyService.addReply(reply);
    }

    @RequestMapping("/delReply.do")
    @ResponseBody
    public boolean delReply(int id) {
        return replyService.delReply(id);
    }

    @RequestMapping("/delComuniti.do")
    @ResponseBody
    public boolean delComuniti(int id) {
        replyService.delReplyByComunitiId(id);
        return comunitiService.delComuniti(id);
    }

    @RequestMapping("/delComunitis.do")
    @ResponseBody
    public boolean delComunitis(String comunitis) {
        List<Comuniti> data = JSONObject.parseArray(comunitis,Comuniti.class);
        for (int i = 0; i < data.size(); i++){
            int id = data.get(i).getId();
            replyService.delReplyByComunitiId(id);
            if (!comunitiService.delComuniti(id))
                return false;
        }
        return true;
    }

    @RequestMapping("/addComuniti.do")
    @ResponseBody
    public boolean addComuniti(Comuniti comuniti) {
        return comunitiService.addComuniti(comuniti);
    }

    @RequestMapping("/getComunitiLike.do")
    @ResponseBody
    public Map<String,Object> getComunitiLike(String title, int page, int limit) {
        List<Comuniti> comunitis = comunitiService.getComunitiLike(title);
        List<Comuniti> comuniti = comunitiService.getComunitiLikePage(title, page, limit);

        Map<String,Object> tableData =new HashMap<String,Object>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", comunitis.size());
        tableData.put("data", comuniti);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/getMyComuniti.do")
    @ResponseBody
    public List<Comuniti> getMyComuniti(String post_id) {
        return comunitiService.getComunitiByPostId(post_id);
    }

    @RequestMapping("/getReportedComuniti.do")
    @ResponseBody
    public List<Comuniti> getReportedComuniti() {
        return comunitiService.getReportedComuniti();
    }

    @RequestMapping("/reportComuniti.do")
    @ResponseBody
    public boolean reportComuniti(int id) {
        return comunitiService.reportComuniti(id);
    }

    @RequestMapping("/unReportComuniti.do")
    @ResponseBody
    public boolean unReportComuniti(int id) {
         return comunitiService.unReportComuniti(id);
    }

    @RequestMapping("/getReportedReply.do")
    @ResponseBody
    public List<Reply> getReportedReply() {
        return replyService.getReportedReply();
    }

    @RequestMapping("/reportReply.do")
    @ResponseBody
    public boolean reportReply(int id) {
        return replyService.reportReply(id);
    }

    @RequestMapping("/unReportReply.do")
    @ResponseBody
    public boolean unReportReply(int id) {
        return replyService.unReportReply(id);
    }

}
