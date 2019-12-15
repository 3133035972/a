package com.example.springboot.controller;

import com.example.springboot.configurer.QiniuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class UploadController {

    @RequestMapping("showfile")
    public String showfile()
    {
        return "file";
    }

    //显示页面
    @RequestMapping("doshow")
    public String doshow()
    {
        return "show";
    }

    //    编辑器上传图片
    @RequestMapping("uploadPhoto")
    public String mainPage(@RequestParam("file") MultipartFile file)  {
        QiniuUtil qiniuUtil = new QiniuUtil();

//        文件名
        String originalFilename = file.getOriginalFilename();
        byte[] bytes = new byte[0];
        String uploadName = null;
        try {
//          获取文件的bytes形式
            bytes = file.getBytes();
//           调用QiniuUtil中的方法
            uploadName = qiniuUtil.upload(bytes, originalFilename);
            System.out.println(uploadName);
            return "show";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "show";
    }



}
