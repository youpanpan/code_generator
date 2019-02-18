package com.chengxuunion.generator.component.context.basic.service;

import com.chengxuunion.generator.component.context.AbstractDataContext;
import com.chengxuunion.generator.component.context.ExtContext;
import com.chengxuunion.generator.component.context.basic.model.BasicContextParam;
import com.chengxuunion.util.date.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-19 18:05
 * @Modified By:
 */
@Component
public class BasicDataContext extends AbstractDataContext{

    /**
     * 基础上下文参数对象
     */
    private BasicContextParam basicContextParam;

    @Override
    public ExtContext getContext() throws Exception{
        ExtContext context = this.getDataContext().getContext();
        if (basicContextParam == null) {
            return context;
        }

        context.setVariable("author", basicContextParam.getAuthor());
        context.setVariable("version", basicContextParam.getVersion());
        context.setVariable("curDate", DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        return context;
    }

    public BasicContextParam getBasicContextParam() {
        return basicContextParam;
    }

    public void setBasicContextParam(BasicContextParam basicContextParam) {
        this.basicContextParam = basicContextParam;
    }
}
