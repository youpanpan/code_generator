package com.chengxuunion.generator.component.context.database.service.jdbc.exception;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2018-12-14 17:10
 * @Modified By:
 */
public class DataBaseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DataBaseException() {
        super();
    }

    public DataBaseException(String message) {
        super(message);
    }

    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataBaseException(Throwable cause) {
        super(cause);
    }

    protected DataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
