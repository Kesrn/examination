package com.zcx.exam.common;

/**
 * 用户权限枚举类，定义了系统中用户可能拥有的不同权限级别
 */
public enum UserEnum {

    /**
     * 普通用户，拥有基本的查看权限
     */
    USER_ROLE("USER", "普通用户"),

    /**
     * 管理员，拥有管理用户和内容的权限
     */
    ADMIN_ROLE("ADMIN", "管理员"),

    /**
     * 性别
     */
    SEX_MALE("1", "男"),
    SEX_FEMALE("2", "女");

    private final String code;
    private final String description;

    UserEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
