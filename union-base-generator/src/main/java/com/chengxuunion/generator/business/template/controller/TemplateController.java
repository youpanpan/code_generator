package com.chengxuunion.generator.business.template.controller;

import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.service.EngineService;
import com.chengxuunion.generator.business.template.model.Template;
import com.chengxuunion.generator.business.template.model.request.TemplatePageParam;
import com.chengxuunion.generator.business.template.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    模版控制器
 * @Date:创建时间: 2019-01-09 14:04:49
 * @Modified By:
 */
@Controller
@RequestMapping("/template/manager")
public class TemplateController extends BaseController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private EngineService engineService;

    @GetMapping
    public String index() {
        return "template/index";
    }

    @GetMapping("/listselect")
    public String listSelect(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String names = request.getParameter("names");
        model.addAttribute("templateType", request.getParameter("templateType"));
        model.addAttribute("maxNum", request.getParameter("maxNum"));
        model.addAttribute("ids", ids);
        model.addAttribute("names", names);
        return "template/list-select";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(TemplatePageParam templatePageParam) {
        return templateService.listTemplatePage(templatePageParam);
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Engine> engineList = engineService.listEngineEnable();
        model.addAttribute("engineList", engineList);
        return "template/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(@RequestBody Template template) {
        return templateService.saveTemplate(template);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Template template = templateService.getTemplate(id);
        model.addAttribute("template", template);
        List<Engine> engineList = engineService.listEngineEnable();
        model.addAttribute("engineList", engineList);
        return "template/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(@RequestBody Template template) {
        return templateService.updateTemplate(template);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return templateService.deleteTemplate(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Template template = templateService.getTemplate(id);
        model.addAttribute("template", template);
        List<Engine> engineList = engineService.listEngineEnable();
        model.addAttribute("engineList", engineList);
        return "template/detail";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object uploadTemplate(MultipartFile file) {
        return templateService.uploadTemplate(file);
    }

    @GetMapping("/download/{id}")
    public String downloadTemplate(HttpServletResponse response, @PathVariable("id") Long id) {
        Boolean success = templateService.downloadTemplate(id, response);
        if (success) {
            return null;
        } else {
            return "public/file-notfind";
        }
    }
}
