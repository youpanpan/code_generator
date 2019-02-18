package com.chengxuunion.generator.common.model;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 09:38
 * @Modified By:
 */
public class ResultCode {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 返回结果
     */
    private Object data;

    public ResultCode(Boolean success, Integer code, String message, Object data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResultCode success(Object data) {
        return new ResultCode(true, ErrorCode.OK.getCode(), ErrorCode.OK.getMessage(), data);
    }

    public static ResultCode fail(ErrorCode errorCode, Object data) {
        return new ResultCode(false, errorCode.getCode(), errorCode.getMessage(), data);
    }

    public static ResultCode fail(Integer code, String message, Object data) {
        return new ResultCode(false, code, message, data);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
