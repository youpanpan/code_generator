package com.chengxuunion.generator.common.model;

/**
 * @Author youpanpan
 * @Description:    分页请求参数
 * @Date:创建时间: 2019-01-07 18:00
 * @Modified By:
 */
public class PageParam {

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页多少条
     */
    private Integer pageSize = 10;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
