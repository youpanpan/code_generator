package com.chengxuunion.generator.component.context;

/**
 * @Author youpanpan
 * @Description:    数据上下文接口
 * @Date:创建时间: 2018-12-19 17:06
 * @Modified By:
 */
public interface DataContext {

    /**
     * 获取上下文对象
     *
     * @return  ExtContext对象
     * @throws Exception
     */
    ExtContext getContext() throws Exception;
}
