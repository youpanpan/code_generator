package com.chengxuunion.generator.business.childtemplate.service.impl;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.collection.CollectionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.childtemplate.model.ChildTemplate;
import com.chengxuunion.generator.business.childtemplate.service.ChildTemplateService;
import com.chengxuunion.generator.business.childtemplate.dao.ChildTemplateDao;
import com.chengxuunion.generator.business.childtemplate.model.request.ChildTemplatePageParam;


/**
 * @Author youpanpan
 * @Description:    子模版服务实现
 * @Date:创建时间: 2019-01-09 17:25:12
 * @Modified By:
 */
@Service
public class ChildTemplateServiceImpl implements ChildTemplateService {

    @Autowired
    private ChildTemplateDao childTemplateDao;

    @Override
    public PageResult<ChildTemplate> listChildTemplatePage(ChildTemplatePageParam childTemplatePageParam) {
        PageHelper.startPage(childTemplatePageParam.getPageNum() , childTemplatePageParam.getPageSize());
        List<ChildTemplate> childTemplateList = childTemplateDao.listChildTemplate(childTemplatePageParam);
        //得到分页的结果对象
        PageInfo<ChildTemplate> pageInfo = new PageInfo<>(childTemplateList);

        return new PageResult<ChildTemplate>(childTemplatePageParam, pageInfo.getTotal(), childTemplateList);
    }

    @Override
    public List<ChildTemplate> listChildTemplateByParentId(Long parentTemplateId) {
        List<ChildTemplate> childTemplateList = childTemplateDao.listChildTemplateByParentId(parentTemplateId);
        return childTemplateList;
    }

    /**
     * 根据主键查询单个子模版对象
     *
     * @param id 主键
     * @return  单个子模版对象
     */
    @Override
    public ChildTemplate getChildTemplate(Long id) {
        return childTemplateDao.getChildTemplate(id);
    }

    /**
     * 保存子模版对象
     *
     * @param childTemplate 子模版对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveChildTemplate(ChildTemplate childTemplate) {
        childTemplate.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        childTemplate.setCreateDate(nowDate);
        childTemplate.setUpdateDate(nowDate);
        childTemplate.setCreateUserId(SessionUtils.getUser().getId());
        return childTemplateDao.saveChildTemplate(childTemplate);
    }

    @Override
    public int saveChildTemplateBatch(List<ChildTemplate> childTemplateList) {
        List<ChildTemplate> updateList = new ArrayList<>();
        List<ChildTemplate> insertList = new ArrayList<>();

        Date nowDate = new Date();
        for (ChildTemplate childTemplate : childTemplateList) {
            if (childTemplate.getId() == null) {
                childTemplate.setId(IdGenerator.getInstance().nextId());
                childTemplate.setCreateDate(nowDate);
                childTemplate.setUpdateDate(nowDate);
                childTemplate.setStatus(Constants.ENABLE);
                insertList.add(childTemplate);
            } else {
                childTemplate.setUpdateDate(nowDate);
                updateList.add(childTemplate);
            }
        }

        if (CollectionUtils.isNotEmpty(updateList)) {
            childTemplateDao.updateChildTemplateBatch(updateList);
        }
        if (CollectionUtils.isNotEmpty(insertList)) {
            childTemplateDao.saveChildTemplateBatch(insertList);
        }

        return 1;
    }

    /**
     * 更新子模版对象
     *
     * @param childTemplate 子模版对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateChildTemplate(ChildTemplate childTemplate) {
        childTemplate.setUpdateDate(new Date());
        return  childTemplateDao.updateChildTemplate(childTemplate);
    }

    /**
     * 根据主键删除子模版
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteChildTemplate(Long id) {
        return  childTemplateDao.deleteChildTemplate(id);
    }

    @Override
    public int deleteChildTemplateByParentId(Long parentTemplateId) {
        return childTemplateDao.deleteChildTemplateByParentId(parentTemplateId);
    }
}

