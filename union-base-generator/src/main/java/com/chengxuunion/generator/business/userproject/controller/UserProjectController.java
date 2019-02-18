package com.chengxuunion.generator.business.userproject.controller;

import com.chengxuunion.generator.business.userproject.model.UserProject;
import com.chengxuunion.generator.business.userproject.model.request.UserProjectPageParam;
import com.chengxuunion.generator.business.userproject.service.UserProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    用户项目组控制器
 * @Date:创建时间: 2019-01-17 14:52:29
 * @Modified By:
 */
@Controller
@RequestMapping("/userproject/manager")
public class UserProjectController extends BaseController {

    @Autowired
    private UserProjectService userProjectService;

    @GetMapping
    public String index() {
        return "userproject/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(UserProjectPageParam userProjectPageParam) {
        return userProjectService.listUserProjectPage(userProjectPageParam);
    }

    /**
     * 查询指定项目下的所有用户
     *
     * @param projectId
     * @return
     */
    @GetMapping("/list/{projectId}")
    @ResponseBody
    public Object listUserProject(@PathVariable("projectId") Long projectId) {
        return userProjectService.listUserProjectByProjectId(projectId);
    }

    @GetMapping("/add")
    public String add() {
        return "userproject/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(UserProject userProject) {
        return userProjectService.saveUserProject(userProject);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        UserProject userProject = userProjectService.getUserProject(id);
        model.addAttribute("userProject", userProject);
        return "userproject/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(UserProject userProject) {
        return userProjectService.updateUserProject(userProject);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return userProjectService.deleteUserProject(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        UserProject userProject = userProjectService.getUserProject(id);
        model.addAttribute("userProject", userProject);
        return "userproject/detail";
    }

    @PostMapping("/do-save")
    @ResponseBody
    public Object doSave(@RequestBody List<UserProject> userProjectList) {
        return userProjectService.saveUserProjectBatch(userProjectList);
    }

}
