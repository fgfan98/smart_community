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

}
