package com.chengxuunion.generator.business.context.controller;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.model.request.ContextPageParam;
import com.chengxuunion.generator.business.context.service.ContextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:    上下文实现配置控制器
 * @Date:创建时间: 2019-01-07 17:43:15
 * @Modified By:
 */
@Controller
@RequestMapping("/context/manager")
public class ContextController extends BaseController {

    @Autowired
    private ContextService contextService;

    @GetMapping
    public String index() {
        return "context/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ContextPageParam contextPageParam) {
        return contextService.listContextPage(contextPageParam);
    }

    @GetMapping("/listselect")
    public String listSelect(Model model, HttpServletRequest request) {

        model.addAttribute("ids", request.getParameter("ids"));
        model.addAttribute("names", request.getParameter("names"));
        model.addAttribute("maxNum", request.getParameter("maxNum"));
        return "context/list-select";
    }

    @GetMapping("/add")
    public String add() {
        return "context/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Context context) {
        return contextService.saveContext(context);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Context context = contextService.getContext(id);
        model.addAttribute("context", context);
        return "context/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Context context) {
        return contextService.updateContext(context);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return contextService.deleteContext(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Context context = contextService.getContext(id);
        model.addAttribute("context", context);
        return "context/detail";
    }

}
