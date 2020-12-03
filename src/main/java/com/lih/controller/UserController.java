package com.lih.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/02 21:02
 */
@Controller
@RequestMapping("user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("login")
    public String login(String username,String password,Integer rememberme){
        log.info("username: {}",username);
        log.info("rememberme: {}",rememberme);

        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        //封装token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //判断是否选中记住我7天
        if(rememberme!=null && rememberme==1){
            //是否开启记住我
            token.setRememberMe(true);
        }
        String message = null;
        try {
            //认证
            subject.login(token);
            return "redirect:/main/main.jsp";
        }catch (UnknownAccountException e){
            message = "用户名不正确";
            log.info("认证结果: {}", message);
            return "redirect:/user/login.jsp";
        }catch (IncorrectCredentialsException e){
            message = "密码不正确";
            log.info("认证结果: {}", message);
            return "redirect:/user/login.jsp";
        }
    }
    @RequestMapping("add")
    public String add(){
        System.out.println("=====");
        return "redirect:/main/query.jsp";
    }
}
