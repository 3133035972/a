import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class shirotest3 {

//    Reaml域:保存客户信息(身份,角色,权限等,)验证时
//    shiro从域中获取用户信息进行验证

    //从配置文件中获取
    SimpleAccountRealm simpleAccountRealm;

    @Test
    public void test()
    {
        //解析配置文件
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");

        //安全管理器
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //判断当前用户是否完成认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证前:"+authenticated);

        //提供认证信息
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "456");

        //登录,认证身份信息
        try{
            subject.login(token);

            //是否拥有某个角色
            boolean admins = subject.hasRole("admins");
            System.out.println("是否拥有admins角色:"+admins);

            //是否拥有多个角色
            List<String> lists=new ArrayList<String>();
            lists.add("admins");
            lists.add("test");

            boolean b = subject.hasAllRoles(lists);
            System.out.println("是否拥有多个角色:"+b);


            //检查是否拥有某个角色
            subject.checkRole("test");

            //判断是否拥有某个权限
            boolean permitted = subject.isPermitted("emp:add");
            System.out.println("当前用户是否拥有这个权限:"+permitted);

            //检查当前用户是否拥有这个权限

            //检查走的异常是 UnauthorizedException e
            subject.checkPermission("emp:update");

        }catch (UnknownAccountException e)  //没有当前用户
        {
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){ //当前用户密码不正确
            System.out.println("用户名密码错误");
        }catch(UnauthorizedException e)
        {
            System.out.println("没有该角色");
        }

        authenticated = subject.isAuthenticated();
        System.out.println("登录后:"+authenticated);

        //用户退出当前账户
        subject.logout();

        authenticated = subject.isAuthenticated();
        System.out.println("退出后:"+authenticated);

    }


}
