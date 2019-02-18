package com.chengxuunion.generator.business.contextparamvalue.controller;

import com.chengxuunion.generator.business.contextparamvalue.model.ContextParamValue;
import com.chengxuunion.generator.business.contextparamvalue.service.ContextParamValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.chengxuunion.generator.common.controller.BaseController;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下参数实例配置值控制器
 * @Date:创建时间: 2019-01-09 09:52:16
 * @Modified By:
 */
@Controller
@RequestMapping("/contextparamvalue/manager")
public class ContextParamValueController extends BaseController {

    @Autowired
    private ContextParamValueService contextParamValueService;

    @GetMapping("/list/{contextId}/{instanceId}")
    @ResponseBody
    public Object listEnable(@PathVariable("contextId") Long contextId, @PathVariable("instanceId") Long instanceId) {

        return contextParamValueService.listContextParamValueEnable(contextId, instanceId);
    }

    @PostMapping("/do-save")
    @ResponseBody
    public Object doSave(@RequestBody List<ContextParamValue> contextParamValueList) {
        return contextParamValueService.saveContextParamValueBatch(contextParamValueList);
    }

    @GetMapping("/listdetail/{contextId}/{instanceId}")
    public String listDetailPage(Model model, @PathVariable("contextId") Long contextId, @PathVariable("instanceId") Long instanceId) {
        model.addAttribute("contextId", contextId);
        model.addAttribute("instanceId", instanceId);
        return "contextparamvalue/list-detail-wrapper";
    }

}
