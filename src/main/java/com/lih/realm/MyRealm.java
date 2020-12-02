package com.lih.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/02 20:14
 */
public class MyRealm extends AuthorizingRealm {
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //强转
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = upToken.getUsername();

        //查询数据库
        //User u = queryByUsername(username)
        //new User("1","admin","c3f2b09474f65a0bb8eda78e3682955f","abcd")

        AuthenticationInfo info = null;
        if(username.equals("admin")){
            info = new SimpleAuthenticationInfo("admin","9898247bfac3a524680145b3b5e203d3",
                    ByteSource.Util.bytes("abcd"),this.getName());
        }

        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        /*//获取用户主身份
        String username = (String) principalCollection.getPrimaryPrincipal();
        //数据库查询
        //根据用户名插叙该用户有哪些角色  角色集合  admin  sAdmin
        //根据角色查询用户有哪些权限   权限集合
        // admin(user:query  user:update category:query category:delete category:update category:add)
        // sAdmin(admin:query admin:add admin:delete log:query)
        SimpleAuthorizationInfo info = null;
        if(username.equals("admin")){
            //设置权限配置
            info = new SimpleAuthorizationInfo();

            //给当前主体赋予一个角色
            //info.addRole("admin");
            //给当前主体赋予多个角色
            info.addRoles(Arrays.asList("admin","sAdmin"));

            //给当前主体赋予一个权限
            //info.addStringPermission("user:query");
            //给当前主体赋予多个权限
            info.addStringPermissions(Arrays.asList("user:query","user:update","admin:query","admin:add","log:query"));
        }

        return info;*/
        return null;
    }
}
