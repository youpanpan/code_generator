package com.chengxuunion.generator.business.engine.controller;

import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.model.request.EnginePageParam;
import com.chengxuunion.generator.business.engine.service.EngineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

/**
 * @Author youpanpan
 * @Description:    模版解析引擎控制器
 * @Date:创建时间: 2019-01-15 09:38:46
 * @Modified By:
 */
@Controller
@RequestMapping("/engine/manager")
public class EngineController extends BaseController {

    @Autowired
    private EngineService engineService;

    @GetMapping
    public String index() {
        return "engine/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(EnginePageParam enginePageParam) {
        return engineService.listEnginePage(enginePageParam);
    }

    @GetMapping("/add")
    public String add() {
        return "engine/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(Engine engine) {
        return engineService.saveEngine(engine);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        Engine engine = engineService.getEngine(id);
        model.addAttribute("engine", engine);
        return "engine/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(Engine engine) {
        return engineService.updateEngine(engine);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return engineService.deleteEngine(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Engine engine = engineService.getEngine(id);
        model.addAttribute("engine", engine);
        return "engine/detail";
    }

}
