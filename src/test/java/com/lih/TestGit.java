package com.lih;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/01 18:24
 */
@SpringBootTest
public class TestGit {
    @Test
    public void test0(){
        int a = 100;
        System.out.println(a);
        int b = 200;
        System.out.println(b);
        int c = 300;
        System.out.println(c);

        System.out.println("哈哈哈");

        System.out.println("同一段代码B");

        System.out.println("同一段代码A");
        //yanww
        //李辉
        System.out.println("侯杰哈哈大神");

    }

    @Test
    void testModifySameFile(){
        int i = 2147483647;
        i = i + 3;
        System.out.println(i);
        //lihui
        System.out.println("哈哈哈哈哈哈哈");
        System.out.println("是傻逼");
    }
}
