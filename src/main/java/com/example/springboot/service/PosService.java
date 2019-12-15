package com.example.springboot.service;

import com.example.springboot.dao.PosDAO;
import com.example.springboot.entity.Pos;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service

public class PosService {

    @Resource
    PosDAO posDAO;

    //根据Id查询
    public List<Pos> findBydid(int did)
    {
        return posDAO.findByPid(did);
    }

}
