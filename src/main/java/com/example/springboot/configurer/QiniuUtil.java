package com.example.springboot.configurer;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.IOException;
import java.util.UUID;

public class QiniuUtil {
    //设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "RuRkV27BKQuVljbXAsADDD6eU45Z8VL78zT8xz1O";
    private static final String SECRET_KEY = "QgEsWNrHp31Rr663D1MWoUKI7SDoI-iKWCJ2lx3i";
    //要上传的空间
    private static final String bucketname = "a319test";
    //密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    //普通上传
    public String upload(byte[] bytes, String oldName) throws IOException {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(cfg);

        String upToken = auth.uploadToken(bucketname);

//        修改文件名称，以免重复
        //文件扩展名
        String fileExtName = oldName.substring(oldName.lastIndexOf(".") + 1);
        String fileName = "mrainBlog" + UUID.randomUUID() + "." + fileExtName;
        //创建上传对象
        //调用put方法上传  这里是使用的Byte类型上传的。还有其他方法
        Response res = uploadManager.put(bytes, fileName, upToken);
        //解析上传成功的结果
        DefaultPutRet putRet = new Gson().fromJson(res.bodyString(), DefaultPutRet.class);
//        获取返回的文件名
        String key = putRet.key;
//        获取图片路径
        String filePath = "http://q2aio14dz.bkt.clouddn.com"+key;

        return filePath;
    }
}

