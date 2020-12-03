package com.lih.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 18:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private String roleId;
    private String roleName;

    //关系属性
    private List<Authority> authorityList;
}
