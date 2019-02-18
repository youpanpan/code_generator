package com.chengxuunion.generator.business.codegenerate.controller;

import com.chengxuunion.generator.business.codegenerate.model.CodeGenerate;
import com.chengxuunion.generator.business.codegenerate.model.request.CodeGeneratePageParam;
import com.chengxuunion.generator.business.codegenerate.model.request.GenerateCodeParam;
import com.chengxuunion.generator.business.codegenerate.service.CodeGenerateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author youpanpan
 * @Description:    代码生成记录控制器
 * @Date:创建时间: 2019-01-10 14:49:05
 * @Modified By:
 */
@Controller
@RequestMapping("/codegenerate/manager")
public class CodeGenerateController extends BaseController {

    @Autowired
    private CodeGenerateService codeGenerateService;

    @GetMapping
    public String index() {
        return "codegenerate/index";
    }

    @GetMapping("/list")
    @ResponseBody
    public Object listPage(CodeGeneratePageParam codeGeneratePageParam) {
        return codeGenerateService.listCodeGeneratePage(codeGeneratePageParam);
    }

    @GetMapping("/generate")
    public String generate() {
        return "codegenerate/generate";
    }

    @PostMapping("/do-generate")
    @ResponseBody
    public Object doGenerate(@RequestBody GenerateCodeParam generateCodeParam) {
        Boolean success = codeGenerateService.generateCode(generateCodeParam);
        return success ? success : null;
    }

    @GetMapping("/add")
    public String add() {
        return "codegenerate/add";
    }

    @PostMapping("/do-add")
    @ResponseBody
    public Object doAdd(CodeGenerate codeGenerate) {
        return codeGenerateService.saveCodeGenerate(codeGenerate);
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {
        CodeGenerate codeGenerate = codeGenerateService.getCodeGenerate(id);
        model.addAttribute("codeGenerate", codeGenerate);
        return "codegenerate/update";
    }

    @PutMapping("/do-update")
    @ResponseBody
    public Object doUpdate(CodeGenerate codeGenerate) {
        return codeGenerateService.updateCodeGenerate(codeGenerate);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public Object doDelete(@PathVariable("id") Long id) {
        return codeGenerateService.deleteCodeGenerate(id);
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        CodeGenerate codeGenerate = codeGenerateService.getCodeGenerate(id);
        model.addAttribute("codeGenerate", codeGenerate);
        return "codegenerate/detail";
    }

    @GetMapping("/download/{id}")
    public String downloadCode(HttpServletResponse response, @PathVariable("id") Long id) {
        Boolean success = codeGenerateService.downloadCode(id, response);
        if (success) {
            return null;
        } else {
            return "public/file-notfind";
        }
    }
}
