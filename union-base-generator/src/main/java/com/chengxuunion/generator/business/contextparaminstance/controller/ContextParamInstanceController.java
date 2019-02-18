package com.chengxuunion.generator.business.contextparaminstance.controller;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.service.ContextService;
import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.model.request.ContextParamInstancePageParam;
import com.chengxuunion.generator.business.contextparaminstance.service.ContextParamInstanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下文参数配置实例控制器
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
@Controller
@RequestMapping("/contextparaminstance/manager")
public class ContextParamInstanceController extends BaseController {

    @Autowired
    private ContextParamInstanceService contextParamInstanceService;

    @Autowired
    private ContextService contextService;

    @GetMapping
    public String index(Model model) {
        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparaminstance/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ContextParamInstancePageParam contextParamInstancePageParam) {
        return contextParamInstanceService.listContextParamInstancePage(contextParamInstancePageParam);
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparaminstance/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(ContextParamInstance contextParamInstance) {
        return contextParamInstanceService.saveContextParamInstance(contextParamInstance);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        ContextParamInstance contextParamInstance = contextParamInstanceService.getContextParamInstance(id);
        model.addAttribute("contextParamInstance", contextParamInstance);

        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparaminstance/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(ContextParamInstance contextParamInstance) {
        return contextParamInstanceService.updateContextParamInstance(contextParamInstance);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return contextParamInstanceService.deleteContextParamInstance(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        ContextParamInstance contextParamInstance = contextParamInstanceService.getContextParamInstance(id);
        model.addAttribute("contextParamInstance", contextParamInstance);

        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparaminstance/detail";
    }

}
