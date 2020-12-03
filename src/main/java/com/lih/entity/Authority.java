package com.lih.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.security.DenyAll;

/**
 * @author:lih
 * @Description:
 * @Date:2020/12/03 18:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority {
    private String authorityId;
    private String authorityName;

}
