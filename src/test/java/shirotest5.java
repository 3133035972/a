import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class shirotest5 {

//    Reaml域:保存客户信息(身份,角色,权限等),验证时,Shiro从域中获取信息进行验证

    @Test
    public void test()
    {
        IniSecurityManagerFactory factory=new IniSecurityManagerFactory("classpath:shiro1.ini");

        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //判断当前用户是否已经完成认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证前:"+authenticated);

        //提供认证信息
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");

        //登录,认真身份信息
        try{

            subject.login(token);

            boolean b = subject.hasRole("超级管理员");
            System.out.println("是否超级原理员:"+b);

            subject.isPermitted("add");
            System.out.println("用户add权限");

        }catch (IncorrectCredentialsException e){// Credentials:密码
            System.out.println("用户密码错误");
        }catch(UnknownAccountException e){
            System.out.println("用户名错误");
        }catch(UnauthorizedException e){
            System.out.println("没有该角色");
        }
        authenticated = subject.isAuthenticated();
        System.out.println("登录后:"+authenticated);

        //退出
        subject.logout();

        authenticated = subject.isAuthenticated();
        System.out.println("退出后:"+authenticated);

    }

}
