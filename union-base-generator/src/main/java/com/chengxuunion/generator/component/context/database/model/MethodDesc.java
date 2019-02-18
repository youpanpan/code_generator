package com.chengxuunion.generator.component.context.database.model;

import java.util.List;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-17 15:56
 * @Modified By:
 */
public class MethodDesc {

    /**
     * 方法修饰符，例public,public static
     */
    private String qualifier;

    /**
     * 返回类型
     */
    private String returnType;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 方法参数列表
     */
    private List<ParamDesc> paramDescList;

    /**
     * 方法参数描述字符串
     */
    private String paramDesc;

    /**
     * 方法体
     */
    private String methodBody;

    public String getQualifier() {
        return qualifier;
    }

    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<ParamDesc> getParamDescList() {
        return paramDescList;
    }

    public void setParamDescList(List<ParamDesc> paramDescList) {
        this.paramDescList = paramDescList;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public String getParamDesc() {
        return paramDesc;
    }

    public void setParamDesc(String paramDesc) {
        this.paramDesc = paramDesc;
    }
}
