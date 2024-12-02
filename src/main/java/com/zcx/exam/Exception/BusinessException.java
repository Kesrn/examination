package com.zcx.exam.Exception;

/**
 * 自定义业务异常类，用于在业务逻辑中抛出自定义异常
 */
public class BusinessException extends RuntimeException {

    /**
     * 异常错误码
     */
    private final String errorCode;

    /**
     * 使用异常消息创建自定义异常
     *
     * @param message 异常消息
     */
    public BusinessException(String message) {
        super(message);
        this.errorCode = "DEFAULT_ERROR";
    }

    /**
     * 使用异常消息和错误码创建自定义异常
     *
     * @param message   异常消息
     * @param errorCode 错误码
     */
    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 使用异常消息、错误码和根异常创建自定义异常
     *
     * @param message   异常消息
     * @param errorCode 错误码
     * @param cause     根异常
     */
    public BusinessException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    /**
     * 获取异常错误码
     *
     * @return 异常错误码
     */
    public String getErrorCode() {
        return errorCode;
    }
}
