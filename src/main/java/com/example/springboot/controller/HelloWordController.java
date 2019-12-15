package com.example.springboot.controller;

import com.example.springboot.entity.Dept;
import com.example.springboot.entity.EmpInfo;
import com.example.springboot.entity.Pos;
import com.example.springboot.service.DeptService;
import com.example.springboot.service.EmpInfoService;
import com.example.springboot.service.PosService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("dept")
public class HelloWordController {

    @Value("${app.web.name}")
    private String name;

    //定义图片存储的路径
    @Value("${app.upload.location}")
    private String path;
    @Autowired
    DeptService deptService;
    @Autowired
    PosService posService;
    @Autowired
    EmpInfoService empInfoService;


    @RequestMapping("index")
    public String index()
    {
        return "index";
    }

    @RequestMapping("dept_show")
    @ResponseBody
    public List<Dept> findAll()
    {
        return deptService.findAll();
    }

    //职位表查询
    @RequestMapping("pos_show")
    @ResponseBody
    public List<Pos> findByDID(int did)
    {
        return  posService.findBydid(did);
    }

//    页面添加
    //上传到自定义地址中
    @RequestMapping("insert")
    public String insert(EmpInfo empInfo, MultipartFile file, HttpServletRequest request) throws IOException {
        //@RequestParam(value="file") 指的是jsp页面中的name属性
        //使用UUID给图片重命名,并去掉四个'-'
        //图片的名称
        String name= UUID.randomUUID().toString().replaceAll("-","");
        //获取文件的扩展名
        String text= FilenameUtils.getExtension(file.getOriginalFilename());
        //设置新的名称
        String FileName=name+"."+text;
        //设置图片保存的路径
        String url=path;
        //保存重命名的图片
        File targeFile=new File(url,FileName);
        file.transferTo(targeFile);
        //把名称存到数据库中
        empInfo.setImg(FileName);
        empInfoService.insert(empInfo);
        return "show";
    }

    //上传到项目地址中
    @RequestMapping("insert1")
    public String insert1(EmpInfo empInfo, MultipartFile file, HttpServletRequest request) throws IOException {
        //@RequestParam(value="file") 指的是jsp页面中的name属性
        //使用UUID给图片重命名,并去掉四个'-'
        //图片的名称
        String name= UUID.randomUUID().toString().replaceAll("-","");
        //获取文件的扩展名
        String text= FilenameUtils.getExtension(file.getOriginalFilename());
        //设置新的名称
        String FileName=name+"."+text;
        //设置图片保存的路径
        String url=ResourceUtils.getURL("classpath:").getPath()+"static/image";
        //获取要上传到的文件夹
        System.out.println(url);
        //保存重命名的图片
        File targeFile=new File(url,FileName);
        file.transferTo(targeFile);
        //把名称存到数据库中
        empInfo.setImg(FileName);
        empInfoService.insert(empInfo);
        return "show";
    }


}
