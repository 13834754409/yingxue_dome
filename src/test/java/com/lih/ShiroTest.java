package com.lih;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.*;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/02 19:55
 */
@SpringBootTest
public class ShiroTest {

    @Test
    public void testMD5() {
        //MD5 SHA
        //创建加密算法  参数:明文密码
        //Md5Hash md5Hash = new Md5Hash("111111");
        //创建加密算法  参数:明文密码,随机盐
        //Md5Hash md5Hashsalt = new Md5Hash("111111","abcd");
        //创建加密算法  参数:明文密码,随机盐,散列次数
        //Md5Hash md5Hash = new Md5Hash("111111","abcd",1024);
        Md2Hash md2Hash = new Md2Hash("111111");
        Md5Hash md5Hash = new Md5Hash("111111");
        Md5Hash md5Hashsalt = new Md5Hash("111111", "abcd");
        Md5Hash md5Hashhash = new Md5Hash("admin", "abcd", 1024);
        Sha1Hash sha1Hash = new Sha1Hash("111111");
        Sha256Hash sha256Hash = new Sha256Hash("111111");
        Sha384Hash sha384Hash = new Sha384Hash("111111");
        Sha512Hash sha512Hash = new Sha512Hash("111111");
        //加密
        String md2 = md2Hash.toHex();
        String md5 = md5Hash.toHex();
        String md5l = md5Hashsalt.toHex();
        String md5h = md5Hashhash.toHex();
        String sha1 = sha1Hash.toHex();
        String sha256 = sha256Hash.toHex();
        String sha384 = sha384Hash.toHex();
        String sha512 = sha512Hash.toHex();

        System.out.println("md2:" + md2);
        System.out.println("md5:" + md5);
        System.out.println("md5l:" + md5l);
        System.out.println("md5h:" + md5h);
        System.out.println("sha1:" + sha1);
        System.out.println("sha256:" + sha256);
        System.out.println("sha384:" + sha384);
        System.out.println("sha512:" + sha512);

        /**
         * md2:43f44d2e244d26cce9272c71b28138dc
         * md5:96e79218965eb72c92a549dd5a330112
         * md5l:f1293bcae648b20fb3329c21b9af1638
         * md5h:c3f2b09474f65a0bb8eda78e3682955f
         * sha1:3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d
         * sha256:bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a
         * sha384:1b0268a40ae44c012946c974d60bf5291e7bb7c63cdb72a904d9283e3dc0a34de9afebe4035665768aaa503a4e7a30c3
         * sha512:b0412597dcea813655574dc54a5b74967cf85317f0332a2591be7953a016f8de56200eb37d5ba593b1e4aa27cea5ca27100f94dccd5b04bae5cadd4454dba67d
         */
    }

    //后台认证方法
    public static void testlogin(String username,String password){
        //初始化安全管理器工厂
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //根据安全管理器工厂初始化安全管理器
        SecurityManager instance = factory.createInstance();

        //将安全管理器交给安全工具类
        SecurityUtils.setSecurityManager(instance);

        //根据安全工具类获取主体对象
        Subject subject = SecurityUtils.getSubject();

        //创建令牌 token=身份信息(username)+凭证信息(password)
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //认证
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("未知的账号异常   用户名不正确");
        }catch (IncorrectCredentialsException e){
            System.out.println("不正确的凭证异常   密码错误");
        }
        /**
         * UnknownAccountException: 未知的账号异常   用户名不正确
         * IncorrectCredentialsException:不正确的凭证异常   密码错误
         */

        //判断是否认证成功
        boolean authenticated = subject.isAuthenticated();
        System.out.println("认证状态"+authenticated);
        if (authenticated){
            //获取角色  渲染页面
            //判断当前主体是否有该角色
            //boolean b = subject.hasRole("admin");

            //判断当前猪体是否含有这些角色
            //boolean[] booleans = subject.hasRoles(Arrays.asList("admin", "sAdmin", "super"));

            //判断当前主体是否有这些所有的角色
            boolean b = subject.hasAllRoles(Arrays.asList("admin", "sAdmin"));

            System.out.println("角色授权状态: "+b);

            //判断当前主体是否有该权限
            //boolean permitted = subject.isPermitted("user:query");

            //判断当前主体是否有这些权限
            //boolean[] permitteds = subject.isPermitted("user:query", "admin:add");

            //判断当前主体是否有这些所有权限
            boolean permitted = subject.isPermittedAll("user:query", "admin:add");

            System.out.println("权限授权状态: "+permitted);
        }
    }

    public static void main(String[] args) {
        testlogin("admin","111111");
    }
}






















