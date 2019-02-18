package com.chengxuunion.generator.business.url.model;

import java.util.Date;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    系统URL
 * @Date:创建时间: 2019-01-16 17:40:36
 * @Modified By:
 */
public class Url {

    
    /**
     * 序号，主键
     * 是否允许为空：NO
     */
    private Long id;

    /**
     * URL名称
     * 是否允许为空：YES
     */
    private String urlName;

    /**
     * URL英文名称
     */
    private String urlNameEnglish;

    /**
     * URL地址
     * 是否允许为空：YES
     */
    private String url;

    /**
     * 请求方法，GET/POST/PUT/DELETE
     * 是否允许为空：YES
     */
    private String method;

    /**
     * 是否菜单，0：否，1：是
     * 是否允许为空：YES
     */
    private String menu;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 父链接地址ID
     * 是否允许为空：YES
     */
    private Long parentId;

    /**
     * 是否启用，0：禁用，1：启用
     * 是否允许为空：YES
     */
    private String status;

    /**
     * 创建时间
     * 是否允许为空：YES
     */
    private Date createDate;

    /**
     * 修改时间
     * 是否允许为空：YES
     */
    private Date updateDate;

    /**
     * 排序号
     * 是否允许为空：YES
     */
    private Integer orderNum;

    /**
     * 创建用户ID
     * 是否允许为空：YES
     */
    private Long createUserId;

    /**
     * 临时字段
     */
    private Long userId;

    /**
     * 父URL
     */
    private Url parentUrl;

    /**
     * 子URL列表
     */
    private List<Url> childUrlList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getUrlNameEnglish() {
        return urlNameEnglish;
    }

    public void setUrlNameEnglish(String urlNameEnglish) {
        this.urlNameEnglish = urlNameEnglish;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Url getParentUrl() {
        return parentUrl;
    }

    public void setParentUrl(Url parentUrl) {
        this.parentUrl = parentUrl;
    }

    public List<Url> getChildUrlList() {
        return childUrlList;
    }

    public void setChildUrlList(List<Url> childUrlList) {
        this.childUrlList = childUrlList;
    }
}