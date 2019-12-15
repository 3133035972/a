import com.zaxxer.hikari.HikariDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class shirotest4 {


//    Reaml域:保存客户信息(身份,角色,权限等),验证时,shiro从域中获取用户信息进行验证

    JdbcRealm jdbcRealm;

    @Before
    public void setReaml()
    {
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/yiibaidb?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("1234");

        jdbcRealm = new JdbcRealm();

        //shiro有默认使用的用户,角色,权限,关联表,未设置对应的sql语句时,使用shiro默认的sql语句
        jdbcRealm.setDataSource(hikariDataSource);

        //定义认证的SQL语句,获取用户的凭证(密码)
        jdbcRealm.setAuthenticationQuery("");

        //定义角色的查询语句,用来获取用户对应的角色
        jdbcRealm.setUserRolesQuery("");

        //定义获取权限的SQL语句
        jdbcRealm.setPermissionsQuery("");

    }

    @Test
    public void test()
    {
        //安全管理器
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        SecurityUtils.setSecurityManager(defaultSecurityManager);

        //获取当前用户
        Subject subject=SecurityUtils.getSubject();

        //判断当前用户是否已经认证
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证前:"+authenticated);

        //提供认证信息
        UsernamePasswordToken token=new UsernamePasswordToken("admin","123");

        //登录,认证身份信息
        try{

            //记住用户信息
            //记住密码
            token.setRememberMe(true);
            subject.login(token);

            //是否拥有某个角色
            boolean b = subject.hasRole("超级管理员");
            System.out.println("是否拥有超级管理员角色:"+b);

            //获取Subject下面的权限

            PrincipalCollection principals = subject.getPrincipals();
            for (Object p:principals)
            {
                System.out.println("p:"+p);
            }
            //判断是否有没有这个权限
            //没有这个权限直接走  UnauthorizedException e 异常
            subject.checkPermission("基础管理");
            //isPermitted 判断有没有这个权限
            boolean permitted = subject.isPermitted("基础管理");
            System.out.println("基础管理:"+permitted);

        }catch (IncorrectCredentialsException e)
        {
            System.out.println("用户密码错误");
        }catch (UnknownAccountException e)
        {
            System.out.println("用户名错误");
        }catch(UnauthorizedException e)
        {
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
