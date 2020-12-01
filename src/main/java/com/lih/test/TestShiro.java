package com.lih.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author yww
 * @Description
 * @Date 2020/12/01 20:42
 */
public class TestShiro {

    public static void login(String username,String password){
        //初始化安全管理器工厂
        IniSecurityManagerFactory managerFactory = new IniSecurityManagerFactory();

        //根据安全管理器工厂初始化安全管理器
        SecurityManager securityManager = managerFactory.createInstance();

        //将安全管理器交给安全工具类
        SecurityUtils.setSecurityManager(securityManager);

        //根据安全工具类获取主体
        Subject subject = SecurityUtils.getSubject();

        //创建令牌 token=身份信息（username）+ 凭证信息（password）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //认证  方式一
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            System.out.println("未知的账号异常 用户名不存在");
        } catch (IncorrectCredentialsException e) {
            System.out.println("不正确的凭证异常 密码输入错误");
        }

        //认证  方式二
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证状态：" + authenticated);
    }

    public static void main(String[] args) {
        login("xiaoyan", "123456");
    }
}
