package com.chengxuunion.generator.business.contextparam.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 16:11:09
 * @Modified By:
 */
public class ContextParamPageParam extends PageParam{

    /**
     * 上下文ID
     */
    private Long contextId;

    /**
     * 参数标识
     */
    private String paramCode;

    /**
     * 状态
     */
    private String status;

    public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getParamCode() {
        return paramCode;
    }

    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}