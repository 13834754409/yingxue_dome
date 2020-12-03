package com.lih.dao;

import com.lih.entity.Admin;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 19:02
 */
public interface AdminDao {
    Admin queryByUsername(String username);
    Admin queryByUsernames(String username);
}
