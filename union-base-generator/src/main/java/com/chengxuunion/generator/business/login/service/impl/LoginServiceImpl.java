package com.chengxuunion.generator.business.login.service.impl;

import com.chengxuunion.generator.business.login.model.request.LoginParam;
import com.chengxuunion.generator.business.login.service.LoginService;
import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.service.UrlService;
import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.user.service.UserService;
import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.chengxuunion.generator.business.userrole.service.UserRoleService;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.exception.BusinessException;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.security.SecurityUtils;
import com.chengxuunion.util.string.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:23
 * @Modified By:
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UrlService urlService;

    @Override
    public void login(LoginParam loginParam) {
        User user = userService.getUserByUserNameOrEmail(loginParam.getUserName());
        if (user == null) {
            throw new BusinessException("用户名/邮箱不存在");
        }

        String password = SecurityUtils.encryptMD5(loginParam.getPassword());
        if (!StringUtils.isEquals(password, user.getPassword())) {
            throw new BusinessException("密码不正确");
        }

        user.setPassword("");
        List<UserRole> userRoleList = userRoleService.listUserRoleByUserId(user.getId());
        user.setUserRoleList(userRoleList);
        SessionUtils.setValue(Constants.USER, user);

        // 获取用户能访问的URL列表
        List<Url> menuList = urlService.listMenuUrlByUserId(user.getId());
        SessionUtils.setValue(Constants.MENU_LIST, menuList);
    }

    @Override
    public void logout() {
        SessionUtils.remove(Constants.USER);
        SessionUtils.remove(Constants.URL_LIST);
        SessionUtils.remove(Constants.MENU_LIST);
    }
}
