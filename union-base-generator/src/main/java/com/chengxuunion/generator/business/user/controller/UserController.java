package com.chengxuunion.generator.business.user.controller;

import com.chengxuunion.generator.business.user.model.User;
import com.chengxuunion.generator.business.user.model.request.UserPageParam;
import com.chengxuunion.generator.business.user.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author youpanpan
 * @Description:    用户控制器
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
@Controller
@RequestMapping("/user/manager")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String index() {
        return "user/index";
    }

    @GetMapping("/listselect")
    public String listSelect(Model model, HttpServletRequest request) {
        String ids = request.getParameter("ids");
        String names = request.getParameter("names");
        model.addAttribute("maxNum", request.getParameter("maxNum"));
        model.addAttribute("ids", ids);
        model.addAttribute("names", names);
        return "user/list-select";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(UserPageParam userPageParam) {
        return userService.listUserPage(userPageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "user/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(User user) {
        return userService.updateUser(user);
    }

    @PutMapping("/reset-password/{id}")
    @ResponseBody
    public Object resetPassword(@PathVariable("id") Long id) {
        return userService.resetPassword(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return userService.deleteUser(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/detail";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object uploadHeadPhoto(MultipartFile file) {
        return userService.uploadHeadPhoto(file);
    }

    @GetMapping("/download/{id}")
    public String downloadHeadPhoto(HttpServletResponse response, @PathVariable("id") Long id) {
        Boolean success = userService.downloadHeadPhoto(id, response);
        if (success) {
            return null;
        } else {
            return "public/file-notfind";
        }
    }
}
