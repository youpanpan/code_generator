package com.chengxuunion.generator.component.context.simple;

import com.chengxuunion.generator.component.context.AbstractDataContext;
import com.chengxuunion.generator.component.context.ExtContext;

import java.util.HashMap;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 17:14
 * @Modified By:
 */
public class DefaultDataContext extends AbstractDataContext {

    @Override
    public ExtContext getContext() {
        return new ExtContext(new HashMap<>());
    }
}
