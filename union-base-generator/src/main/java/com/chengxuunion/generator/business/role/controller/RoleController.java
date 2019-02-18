package com.chengxuunion.generator.business.role.controller;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.business.role.model.request.RolePageParam;
import com.chengxuunion.generator.business.role.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author youpanpan
 * @Description:    角色控制器
 * @Date:创建时间: 2019-01-16 17:02:24
 * @Modified By:
 */
@Controller
@RequestMapping("/role/manager")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index() {
        return "role/index";
    }

    @GetMapping("/listselect")
    public String listSelect(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String names = request.getParameter("names");
        model.addAttribute("maxNum", request.getParameter("maxNum"));
        model.addAttribute("ids", ids);
        model.addAttribute("names", names);
        return "role/list-select";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(RolePageParam rolePageParam) {
        return roleService.listRolePage(rolePageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "role/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        return "role/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return roleService.deleteRole(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Role role = roleService.getRole(id);
        model.addAttribute("role", role);
        return "role/detail";
    }

}
