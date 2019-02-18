package com.chengxuunion.generator.business.childtemplate.controller;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.model.request.ChildTemplatePageParam;
import com.chengxuunion.generator.business.childtemplate.service.ChildTemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    子模版控制器
 * @Date:创建时间: 2019-01-09 17:25:12
 * @Modified By:
 */
@Controller
@RequestMapping("/childtemplate/manager")
public class ChildTemplateController extends BaseController {

    @Autowired
    private ChildTemplateService childTemplateService;

    @GetMapping
    public String index() {
        return "childtemplate/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ChildTemplatePageParam childTemplatePageParam) {
        return childTemplateService.listChildTemplatePage(childTemplatePageParam);
    }

    /**
     * 根据父模版ID查询子模版列表
     *
     * @param parentTemplateId
     * @return
     */
    @GetMapping("/list/{parentTemplateId}")
    @ResponseBody
    public Object listChildTemplateByParentId(@PathVariable("parentTemplateId") Long parentTemplateId) {
        return childTemplateService.listChildTemplateByParentId(parentTemplateId);
    }

    @GetMapping("/add")
    public String add() {
        return "childtemplate/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(ChildTemplate childTemplate) {
        return childTemplateService.saveChildTemplate(childTemplate);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        ChildTemplate childTemplate = childTemplateService.getChildTemplate(id);
        model.addAttribute("childTemplate", childTemplate);
        return "childtemplate/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(ChildTemplate childTemplate) {
        return childTemplateService.updateChildTemplate(childTemplate);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return childTemplateService.deleteChildTemplate(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        ChildTemplate childTemplate = childTemplateService.getChildTemplate(id);
        model.addAttribute("childTemplate", childTemplate);
        return "childtemplate/detail";
    }

}
