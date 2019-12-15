package com.example.springboot.service;

import com.example.springboot.dao.UserInfoDAO;
import com.example.springboot.entity.UserInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserInfoService {

    @Resource
    UserInfoDAO userInfoDAO;

    public int InsertUserInfo(UserInfo userInfo)
    {
        userInfo.setSalt("csdn");   //我们先设置好md5的盐值
        userInfo.setHashinteration(5);  //设置好加密次数
        return userInfoDAO.insert(userInfo);
    }

}
