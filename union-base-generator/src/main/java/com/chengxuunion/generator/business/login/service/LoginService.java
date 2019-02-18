package com.chengxuunion.generator.business.login.service;

import com.chengxuunion.generator.business.login.model.request.LoginParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:23
 * @Modified By:
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param loginParam
     */
    void login(LoginParam loginParam);

    /**
     * 注销
     */
    void logout();
}
