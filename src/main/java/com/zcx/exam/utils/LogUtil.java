package com.zcx.exam.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志打印工具类
 */
public class LogUtil {

    /**
     * 获取日志对象
     * @param clazz 类
     * @return 日志对象
     */
    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * 打印 info 级别的日志
     * @param clazz 类
     * @param message 日志信息
     */
    public static void info(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isInfoEnabled()) {
            logger.info(message);
        }
    }

    /**
     * 打印 info 级别的日志，带异常信息
     * @param clazz 类
     * @param message 日志信息
     * @param e 异常
     */
    public static void info(Class<?> clazz, String message, Throwable e) {
        Logger logger = getLogger(clazz);
        if (logger.isInfoEnabled()) {
            logger.info(message, e);
        }
    }

    /**
     * 打印 debug 级别的日志
     * @param clazz 类
     * @param message 日志信息
     */
    public static void debug(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug(message);
        }
    }

    /**
     * 打印 debug 级别的日志，带异常信息
     * @param clazz 类
     * @param message 日志信息
     * @param e 异常
     */
    public static void debug(Class<?> clazz, String message, Throwable e) {
        Logger logger = getLogger(clazz);
        if (logger.isDebugEnabled()) {
            logger.debug(message, e);
        }
    }

    /**
     * 打印 warn 级别的日志
     * @param clazz 类
     * @param message 日志信息
     */
    public static void warn(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isWarnEnabled()) {
            logger.warn(message);
        }
    }

    /**
     * 打印 warn 级别的日志，带异常信息
     * @param clazz 类
     * @param message 日志信息
     * @param e 异常
     */
    public static void warn(Class<?> clazz, String message, Throwable e) {
        Logger logger = getLogger(clazz);
        if (logger.isWarnEnabled()) {
            logger.warn(message, e);
        }
    }

    /**
     * 打印 error 级别的日志
     * @param clazz 类
     * @param message 日志信息
     */
    public static void error(Class<?> clazz, String message) {
        Logger logger = getLogger(clazz);
        if (logger.isErrorEnabled()) {
            logger.error(message);
        }
    }

    /**
     * 打印 error 级别的日志，带异常信息
     * @param clazz 类
     * @param message 日志信息
     * @param e 异常
     */
    public static void error(Class<?> clazz, String message, Throwable e) {
        Logger logger = getLogger(clazz);
        if (logger.isErrorEnabled()) {
            logger.error(message, e);
        }
    }
}
