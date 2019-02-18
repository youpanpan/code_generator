package com.chengxuunion.generator.component.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Set;

/**
 * @Author youpanpan
 * @Description:    Context扩展
 * @Date:创建时间: 2018-12-19 17:47
 * @Modified By:
 */
public class ExtContext {

    private static final Logger logger = LoggerFactory.getLogger(ExtContext.class);

    private Map<String, Object> dataMap;

    public ExtContext(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public boolean containsVariable(String var1) {
        return dataMap.containsKey(var1);
    }

    public Set<String> getVariableNames() {
        return dataMap.keySet();
    }

    public Object getVariable(String var1) {
        return dataMap.get(var1);
    }

    public void setVariable(String name, Object value) {
        if (dataMap.containsKey(name)) {
            logger.info("变量【"+ name +"】已存在，变量列表【"+ this.getVariableNames() +"】" );
        }
        dataMap.put(name, value);
    }

    public void setVariables(Map<String, Object> variables) {
        dataMap.putAll(variables);
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

}
