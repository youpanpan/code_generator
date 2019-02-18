package com.chengxuunion.generator.business.index.service.impl;

import com.chengxuunion.generator.business.index.dao.IndexDao;
import com.chengxuunion.generator.business.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-24 14:42
 * @Modified By:
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private IndexDao indexDao;

    @Override
    public Map<String, Object> getNumberStatistics() {
        List<Map<String, Object>> list = indexDao.listNumberStatistics();

        Map<String, Object> map = new HashMap<>();
        for (Map<String, Object> data : list) {
            map.put(data.get("typeName").toString(), data.get("total"));
        }

        return map;
    }

    @Override
    public Map<String, Object> getCodeNumber(Integer type) {
        List<Map<String, Object>> list = indexDao.listCodeNumber(type);
        List<Object> xAxisList = new LinkedList<>();
        List<Object> yAxisList = new LinkedList<>();
        for (Map<String, Object> data : list) {
            xAxisList.add(data.get("k"));
            yAxisList.add(data.get("v"));
        }

        Map<String, Object> map = new HashMap<>();
        map.put("xAxis", xAxisList);
        map.put("yAxis", yAxisList);
        return map;
    }
}
