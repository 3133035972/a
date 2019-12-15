package com.example.springboot.dao;

import com.example.springboot.entity.UserInfo;
import tk.mybatis.mapper.common.Mapper;

public interface UserInfoDAO  extends Mapper<UserInfo> {

    //当前我们继承的是通用Mapper所以我们直接在service层中直接调添加方法

}
