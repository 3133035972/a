package com.example.springboot.entity;

import javax.persistence.Table;

@Table(name = "empinfo")
public class EmpInfo {

    private Integer eid;
    private Integer did;
    private Integer pid;
    private String img;

    @Override
    public String toString() {
        return "EmpInfo{" +
                "eid=" + eid +
                ", did=" + did +
                ", pid=" + pid +
                ", img='" + img + '\'' +
                '}';
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
