package com.lih.entity;

/**
 * @author yww
 * @Description
 * @Date 2020/12/01 20:21
 */
public class Department {
    private String id;
    private String name;
    private Integer departmentCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(Integer departmentCount) {
        this.departmentCount = departmentCount;
    }
}
