package com.chengxuunion.generator.common.model;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    分页响应结果
 * @Date:创建时间: 2019-01-07 17:54
 * @Modified By:
 */
public class PageResult<T> {

    /**
     * 当前页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;

    /**
     * 总记录数
     */
    private Long count;

    /**
     * 数据列表
     */
    private List<T> list;

    public PageResult() {

    }

    public PageResult(Long count, List<T> list) {
        this.count = count;
        this.list = list;
    }

    public PageResult(Integer pageNum, Integer pageSize, Long count, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.count = count;
        this.list = list;
    }

    public PageResult(PageParam pageRequest, Long totalSize, List<T> datas) {
        this(pageRequest.getPageNum(), pageRequest.getPageSize(), totalSize, datas);
    }

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
