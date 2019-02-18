package com.chengxuunion.generator.business.context.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-07 18:09
 * @Modified By:
 */
public class ContextPageParam extends PageParam {

    /**
     * 全类名
     */
    private String className;

    /**
     * 上下文名称
     */
    private String contextName;

    /**
     * 状态，0：禁用，1：启用
     */
    private String status;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
