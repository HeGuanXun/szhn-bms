package com.szhn.hegx.bms.security;

import com.szhn.hegx.bms.dao.UserDao;
import com.szhn.hegx.bms.model.BmsUser;
import com.szhn.hegx.bms.model.Role;
import com.szhn.hegx.bms.utils.SpringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户登录认证信息查询
 * @author 何冠勋
 * @since 2020/7/13
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userDao==null){
            userDao = SpringUtil.getBean(UserDao.class);
        }
        BmsUser bmsUser = userDao.findByUsername(username);
        if (bmsUser==null){
            return null;
        }
        String password = new BCryptPasswordEncoder().encode(bmsUser.getPassWord());
        bmsUser.setPassWord(password);
        // 根据用户ID，查询用户的角色
        List<Role> roles = userDao.findRoleByUserId(bmsUser.getUserId());
        // 添加角色
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            authorities.add(new SimpleGrantedAuthority(roles.get(i).getName()));
        }
        // 构建 Security 的 BmsUser 对象
        return new User(bmsUser.getUserName(), bmsUser.getPassWord(), authorities);
    }
}