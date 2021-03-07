package com.gigsider.controller;

import com.alibaba.fastjson.JSONObject;
import com.gigsider.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/upload")
public class uploadController {

    @Autowired
    HouseService houseService;

    @RequestMapping("/blueprint.do")
    @ResponseBody
    public JSONObject upload(@RequestParam("file")MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();

        //作为新的文件名
        String newfileName=request.getParameter("house_id").split("#")[1];

        if (newfileName.equals("wrong")){
            resUrl.put("src", "");
            res.put("code", 1);
            res.put("msg", "住宅编号格式错误！");
            res.put("data", resUrl);
            return res;
        }

        // url为相对路径  /    \
        String  url="upload"+File.separator+"blueprint"+File.separator;  // upload/blueprint/

        //path存放绝对路径
        String path = request.getSession().getServletContext()
                .getRealPath(File.separator+"upload"+File.separator+"blueprint"+File.separator);

        //测试绝对路径的目录是否存在，不存在，则建立对应目录;
        File file2=new File(path);
        if(!file2.exists())
            file2.mkdirs();

        /*
         * 接下来代码是复制文件，进行实质的保存；
         */

        //得到原始的上传文件的文件名（包含后缀）
        String fileName = file.getOriginalFilename();
        System.out.println("源文件名:" + fileName);
        //获得新文件的文件名，添加后缀
        String suffix=fileName.substring(fileName.lastIndexOf("."), fileName.length());
        newfileName=newfileName+suffix;
        System.out.println("新的文件名:" + newfileName);
        url+=newfileName;
        System.out.println(url);
        System.out.println(path);
        //在磁盘建立文件进行
        File targetFile = new File(path, newfileName);
        try {
            //文件复制
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resUrl.put("src", url);
        res.put("code", 0);
        res.put("msg", "上传成功！");
        res.put("data", resUrl);

        return res;
    }
}
