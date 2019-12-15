package com.example.springboot.service;

import com.example.springboot.dao.EmpInfoDAO;
import com.example.springboot.entity.EmpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpInfoService {

    @Resource
    EmpInfoDAO empInfoDAO;

    public int insert(EmpInfo empInfo)
    {
        return empInfoDAO.insert(empInfo);
    }

}
