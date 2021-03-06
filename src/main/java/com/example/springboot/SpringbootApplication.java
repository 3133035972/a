package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//配置解析通用Mapper
@MapperScan("com.example.springboot.dao")
@PropertySource("classpath:message.properties")
public class SpringbootApplication {

    public static void main(String[] args)
    {
        SpringApplication.run(SpringbootApplication.class,args);
    }

}
