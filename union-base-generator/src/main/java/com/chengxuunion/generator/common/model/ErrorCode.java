package com.chengxuunion.generator.common.model;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 09:40
 * @Modified By:
 */
public enum ErrorCode {

    OK(200, "成功"),

    BUSINESS_EXCEPTION(400, "业务异常"),

    NOT_AUTH_EXCEPTION(403, "没有权限访问"),

    ERROR(500, "系统异常");

    private int code;

    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
