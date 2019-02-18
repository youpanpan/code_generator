package com.chengxuunion.generator.business.roleurl.controller;

import com.chengxuunion.generator.business.role.model.Role;
import com.chengxuunion.generator.business.roleurl.model.RoleUrl;
import com.chengxuunion.generator.business.roleurl.model.request.RoleUrlPageParam;
import com.chengxuunion.generator.business.roleurl.service.RoleUrlService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    角色URL控制器
 * @Date:创建时间: 2019-01-17 17:44:49
 * @Modified By:
 */
@Controller
@RequestMapping("/roleurl/manager")
public class RoleUrlController extends BaseController {

    @Autowired
    private RoleUrlService roleUrlService;

    @GetMapping
    public String index() {
        return "roleurl/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(RoleUrlPageParam roleUrlPageParam) {
        return roleUrlService.listRoleUrlPage(roleUrlPageParam);
    }

    @GetMapping("/list/{roleId}")
    @ResponseBody
    public Object listUrl(@PathVariable("roleId") Long roleId) {
        return roleUrlService.listRoleUrlByRoleId(roleId);
    }

    @GetMapping("/add")
    public String add() {
        return "roleurl/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(RoleUrl roleUrl) {
        return roleUrlService.saveRoleUrl(roleUrl);
    }

    @PostMapping("do-save")
    @ResponseBody
    public Object doSave(@RequestBody Role role) {
        return roleUrlService.saveRoleUrlBatch(role);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        RoleUrl roleUrl = roleUrlService.getRoleUrl(id);
        model.addAttribute("roleUrl", roleUrl);
        return "roleurl/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(RoleUrl roleUrl) {
        return roleUrlService.updateRoleUrl(roleUrl);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return roleUrlService.deleteRoleUrl(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        RoleUrl roleUrl = roleUrlService.getRoleUrl(id);
        model.addAttribute("roleUrl", roleUrl);
        return "roleurl/detail";
    }

}
