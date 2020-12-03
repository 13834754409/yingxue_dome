package com.lih.service;

import com.lih.dao.AdminDao;
import com.lih.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 20:07
 */
public interface AdminService {
    Admin queryByUsername(String username);
    Admin queryByUsernames(String username);
}
