package com.chengxuunion.generator.business.userrole.controller;

import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.userrole.model.UserRole;
import com.chengxuunion.generator.business.userrole.model.request.UserRolePageParam;
import com.chengxuunion.generator.business.userrole.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    用户角色控制器
 * @Date:创建时间: 2019-01-17 16:59:14
 * @Modified By:
 */
@Controller
@RequestMapping("/userrole/manager")
public class UserRoleController extends BaseController {

    @Autowired
    private UserRoleService userRoleService;

    @GetMapping
    public String index() {
        return "userrole/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(UserRolePageParam userRolePageParam) {
        return userRoleService.listUserRolePage(userRolePageParam);
    }

    @GetMapping("/list/{userId}")
    @ResponseBody
    public Object listUserRole(@PathVariable("userId") Long userId) {
        return userRoleService.listUserRoleByUserId(userId);
    }

    @GetMapping("/add")
    public String add() {
        return "userrole/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(UserRole userRole) {
        return userRoleService.saveUserRole(userRole);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        UserRole userRole = userRoleService.getUserRole(id);
        model.addAttribute("userRole", userRole);
        return "userrole/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(UserRole userRole) {
        return userRoleService.updateUserRole(userRole);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return userRoleService.deleteUserRole(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        UserRole userRole = userRoleService.getUserRole(id);
        model.addAttribute("userRole", userRole);
        return "userrole/detail";
    }

    @PostMapping("/do-save")
    @ResponseBody
    public Object doSave(@RequestBody User user) {
        return userRoleService.saveUserRoleBatch(user);
    }
}
