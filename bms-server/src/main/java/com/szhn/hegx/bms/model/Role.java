package com.szhn.hegx.bms.model;

import java.io.Serializable;

/**
 * <p>角色信息</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
public class Role implements Serializable {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}