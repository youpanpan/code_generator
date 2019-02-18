package com.chengxuunion.generator.business.templatecontext.controller;

import com.chengxuunion.generator.business.templatecontext.model.TemplateContext;
import com.chengxuunion.generator.business.templatecontext.model.request.TemplateContextPageParam;
import com.chengxuunion.generator.business.templatecontext.service.TemplateContextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    模版依赖的上下文控制器
 * @Date:创建时间: 2019-01-15 11:49:27
 * @Modified By:
 */
@Controller
@RequestMapping("/templatecontext/manager")
public class TemplateContextController extends BaseController {

    @Autowired
    private TemplateContextService templateContextService;

    @GetMapping
    public String index() {
        return "templatecontext/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(TemplateContextPageParam templateContextPageParam) {
        return templateContextService.listTemplateContextPage(templateContextPageParam);
    }

    @GetMapping("/list/{templateId}")
    @ResponseBody
    public Object listTemplateContext(@PathVariable("templateId") Long templateId) {
        return templateContextService.listTemplateContextByTemplateId(templateId);
    }

    @GetMapping("/add")
    public String add() {
        return "templatecontext/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(TemplateContext templateContext) {
        return templateContextService.saveTemplateContext(templateContext);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        TemplateContext templateContext = templateContextService.getTemplateContext(id);
        model.addAttribute("templateContext", templateContext);
        return "templatecontext/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(TemplateContext templateContext) {
        return templateContextService.updateTemplateContext(templateContext);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return templateContextService.deleteTemplateContext(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        TemplateContext templateContext = templateContextService.getTemplateContext(id);
        model.addAttribute("templateContext", templateContext);
        return "templatecontext/detail";
    }

}
