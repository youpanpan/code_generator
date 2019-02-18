package com.chengxuunion.generator.business.url.dao;

import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.model.request.UrlPageParam;

import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:    系统URLDao
 * @Date:创建时间: 2019-01-16 17:40:36
 * @Modified By:
 */
@Repository
public interface UrlDao {

    /**
     * 查询系统URL列表
     *
     * @param urlPageParam  参数对象
     * @return  系统URL列表
     */
    List<Url> listUrl(UrlPageParam urlPageParam);

    /**
     * 查询用户能访问的URL列表
     *
     * @param userId
     * @return
     */
    List<Url> listUrlByUserId(@Param("userId") Long userId);

    /**
     * 查询用户能访问的菜单URL
     *
     * @param userId
     * @return
     */
    List<Url> listMenuUrlByUserId(@Param("userId") Long userId);

    /**
     * 查询系统所有URL列表（递归查询子级节点）
     *
     * @return
     */
    List<Url> listAllUrl();

    /**
     * 根据父菜单ID下的所有子菜单
     *
     * @param parentId
     * @return
     */
    List<Url> listUrlByParentId(Long parentId);

    /**
     * 根据主键查询单个系统URL对象
     *
     * @param id 主键
     * @return  单个系统URL对象
     */
    Url getUrl(@Param("id") Long id);

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
    int deleteUrl(@Param("id") Long id);

    /**
     * 批量删除URL
     *
     * @param urlIdList
     * @return
     */
    int deleteUrlBatch(List<Long> urlIdList);
}