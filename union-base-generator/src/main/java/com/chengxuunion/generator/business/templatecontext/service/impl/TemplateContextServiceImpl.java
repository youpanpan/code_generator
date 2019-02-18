package com.chengxuunion.generator.business.templatecontext.service.impl;

import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.templatecontext.model.TemplateContext;
import com.chengxuunion.generator.business.templatecontext.service.TemplateContextService;
import com.chengxuunion.generator.business.templatecontext.dao.TemplateContextDao;
import com.chengxuunion.generator.business.templatecontext.model.request.TemplateContextPageParam;


/**
 * @Author youpanpan
 * @Description:    模版依赖的上下文服务实现
 * @Date:创建时间: 2019-01-15 11:49:27
 * @Modified By:
 */
@Service
public class TemplateContextServiceImpl implements TemplateContextService {

    @Autowired
    private TemplateContextDao templateContextDao;

    @Override
    public PageResult<TemplateContext> listTemplateContextPage(TemplateContextPageParam templateContextPageParam) {
        PageHelper.startPage(templateContextPageParam.getPageNum() , templateContextPageParam.getPageSize());
        List<TemplateContext> templateContextList = templateContextDao.listTemplateContext(templateContextPageParam);
        //得到分页的结果对象
        PageInfo<TemplateContext> pageInfo = new PageInfo<>(templateContextList);

        return new PageResult<TemplateContext>(templateContextPageParam, pageInfo.getTotal(), templateContextList);
    }

    @Override
    public List<TemplateContext> listTemplateContextByTemplateId(Long templateId) {
        return templateContextDao.listTemplateContextByTemplateId(templateId);
    }

    /**
     * 根据主键查询单个模版依赖的上下文对象
     *
     * @param id 主键
     * @return  单个模版依赖的上下文对象
     */
    @Override
    public TemplateContext getTemplateContext(Long id) {
        return templateContextDao.getTemplateContext(id);
    }

    /**
     * 保存模版依赖的上下文对象
     *
     * @param templateContext 模版依赖的上下文对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveTemplateContext(TemplateContext templateContext) {
        templateContext.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        templateContext.setCreateDate(nowDate);
        templateContext.setCreateUserId(SessionUtils.getUser().getId());
        return templateContextDao.saveTemplateContext(templateContext);
    }

    @Override
    public int saveTemplateContextBatch(List<TemplateContext> templateContextList) {
        Date nowDate = new Date();
        for (TemplateContext templateContext : templateContextList) {
            templateContext.setId(IdGenerator.getInstance().nextId());
            templateContext.setCreateDate(nowDate);
        }
        return templateContextDao.saveTemplateContextBatch(templateContextList);
    }

    /**
     * 更新模版依赖的上下文对象
     *
     * @param templateContext 模版依赖的上下文对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateTemplateContext(TemplateContext templateContext) {
        return  templateContextDao.updateTemplateContext(templateContext);
    }

    /**
     * 根据主键删除模版依赖的上下文
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteTemplateContext(Long id) {
        return  templateContextDao.deleteTemplateContext(id);
    }

    @Override
    public int deleteTemplateContextByTemplateId(Long tempalteId) {
        return templateContextDao.deleteTemplateContextByTemplateId(tempalteId);
    }
}

