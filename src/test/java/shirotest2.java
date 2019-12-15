import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class shirotest2 {

//    Reaml域:保存客户信息(身份,角色,权限等,)验证时
//    shiro从域中获取用户信息进行验证

    //判断用户有没有哪个角色
    SimpleAccountRealm simpleAccountRealm;
    @Before
    public void setReaml()
    {
        simpleAccountRealm=new SimpleAccountRealm();
        simpleAccountRealm.addAccount("admin","123","test","admins","superadmin");
        simpleAccountRealm.addAccount("zhangsan","456","test");
    }

    @Test
    public void test()
    {
        //安全管理器
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();

        defaultSecurityManager.setRealm(simpleAccountRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取当前用户
        Subject subject=SecurityUtils.getSubject();

        //判断当前用户是否已经完成认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证前:"+authenticated);

        //提供认证信息
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");

        //登录,认证身份信息
        try{
            subject.login(token);
            //获取用户名
            String s = subject.getPrincipal().toString();//获取用户名
            System.out.println("用户名:"+s);

            //判断用户是否拥有某个角色
            boolean admins = subject.hasRole("admins");
            System.out.println("是否拥有admins角色:"+admins);
            //判断用户是否拥有多个角色
            List<String> roles=new ArrayList<String>();
            roles.add("admins");
            roles.add("test");
            boolean b = subject.hasAllRoles(roles);
            System.out.println("是否拥有多个角色:"+b);

            //检查是否拥有某个角色
            subject.checkRole("superadmin");

            //设置记住用户
            token.setRememberMe(true);
            //是否记住用户
            boolean rememberMe = token.isRememberMe();
            System.out.println("是否记住用户:"+rememberMe);

        }catch (IncorrectCredentialsException e)
        {
            System.out.println("用户密码错误!");
        }catch(UnknownAccountException e)
        {
            System.out.println("用户名错误");
        }catch (UnauthorizedException e)
        {
            System.out.println("没有该角色");
        }
        boolean authenticated1 = subject.isAuthenticated();
        System.out.println("登录后:"+authenticated1);

        //退出
        subject.logout();

        authenticated1 = subject.isAuthenticated();
        System.out.println("退出后:"+authenticated1);

    }


}
