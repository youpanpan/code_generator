package com.chengxuunion.generator.business.user.model.request;

import com.chengxuunion.generator.common.model.PageParam;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-16 13:54:23
 * @Modified By:
 */
public class UserPageParam extends PageParam{

    private String userName;

    /**
     * 状态，0：禁用，1：启用
     */
    private String status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}