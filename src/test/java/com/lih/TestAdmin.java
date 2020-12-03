package com.lih;

import com.lih.dao.AdminDao;
import com.lih.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 19:37
 */
@SpringBootTest
public class TestAdmin {
    @Autowired
    private AdminDao adminDao;
    @Test
    public void test0(){
        Admin admin = adminDao.queryByUsername("nanan");
        System.out.println(admin);
    }
    @Test
    public void test1(){
        Admin admin = adminDao.queryByUsernames("nanan");
        System.out.println(admin);

        /**
         * Admin(adminId=1, username=nanan, password=a2c9ec06b8c0a2be811dfd47be6e5f82, salt=asdfaf,
         *          roleList=[
         *              Role(
         *                  roleId=2, roleName=superAdmin,
         *                        authorityList=[Authority(authorityId=1, authorityName=admin:query),
         *                                      Authority(authorityId=2, authorityName=admin:delete),
         *                                      Authority(authorityId=3, authorityName=admin:update),
         *                                      Authority(authorityId=4, authorityName=admin:insert)]
         *                  ),
         *              Role(
         *                  roleId=3, roleName=admin,
         *                      authorityList=[Authority(authorityId=5, authorityName=user:select),
         *                                     Authority(authorityId=6, authorityName=user:update)]
         *                  ),
         *              Role(
         *                  roleId=1, roleName=common,
         *                      authorityList=[]
         *                  ),
         *              Role(
         *                  roleId=4, roleName=user,
         *                      authorityList=[]
         *                  )
         *         ]
         * )
         */
    }
}
