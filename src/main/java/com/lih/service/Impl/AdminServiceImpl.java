package com.lih.service.Impl;

import com.lih.dao.AdminDao;
import com.lih.entity.Admin;
import com.lih.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 20:08
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminDao adminDao;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin queryByUsername(String username) {
        return adminDao.queryByUsername(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin queryByUsernames(String username) {
        return adminDao.queryByUsernames(username);
    }
}
