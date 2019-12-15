package com.example.springboot.dao;

import com.example.springboot.entity.Pos;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface PosDAO  extends Mapper<Pos> {

    @Select("select *from pos where did=#{param1}")
    List<Pos> findByPid(int did);

}
