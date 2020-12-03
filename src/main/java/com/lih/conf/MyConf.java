package com.lih.conf;

import com.lih.realm.MyRealm;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/02 20:41
 */
@Configuration
public class MyConf {
    //将Shiro过滤器工厂对象交给spring工厂管理
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        //创建Shiro过滤器工厂对象
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //将安全管理器对象交给过滤器工厂
        factoryBean.setSecurityManager(securityManager);
        //设置过滤规则的map
        HashMap<String, String> map = new HashMap<>();
        /**
         * anon  匿名过滤器  配置的资源不用认证就可以访问
         * authc 认证过滤器  配置的所有资源必须要认证通过才能访问
         */
        //资源路径,过滤器简称
        map.put("/**", "authc");
        map.put("/user/login", "anon");
        map.put("/main/main.jsp","anon");

        //配置退出过滤器  具体的退出代码Shiro已经实现
        map.put("/logout", "logout");
        //记住我管理器
        map.put("/**","user");
        //角色过滤器
        //map.put("/user/add","roles[userA]");
        //权限过滤器
        map.put("/user/add","perms[user:add]");


        //配置过滤器链(多个过滤器)
        factoryBean.setFilterChainDefinitionMap(map);
        //指定登陆页面的位置
        factoryBean.setLoginUrl("/user/login.jsp");
        //没有权限跳转的页面
        factoryBean.setUnauthorizedUrl("main/unauthorized.jsp");

        return factoryBean;
    }

    //将安全管理器对象交给Spring工厂管理
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm, DefaultWebSessionManager defaultWebSessionManager, CookieRememberMeManager rememberMeManager,EhCacheManager ehCacheManager) {
        //创建安全管理器对象
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义Realm交给安全管理器
        securityManager.setRealm(myRealm);
        //将sessin管理器交给安全管理器
        securityManager.setSessionManager(defaultWebSessionManager);
        //将记住我管理器交给安全管理器
        securityManager.setRememberMeManager(rememberMeManager);
        //将缓存管理器交给安全管理器
        securityManager.setCacheManager(ehCacheManager);

        return securityManager;
    }

    //将自定义Realm对象交给Spring工厂管理
    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher credentialsMatcher) {
        //创建自定义的Realm对象
        MyRealm myRealm = new MyRealm();
        //将凭证匹配器给自定义的Realm
        myRealm.setCredentialsMatcher(credentialsMatcher);
        return myRealm;
    }

    //将凭证匹配器对象交给Spring工厂管理
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        //创建凭证匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("MD5");//加密算法
        credentialsMatcher.setHashIterations(1024);//散列次数
        return credentialsMatcher;
    }

    //将Session管理器对象交给Spring工厂管理
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        //创建Session管理器对象
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();

        //设置session过期时间  参数:long类型的过期时间单位时毫秒
        defaultWebSessionManager.setGlobalSessionTimeout(120000);
        return defaultWebSessionManager;
    }

    //将记住我管理器对象交给Spring工厂管理
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie simpleCookie) {
        //创建记住我管理器
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        //将Cookie对象交给记住我管理器
        rememberMeManager.setCookie(simpleCookie);

        return rememberMeManager;
    }

    //将Cookie对象交给Spring工厂管理
    @Bean
    public SimpleCookie getSimpleCookie() {
        //创建Cookie对象
        SimpleCookie simpleCookie = new SimpleCookie();
        //设置记住我的名字  登陆页面 checkbox 中name的名字
        simpleCookie.setName("rememberme");
        //设置过期时间  单位秒
        simpleCookie.setMaxAge(604800);//7天

        return simpleCookie;
    }
    @Bean
    public EhCacheManager getEhCacheManager(){
        //创建缓存管理器
        return new EhCacheManager();
    }

}
