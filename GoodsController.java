package org.zys.controller;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.zys.model.UserModel;
import org.zys.service.GoodService;
import org.zys.util.OssFileUtils;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.HashMap;


@Controller
@RequestMapping("goods")
public class GoodsController {
    @Resource
    private GoodService goodService;


    @RequestMapping("tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("loginUser")
    @ResponseBody
    public String referuser(UserModel user){
        String str="";
        UserModel usermodel=goodService.referuser(user);
        if (usermodel!=null){
            str="2";
        }else{
            str="1";
        }
       return str;
    }
    @RequestMapping("toshow")
    public String toshow(){
        return "show";
    }
    @RequestMapping("refer")
    @ResponseBody
    public HashMap<String,Object> refer(Integer start,Integer pageSize){
        return goodService.refer(start,pageSize);
    }

    @RequestMapping("oss")
    @ResponseBody
    public void fileUpload(MultipartFile multipartFile, String fileName) {

        try {
            OssFileUtils.uploadFile(fileName,multipartFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("fasong")
    public void getemal(){
        try {
            HtmlEmail email=new HtmlEmail();
            email.setHostName("smtp.163.com");
            email.setCharset("UTF-8");
            email.addTo("15942087701@163.com");
            email.setFrom("zys1176344552@163.com","张先生");
            email.setAuthentication("zys1176344552@163.com","123123zhang");
            email.setSubject("测试类");
            email.setMsg("111111");
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}
