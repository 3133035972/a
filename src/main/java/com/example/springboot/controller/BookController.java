package com.example.springboot.controller;

import com.example.springboot.entity.Book;
import com.example.springboot.service.BookService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("book")
public class BookController {

    @Autowired
    BookService bookService;

    //提交的路径
    @Value("${app.upload.location}")
    private  String path;

    @RequestMapping("show")
    public String show()
    {
        return "show";
    }

    //显示页面
    @RequestMapping("showBook")
    public String showBook()
    {
        return "book";
    }

    //添加图书
    @RequestMapping("Insert")
    public String Insert(Book book,@RequestParam(value = "file")MultipartFile[] file) throws IOException {
        //定义序号
        int count=1;
        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
                // 使用UUID给图片重命名，并去掉四个“-”
                String name = UUID.randomUUID().toString().replaceAll("-", "");
                // 获取文件的扩展名
                String ext = FilenameUtils.getExtension(mf.getOriginalFilename());
                // 设置图片上传路径
                String url=path;
                //设置图片新的名字
                String fileName=name+"."+ext;
                // 以绝对路径保存重名命后的图片
                File targeFile=new File(url,fileName);
                mf.transferTo(targeFile);
                // 把图片存储路径保存到数据库
                if(count==1){
                    book.setImg(fileName);
                }else if(count==2){
                    book.setIm1(fileName);
                }
            }
            count++;
        }
        bookService.Insert(book);
        System.out.println(book);
        return "show";
    }

}
