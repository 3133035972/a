package com.example.springboot.service;

import com.example.springboot.dao.DeptDAO;
import com.example.springboot.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService {

    @Resource
    DeptDAO deptDAO;

    //查询全部
    public List<Dept> findAll()
    {
        return deptDAO.selectAll();
    }




}
