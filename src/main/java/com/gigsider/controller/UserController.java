package com.gigsider.controller;

import com.gigsider.po.*;
import com.gigsider.service.*;
import com.gigsider.utils.SessionPool;
import com.gigsider.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private BulletinService bulletinService;
    @Autowired
    private ComunitiService comunitiService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private ParkingService parkingService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PaymentService paymentService;

    @RequestMapping("/login.do")
    @ResponseBody
    public UserVO login(String user_name, String passwd, HttpSession httpSession){
        UserVO userVO = userService.userLogin(user_name, passwd);

        if (userVO.getUser() != null &&
                SessionPool.getExistSession(userVO.getUser().getId_num()) == null){
            SessionPool.sessions.put(userVO.getUser().getId_num(),httpSession);
            httpSession.setAttribute("id",userVO.getUser().getId_num());
            httpSession.setAttribute("login_status","success");
            httpSession.setAttribute("identity","user");
            httpSession.setMaxInactiveInterval(5*60*1000);
        }else if (userVO.getUser() != null){
            System.out.println("账号已登录");
            userVO.setMsg("warning");
        }else
            httpSession.invalidate();

        return userVO;
    }

    @RequestMapping("/exit.do")
    @ResponseBody
    public void exit(String id_num) {
        SessionPool.destroyExistSession(id_num);
    }

    @RequestMapping("isUserExistByIdNum.do")
    @ResponseBody
    public User isUserExistByIdNum(String id_num) {
        List<User> list = userService.getUserByIdNum(id_num);
        if (list.size() == 0)
            return null;
        return list.get(0);
    }

    @RequestMapping("/upUser.do")
    @ResponseBody
    public boolean upUser(User user) {
        return userService.upUser(user);
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

    @RequestMapping("/delComuniti.do")
    @ResponseBody
    public boolean delComuniti(int id) {
        replyService.delReplyByComunitiId(id);
        return comunitiService.delComuniti(id);
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

    @RequestMapping("/addComuniti.do")
    @ResponseBody
    public boolean addComuniti(Comuniti comuniti) {
        return comunitiService.addComuniti(comuniti);
    }

    @RequestMapping("/getUserById.do")
    @ResponseBody
    public User getUserById(int id) {
        User user = null;
        List<User> list = userService.getUserById(id);
        if (list.size() > 0)
            user = list.get(0);

        return user;
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

    @RequestMapping("/getParkingByLicenseNum.do")
    @ResponseBody
    public Parking getParkingByLicenseNum(String license_num) {
        Parking parking = null;
        List<Parking> list = parkingService.getParkingByLicenseNum(license_num);
        if (list.size() > 0)
            parking = list.get(0);

        return parking;
    }

    @RequestMapping("/upParking.do")
    @ResponseBody
    public boolean upParking(Parking parking) {
        return parkingService.upParking(parking);
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

    @RequestMapping("/getMyFeedback.do")
    @ResponseBody
    public List<Feedback> getMyFeedback(String user_name) {
        return feedbackService.getFeedbackByUserName(user_name);
    }

    @RequestMapping("/addFeedback.do")
    @ResponseBody
    public boolean addFeedback(Feedback feedback) {
        return feedbackService.addFeedback(feedback);
    }

    @RequestMapping("/addPayment.do")
    @ResponseBody
    public boolean addPayment(Payment payment) {
        return paymentService.addPayment(payment);
    }

    @RequestMapping("/getMyPayment.do")
    @ResponseBody
    public Map<String,Object> getMyPayment(int page, int limit, String user) {
        List<Payment> payments = paymentService.getPaymentByUser(user);
        List<Payment> payment = paymentService.paymentUserPage(user, page, limit);

        Map<String,Object> tableData = new HashMap<>();

        tableData.put("code", 0);
        tableData.put("msg", "");
        tableData.put("count", payments.size());
        tableData.put("data", payment);
        //返回给前端
        return tableData;
    }

    @RequestMapping("/pay.do")
    @ResponseBody
    public boolean pay(String id_num) {
        if (SessionPool.getExistSession(id_num) == null)
            return false;
        HttpSession session = SessionPool.getExistSession(id_num);
        session.setAttribute("pay_status","success");
        return true;
    }

    @RequestMapping("/checkPay.do")
    @ResponseBody
    public boolean checkPay(HttpSession session) {
        if (session.getAttribute("pay_status").equals("success"))
            return true;
        return false;
    }

    @RequestMapping("/completePay.do")
    @ResponseBody
    public boolean completePay(int id, HttpSession session) {
        session.removeAttribute("pay_status");
        return paymentService.delPayment(id);
    }
}
