package com.chengxuunion.generator.business.index.service;

import java.util.Map;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-24 14:42
 * @Modified By:
 */
public interface IndexService {

    /**
     * 获取数量统计
     *
     * @return
     */
    Map<String, Object> getNumberStatistics();

    /**
     * 获取代码统计数据
     *
     * @param type
     * @return
     */
    Map<String, Object> getCodeNumber(Integer type);
}
