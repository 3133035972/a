package com.example.springboot.entity;

import javax.persistence.Table;

@Table(name = "pos")
public class Pos {

    private Integer pid;
    private String pname;
    private Integer did;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}
