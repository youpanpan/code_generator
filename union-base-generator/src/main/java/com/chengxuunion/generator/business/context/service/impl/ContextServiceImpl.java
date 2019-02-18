package com.chengxuunion.generator.business.context.service.impl;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.model.PageResult;
import com.chengxuunion.generator.business.context.model.request.ContextPageParam;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import com.chengxuunion.generator.business.context.model.Context;
import com.chengxuunion.generator.business.context.service.ContextService;
import com.chengxuunion.generator.business.context.dao.ContextDao;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author youpanpan
 * @Description:    上下文实现配置服务实现
 * @Date:创建时间: 2019-01-07 17:43:15
 * @Modified By:
 */
@Service
public class ContextServiceImpl implements ContextService {

    @Autowired
    private ContextDao contextDao;

    @Override
    public PageResult<Context> listContextPage(ContextPageParam contextPageParam) {
        PageHelper.startPage(contextPageParam.getPageNum() , contextPageParam.getPageSize());
        List<Context> contextList = contextDao.listContext(contextPageParam);
        //得到分页的结果对象
        PageInfo<Context> pageInfo = new PageInfo<>(contextList);

        return new PageResult<Context>(contextPageParam, pageInfo.getTotal(), contextList);
    }

    @Override
    public List<Context> listContextEnable() {
        ContextPageParam contextPageParam = new ContextPageParam();
        contextPageParam.setStatus(Constants.ENABLE);
        return contextDao.listContext(contextPageParam);
    }

    /**
     * 根据主键查询单个上下文实现配置对象
     *
     * @param id 主键
     * @return  单个上下文实现配置对象
     */
    @Override
    public Context getContext(Long id) {
        return contextDao.getContext(id);
    }

    /**
     * 保存上下文实现配置对象
     *
     * @param context 上下文实现配置对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveContext(Context context) {
        context.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        context.setCreateDate(nowDate);
        context.setUpdateDate(nowDate);
        context.setCreateUserId(SessionUtils.getUser().getId());
        return contextDao.saveContext(context);
    }

    /**
     * 更新上下文实现配置对象
     *
     * @param context 上下文实现配置对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateContext(Context context) {
        context.setUpdateDate(new Date());
        return  contextDao.updateContext(context);
    }

    /**
     * 根据主键删除上下文实现配置
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteContext(Long id) {
        return  contextDao.deleteContext(id);
    }

}

