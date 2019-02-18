package com.chengxuunion.generator.business.url.service.impl;

import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.url.model.Url;
import com.chengxuunion.generator.business.url.service.UrlService;
import com.chengxuunion.generator.business.url.dao.UrlDao;
import com.chengxuunion.generator.business.url.model.request.UrlPageParam;


/**
 * @Author youpanpan
 * @Description:    系统URL服务实现
 * @Date:创建时间: 2019-01-16 17:40:36
 * @Modified By:
 */
@Service
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlDao urlDao;

    @Override
    public PageResult<Url> listUrlPage(UrlPageParam urlPageParam) {
        PageHelper.startPage(urlPageParam.getPageNum() , urlPageParam.getPageSize());
        List<Url> urlList = urlDao.listUrl(urlPageParam);
        //得到分页的结果对象
        PageInfo<Url> pageInfo = new PageInfo<>(urlList);

        return new PageResult<Url>(urlPageParam, pageInfo.getTotal(), urlList);
    }

    @Override
    public List<Url> listUrlByUserId(Long userId) {
        return urlDao.listUrlByUserId(userId);
    }

    @Override
    public List<Url> listMenuUrlByUserId(Long userId) {
        return urlDao.listMenuUrlByUserId(userId);
    }

    @Override
    public List<Url> listAllUrl() {
        return urlDao.listAllUrl();
    }

    /**
     * 根据主键查询单个系统URL对象
     *
     * @param id 主键
     * @return  单个系统URL对象
     */
    @Override
    public Url getUrl(Long id) {
        return urlDao.getUrl(id);
    }

    /**
     * 保存系统URL对象
     *
     * @param url 系统URL对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveUrl(Url url) {
        url.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        url.setCreateDate(nowDate);
        url.setUpdateDate(nowDate);
        url.setCreateUserId(SessionUtils.getUser().getId());
        return urlDao.saveUrl(url);
    }

    /**
     * 更新系统URL对象
     *
     * @param url 系统URL对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateUrl(Url url) {
        url.setUpdateDate(new Date());
        return  urlDao.updateUrl(url);
    }

    /**
     * 根据主键删除系统URL
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteUrl(Long id) {
        List<Url> urlList = urlDao.listUrlByParentId(id);
        List<Long> urlIdList = new ArrayList<>();
        for (Url url : urlList) {
            urlIdList.addAll(getUrlIdList(url));
        }
        if (CollectionUtils.isNotEmpty(urlIdList)) {
            urlDao.deleteUrlBatch(urlIdList);
        }
        return  urlDao.deleteUrl(id);
    }

    /**
     * 获取菜单及子菜单ID集合
     *
     * @param url
     * @return
     */
    private List<Long> getUrlIdList(Url url) {
        if (CollectionUtils.isEmpty(url.getChildUrlList())) {
            return Arrays.asList(url.getId());
        }

        List<Long> urlIdList = new ArrayList<>();
        urlIdList.add(url.getId());
        for (Url childUrl : url.getChildUrlList()) {
            urlIdList.addAll(getUrlIdList(childUrl));
        }

        return urlIdList;
    }

}

