package com.chengxuunion.generator.business.index.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-24 14:43
 * @Modified By:
 */
@Repository
public interface IndexDao {

    /**
     * 获取数量统计
     *
     * @return
     */
    List<Map<String, Object>> listNumberStatistics();

    /**
     * 代码数量变化趋势
     *
     * @param type
     * @return
     */
    List<Map<String, Object>> listCodeNumber(@Param("type") Integer type);

}
