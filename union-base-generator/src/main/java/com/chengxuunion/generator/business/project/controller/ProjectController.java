package com.chengxuunion.generator.business.project.controller;

import com.chengxuunion.generator.business.project.model.Project;
import com.chengxuunion.generator.business.project.model.request.ProjectPageParam;
import com.chengxuunion.generator.business.project.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    项目组控制器
 * @Date:创建时间: 2019-01-17 11:46:59
 * @Modified By:
 */
@Controller
@RequestMapping("/project/manager")
public class ProjectController extends BaseController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    public String index() {
        return "project/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(ProjectPageParam projectPageParam) {
        return projectService.listProjectPage(projectPageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "project/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Project project) {
        return projectService.saveProject(project);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Project project) {
        return projectService.updateProject(project);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return projectService.deleteProject(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Project project = projectService.getProject(id);
        model.addAttribute("project", project);
        return "project/detail";
    }

    @GetMapping("/manager-user/{id}")
    public String managerUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("id", id);
        return "project/managerUser";
    }

}
