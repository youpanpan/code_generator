package com.chengxuunion.generator.component.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 17:09
 * @Modified By:
 */
public abstract class AbstractDataContext implements DataContext {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    private DataContext dataContext;

    public void setDataContext(DataContext dataContext) {
        this.dataContext = dataContext;
    }

    public DataContext getDataContext() {
        return dataContext;
    }

}
