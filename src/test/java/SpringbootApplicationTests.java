
import com.example.springboot.entity.UserInfo;
import com.example.springboot.service.UserInfoService;
import com.example.springboot.util.Md5Util;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SpringbootApplicationTests {

   @Autowired
   UserInfoService userInfoService;

    @Test
    public void test1()
    {

        UserInfo userInfo=new UserInfo();
        userInfo.setUserid(1);
        userInfo.setUsername("张三");
        userInfo.setUserpassword(Md5Util.getMd5("1234","csdn"));
        System.out.println(userInfoService.InsertUserInfo(userInfo));

    }

}
