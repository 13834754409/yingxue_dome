package com.lih.realm;

import com.lih.dao.AdminDao;
import com.lih.entity.Admin;
import com.lih.entity.Authority;
import com.lih.entity.Role;
import com.lih.service.AdminService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/02 20:14
 */
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");
        //强转
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //获取用户名
        String username = upToken.getUsername();

        //查询数据库
        //User u = queryByUsername(username)
        //new User("1","admin","c3f2b09474f65a0bb8eda78e3682955f","abcd")
        Admin admin = adminService.queryByUsername(username);


        AuthenticationInfo info = null;
        if(admin!=null){
            info = new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),
                    ByteSource.Util.bytes(admin.getSalt()),this.getName());
        }

        return info;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权");
        //获取用户主身份
        String username = (String) principalCollection.getPrimaryPrincipal();
        //数据库查询
        //根据用户名插叙该用户有哪些角色  角色集合  admin  sAdmin
        //根据角色查询用户有哪些权限   权限集合
        // admin(user:query  user:update category:query category:delete category:update category:add)
        // sAdmin(admin:query admin:add admin:delete log:query)
        //角色集合
        ArrayList<String> roles = new ArrayList<>();
        //权限集合
        ArrayList<String> permissiones = new ArrayList<>();
        //获取数据
        Admin admin = adminService.queryByUsernames(username);
        for (Role role : admin.getRoleList()) {
            //获取角色
            String roleName = role.getRoleName();
            roles.add(roleName);
            //获取权限
            for (Authority authority : role.getAuthorityList()) {
                permissiones.add(authority.getAuthorityName());
            }
        }
        SimpleAuthorizationInfo info = null;
        if(admin!=null){
            //设置权限配置
            info = new SimpleAuthorizationInfo();

            //给当前主体赋予一个角色
            //info.addRole("admin");
            //给当前主体赋予多个角色
            info.addRoles(roles);

            //给当前主体赋予一个权限
            //info.addStringPermission("user:query");
            //给当前主体赋予多个权限
            info.addStringPermissions(permissiones);
        }

        return info;
    }
}
