package com.chengxuunion.generator.business.url.controller;

import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.model.request.UrlPageParam;
import com.chengxuunion.generator.business.url.service.UrlService;

import com.chengxuunion.generator.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:    系统URL控制器
 * @Date:创建时间: 2019-01-16 17:40:36
 * @Modified By:
 */
@Controller
@RequestMapping("/url/manager")
public class UrlController extends BaseController {

    @Autowired
    private UrlService urlService;

    @GetMapping
    public String index() {
        return "url/index";
    }

    @GetMapping("/treeselect")
    public String listSelect(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        model.addAttribute("ids", ids);
        return "url/tree-select";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(UrlPageParam urlPageParam) {
        return urlService.listUrlPage(urlPageParam);
    }


    @GetMapping("/list-all")
    @ResponseBody
    public Object listAll() {
        return urlService.listAllUrl();
    }

    @GetMapping("/add")
    public String add(Model model, HttpServletRequest request) {
        model.addAttribute("parentId", request.getParameter("parentId"));
        model.addAttribute("parentName", request.getParameter("parentName"));
        model.addAttribute("methods", Constants.REQUEST_METHODS);
        return "url/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Url url) {
        return urlService.saveUrl(url);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Url url = urlService.getUrl(id);
        model.addAttribute("url", url);
        model.addAttribute("methods", Constants.REQUEST_METHODS);
        return "url/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Url url) {
        return urlService.updateUrl(url);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return urlService.deleteUrl(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Url url = urlService.getUrl(id);
        model.addAttribute("url", url);
        model.addAttribute("methods", Constants.REQUEST_METHODS);
        return "url/detail";
    }

}
