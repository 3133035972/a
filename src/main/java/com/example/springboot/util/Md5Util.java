package com.example.springboot.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Util {

    public static String getMd5(String pwd,String salt)
    {
        //算法名字:加密方式
        String algontiehmName = "MD5";
        //盐值
        ByteSource bytes = ByteSource.Util.bytes(salt);
        //加密次数
        int hashIterations=5;
        return new SimpleHash(algontiehmName,pwd,bytes,hashIterations).toString();
    }

}
