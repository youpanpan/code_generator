package com.chengxuunion.generator.business.contextparam.service.impl;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

import com.chengxuunion.generator.business.contextparam.model.ContextParam;
import com.chengxuunion.generator.business.contextparam.service.ContextParamService;
import com.chengxuunion.generator.business.contextparam.dao.ContextParamDao;
import com.chengxuunion.generator.business.contextparam.model.request.ContextParamPageParam;


/**
 * @Author youpanpan
 * @Description:    上下文参数服务实现
 * @Date:创建时间: 2019-01-08 16:11:09
 * @Modified By:
 */
@Service
public class ContextParamServiceImpl implements ContextParamService {

    @Autowired
    private ContextParamDao contextParamDao;

    @Override
    public PageResult<ContextParam> listContextParamPage(ContextParamPageParam contextParamPageParam) {
        PageHelper.startPage(contextParamPageParam.getPageNum() , contextParamPageParam.getPageSize());
        List<ContextParam> contextParamList = contextParamDao.listContextParam(contextParamPageParam);
        //得到分页的结果对象
        PageInfo<ContextParam> pageInfo = new PageInfo<>(contextParamList);

        return new PageResult<ContextParam>(contextParamPageParam, pageInfo.getTotal(), contextParamList);
    }

    @Override
    public List<ContextParam> listContextParamEnableByContextId(Long contextId) {
        ContextParamPageParam contextParamPageParam = new ContextParamPageParam();
        contextParamPageParam.setContextId(contextId);
        contextParamPageParam.setStatus(Constants.ENABLE);
        return contextParamDao.listContextParam(contextParamPageParam);
    }

    /**
     * 根据主键查询单个上下文参数对象
     *
     * @param id 主键
     * @return  单个上下文参数对象
     */
    @Override
    public ContextParam getContextParam(Long id) {
        return contextParamDao.getContextParam(id);
    }

    /**
     * 保存上下文参数对象
     *
     * @param contextParam 上下文参数对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveContextParam(ContextParam contextParam) {
        contextParam.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        contextParam.setCreateDate(nowDate);
        contextParam.setUpdateDate(nowDate);
        contextParam.setCreateUserId(SessionUtils.getUser().getId());
        return contextParamDao.saveContextParam(contextParam);
    }

    /**
     * 更新上下文参数对象
     *
     * @param contextParam 上下文参数对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateContextParam(ContextParam contextParam) {
        contextParam.setUpdateDate(new Date());
        return  contextParamDao.updateContextParam(contextParam);
    }

    /**
     * 根据主键删除上下文参数
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteContextParam(Long id) {
        return  contextParamDao.deleteContextParam(id);
    }

}

