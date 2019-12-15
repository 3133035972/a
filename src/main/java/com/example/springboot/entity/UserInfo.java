package com.example.springboot.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class UserInfo {

    private Integer userid;
    private String username;
    private String userpassword;
    private String salt;
    private Integer hashinteration;


    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                ", salt='" + salt + '\'' +
                ", hashinteration=" + hashinteration +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getHashinteration() {
        return hashinteration;
    }

    public void setHashinteration(Integer hashinteration) {
        this.hashinteration = hashinteration;
    }
}
