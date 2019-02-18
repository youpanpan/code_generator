package com.chengxuunion.generator.business.url.service;

import com.chengxuunion.generator.common.model.PageResult;

import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.model.request.UrlPageParam;

import java.util.List;


/**
 * @Author youpanpan
 * @Description:    系统URL服务接口
 * @Date:创建时间: 2019-01-16 17:40:36
 * @Modified By:
 */
public interface UrlService {

    /**
     * 查询系统URL分页列表
     *
     * @param urlPageParam  参数对象
     * @return  系统URL列表
     */
    PageResult<Url> listUrlPage(UrlPageParam urlPageParam);

    /**
     * 查询用户能访问的URL列表
     *
     * @param userId
     * @return
     */
    List<Url> listUrlByUserId(Long userId);

    /**
     * 查询用户能访问的菜单URL
     *
     * @param userId
     * @return
     */
    List<Url> listMenuUrlByUserId(Long userId);

    /**
     * 查询系统所有URL列表（递归查询子级节点）
     *
     * @return
     */
    List<Url> listAllUrl();

    /**
     * 根据主键查询单个系统URL对象
     *
     * @param id 主键
     * @return  单个系统URL对象
     */
    Url getUrl(Long id);

    /**
     * 保存系统URL对象
     *
     * @param url 系统URL对象
     * @return  保存影响的记录数
     */
    int saveUrl(Url url);

    /**
     * 更新系统URL对象
     *
     * @param url 系统URL对象
     * @return  更新影响的记录数
     */
    int updateUrl(Url url);

    /**
     * 根据主键删除系统URL
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    int deleteUrl(Long id);

}