package com.szhn.hegx.bms.controller;

import com.szhn.hegx.bms.security.JwtAuthenticationToken;
import com.szhn.hegx.bms.utils.ResultBody;
import com.szhn.hegx.bms.utils.SecurityUtils;
import com.szhn.hegx.bms.utils.SpringUtil;
import com.szhn.hegx.bms.vo.LoginBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 登录控制器
 * @author 何冠勋
 * @since 2020/7/13
 */
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 登录接口
     */
    @RequestMapping(value = "/login")
    public ResultBody login(LoginBean loginBean, HttpServletRequest request) throws IOException {
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        if (authenticationManager==null){
            authenticationManager = SpringUtil.getBean(AuthenticationManager.class);
        }
        // 系统登录认证
        JwtAuthenticationToken token = SecurityUtils.login(request, username, password, authenticationManager);

        return ResultBody.success(token);
    }

}