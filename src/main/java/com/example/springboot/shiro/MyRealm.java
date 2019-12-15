package com.example.springboot.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    //获取Service中的Bean对象

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {



        String principal=authenticationToken.getPrincipal().toString();//身份:用户名
        System.out.println("用户名:"+principal);

        //获取密码
        char[] credentials = (char[]) authenticationToken.getCredentials();//凭证:密码
        System.out.println("类型:"+authenticationToken.getCredentials().getClass());

        String pwd=new String(credentials);
        System.out.println("密码:"+pwd);

        //数据库查询
        if(principal.equals("admin")&& pwd.equals("123"))
        {
            //认证通过
            return new SimpleAuthenticationInfo(principal,pwd,getName());
        }
        //认真失败
        return null;
    }

//    授权
//    AuthorizationInfo :角色 权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        //获取身份信息
        String user= (String) principalCollection.getPrimaryPrincipal();
        System.out.println("uid:"+user);

        //定义角色
        Set<String> roles=new HashSet<String>();
        //1.查询角色
        roles.add("超级管理员");
        roles.add("测试用户");
        SimpleAuthorizationInfo simpleAuthorizationInfo=new SimpleAuthorizationInfo(roles);
        //角色分配权限
        for(String role:roles)
        {
            //根据角色查询对应的权限
            List<String> permissions=new ArrayList<String>();
            permissions.add("add");
            permissions.add("delete");
            permissions.add("select");
            simpleAuthorizationInfo.addStringPermissions(permissions);
        }
        return simpleAuthorizationInfo;
    }


}
