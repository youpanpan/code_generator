package com.chengxuunion.generator.business.login.controller;

import com.chengxuunion.generator.business.login.model.request.LoginParam;
import com.chengxuunion.generator.business.login.service.LoginService;
import com.chengxuunion.generator.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 10:11
 * @Modified By:
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public String index() {
        return "login/login";
    }

    @PostMapping("/do-login")
    @ResponseBody
    public Object doLogin(LoginParam loginParam) {
        loginService.login(loginParam);
        return "";
    }

    @GetMapping("/do-logout")
    @ResponseBody
    public Object doLogout() {
        loginService.logout();
        return "";
    }

}
