package com.chengxuunion.generator.business.contextparaminstance.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 17:31:52
 * @Modified By:
 */
public class ContextParamInstancePageParam extends PageParam{

    /**
     * 上下文ID
     */
    private Long contextId;

    /**
     * 参数实例名称
     */
    private String instanceName;

    /**
     * 状态
     */
    private String status;

    /**
     * 用户ID
     */
    private Long userId;

   public Long getContextId() {
        return contextId;
    }

    public void setContextId(Long contextId) {
        this.contextId = contextId;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}