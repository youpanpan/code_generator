package com.chengxuunion.generator.business.index.controller;

import com.chengxuunion.generator.business.index.service.IndexService;
import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.controller.BaseController;
import com.chengxuunion.generator.common.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:    首页控制器
 * @Date:创建时间: 2018-12-19 16:58
 * @Modified By:
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    @Autowired
    private IndexService indexService;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/set-language/{language}")
    @ResponseBody
    public Object setLanguage(@PathVariable("language") String language, HttpServletRequest request) {
        SessionUtils.setValue(Constants.LANGUAGE, language);
        return "";
    }

    @GetMapping("/get-number-statistics")
    @ResponseBody
    public Object getNumberStatistics() {
        return indexService.getNumberStatistics();
    }

    @GetMapping("/get-code-number")
    @ResponseBody
    public Object getCodeNumber(Integer type) {
        return indexService.getCodeNumber(type);
    }

}
