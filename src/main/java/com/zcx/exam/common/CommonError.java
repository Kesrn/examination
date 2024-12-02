package com.zcx.exam.common;

/**
 * 常见异常枚举类，定义了系统中可能出现的各种异常类型及其对应的错误码和描述信息
 */
public enum CommonError {

    /**
     * 通用错误
     */
    GENERIC_ERROR("GENERIC_ERROR", "通用错误"),

    /**
     * 参数无效
     */
    INVALID_PARAMETER("INVALID_PARAMETER", "参数无效"),

    /**
     * 用户未找到
     */
    USER_NOT_FOUND("USER_NOT_FOUND", "用户未找到"),

    /**
     * 用户已存在
     */
    USER_ALREADY_EXISTS("USER_ALREADY_EXISTS", "用户已存在"),

    /**
     * 密码错误
     */
    PASSWORD_ERROR("PASSWORD_ERROR", "密码错误"),

    /**
     * 权限不足
     */
    INSUFFICIENT_PERMISSIONS("INSUFFICIENT_PERMISSIONS", "权限不足"),

    /**
     * 数据库操作失败
     */
    DATABASE_OPERATION_FAILED("DATABASE_OPERATION_FAILED", "数据库操作失败"),

    /**
     * 服务不可用
     */
    SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", "服务不可用"),

    /**
     * 内部服务器错误
     */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "内部服务器错误");


    private final String errorCode;
    private final String description;

    CommonError(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
