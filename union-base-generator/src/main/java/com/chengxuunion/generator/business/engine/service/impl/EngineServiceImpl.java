package com.chengxuunion.generator.business.engine.service.impl;

import com.chengxuunion.generator.common.constant.Constants;
import com.chengxuunion.generator.common.util.SessionUtils;
import com.chengxuunion.util.id.IdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chengxuunion.generator.common.model.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import java.util.Date;

import com.chengxuunion.generator.business.engine.model.Engine;
import com.chengxuunion.generator.business.engine.service.EngineService;
import com.chengxuunion.generator.business.engine.dao.EngineDao;
import com.chengxuunion.generator.business.engine.model.request.EnginePageParam;


/**
 * @Author youpanpan
 * @Description:    模版解析引擎服务实现
 * @Date:创建时间: 2019-01-15 09:38:46
 * @Modified By:
 */
@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    private EngineDao engineDao;

    @Override
    public PageResult<Engine> listEnginePage(EnginePageParam enginePageParam) {
        PageHelper.startPage(enginePageParam.getPageNum() , enginePageParam.getPageSize());
        List<Engine> engineList = engineDao.listEngine(enginePageParam);
        //得到分页的结果对象
        PageInfo<Engine> pageInfo = new PageInfo<>(engineList);

        return new PageResult<Engine>(enginePageParam, pageInfo.getTotal(), engineList);
    }

    @Override
    public List<Engine> listEngineEnable() {
        EnginePageParam enginePageParam = new EnginePageParam();
        enginePageParam.setStatus(Constants.ENABLE);
        List<Engine> engineList = engineDao.listEngine(enginePageParam);
        return engineList;
    }

    /**
     * 根据主键查询单个模版解析引擎对象
     *
     * @param id 主键
     * @return  单个模版解析引擎对象
     */
    @Override
    public Engine getEngine(Long id) {
        return engineDao.getEngine(id);
    }

    /**
     * 保存模版解析引擎对象
     *
     * @param engine 模版解析引擎对象
     * @return  保存影响的记录数
     */
    @Override
    public int saveEngine(Engine engine) {
        engine.setId(IdGenerator.getInstance().nextId());
        Date nowDate = new Date();
        engine.setCreateDate(nowDate);
        engine.setUpdateDate(nowDate);
        engine.setCreateUserId(SessionUtils.getUser().getId());
        return engineDao.saveEngine(engine);
    }

    /**
     * 更新模版解析引擎对象
     *
     * @param engine 模版解析引擎对象
     * @return  更新影响的记录数
     */
    @Override
    public int updateEngine(Engine engine) {
        engine.setUpdateDate(new Date());
        return  engineDao.updateEngine(engine);
    }

    /**
     * 根据主键删除模版解析引擎
     *
     * @param id 主键
     * @return  删除影响的记录数
     */
    @Override
    public int deleteEngine(Long id) {
        return  engineDao.deleteEngine(id);
    }

}

