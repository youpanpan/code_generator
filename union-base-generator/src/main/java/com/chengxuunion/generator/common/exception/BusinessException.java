package com.chengxuunion.generator.common.exception;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-21 11:34
 * @Modified By:
 */
public class BusinessException extends RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 状态码
     */
    private int code;

    public BusinessException() {
        super();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }


    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
