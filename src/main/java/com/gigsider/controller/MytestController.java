package com.gigsider.controller;

import com.gigsider.po.Mytest;
import com.gigsider.service.MytestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/test")
public class MytestController {

    @Autowired
    private MytestService mytestService;

    @RequestMapping("/getAll.do")
    @ResponseBody
    public List<Mytest> getAll(){
        List<Mytest> list = mytestService.findAll();
        for (Mytest mytest : list)
            System.out.println(mytest);
        return list;
    }

    @RequestMapping("/save.do")
    @ResponseBody
    public void save(Mytest mytest){
        mytestService.save(mytest);
    }

}
