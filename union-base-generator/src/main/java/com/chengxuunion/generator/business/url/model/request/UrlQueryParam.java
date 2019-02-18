package com.chengxuunion.generator.business.url.model.request;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-17 10:11
 * @Modified By:
 */
public class UrlQueryParam {

    /**
     * 状态，0：禁用，1：启用
     */
    private String status;

    /**
     * 是否菜单，0：否，1：是
     */
    private String menu;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
