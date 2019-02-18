package com.chengxuunion.generator.component.context.database.model;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-17 15:57
 * @Modified By:
 */
public class ParamDesc {

    /**
     * 参数名
     */
    private String paramName;

    /**
     * 参数类型
     */
    private String paramType;

    /**
     * 参数修饰符
     */
    private String qualifier;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
}
