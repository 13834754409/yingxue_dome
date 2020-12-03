package com.lih.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 18:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String adminId;
    private String username;
    private String password;
    private String salt;
    //关系属性
    List<Role> roleList;
}
