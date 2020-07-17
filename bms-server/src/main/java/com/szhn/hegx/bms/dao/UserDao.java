package com.szhn.hegx.bms.dao;

import com.szhn.hegx.bms.model.BmsUser;
import com.szhn.hegx.bms.model.Role;

import java.util.List;


/**
 * <p>TODO</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
public interface UserDao {

    /**
     * 查找用户
     * @param username
     * @return
     */
    BmsUser findByUsername(String username);

    /**
     * 获取角色信息
     * @param id
     * @return
     */
    List<Role> findRoleByUserId(Integer id);

}
