package com.chengxuunion.generator.business.contextparam.controller;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.service.ContextService;
import com.chengxuunion.generator.business.contextparam.model.ContextParam;
import com.chengxuunion.generator.business.contextparam.model.request.ContextParamPageParam;
import com.chengxuunion.generator.business.contextparam.service.ContextParamService;

import com.chengxuunion.generator.business.contextparaminstance.model.ContextParamInstance;
import com.chengxuunion.generator.business.contextparaminstance.service.ContextParamInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下文参数控制器
 * @Date:创建时间: 2019-01-08 16:11:09
 * @Modified By:
 */
@Controller
@RequestMapping("/contextparam/manager")
public class ContextParamController extends BaseController {

    @Autowired
    private ContextParamService contextParamService;

    @Autowired
    private ContextService contextService;

    @Autowired
    private ContextParamInstanceService contextParamInstanceService;

    @GetMapping
    public String index(Model model) {
        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparam/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ContextParamPageParam contextParamPageParam) {
        return contextParamService.listContextParamPage(contextParamPageParam);
    }

    @GetMapping("/list/{contextId}")
    public String listContextParam(Model model, @PathVariable("contextId") Long contextId) {
        List<ContextParam> contextParamList = contextParamService.listContextParamEnableByContextId(contextId);
        model.addAttribute("contextParamList", contextParamList);
        List<ContextParamInstance> instanceList = contextParamInstanceService.listContextParamInstanceByContextId(contextId);
        model.addAttribute("instanceList", instanceList);
        model.addAttribute("contextId", contextId);
        return "contextparam/context-param";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparam/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(ContextParam contextParam) {
        return contextParamService.saveContextParam(contextParam);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        ContextParam contextParam = contextParamService.getContextParam(id);
        model.addAttribute("contextParam", contextParam);

        List<Context> contextList = contextService.listContextEnable();
        model.addAttribute("contextList", contextList);
        return "contextparam/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(ContextParam contextParam) {
        return contextParamService.updateContextParam(contextParam);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return contextParamService.deleteContextParam(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        ContextParam contextParam = contextParamService.getContextParam(id);
        model.addAttribute("contextParam", contextParam);
        return "contextparam/detail";
    }

}
