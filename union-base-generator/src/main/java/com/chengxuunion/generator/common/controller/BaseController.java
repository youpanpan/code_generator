package com.chengxuunion.generator.common.controller;

import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.service.UrlService;
import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 16:58
 * @Modified By:
 */
public abstract class BaseController {

    @Autowired
    private UrlService urlService;

    @SuppressWarnings("unchecked")
	@ModelAttribute
    public void populateModel( Model model) throws Exception {
        if (!SessionUtils.isLogin()) {
            return;
        }

        // 获取用户能访问的URL列表
        User user = SessionUtils.getUser();
        Object value = SessionUtils.getValue(Constants.MENU_LIST);
        List<Url> menuList = null;
        if (value == null) {
            menuList = urlService.listMenuUrlByUserId(user.getId());
            SessionUtils.setValue(Constants.MENU_LIST, menuList);
        } else {
            menuList = (List<Url>)value;
        }
        model.addAttribute("menuList", menuList);
    }

}
