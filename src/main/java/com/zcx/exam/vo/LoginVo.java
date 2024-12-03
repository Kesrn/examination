package com.zcx.exam.vo;

import com.zcx.exam.common.BaseVo;

/**
 * 登录验证入参vo
 */
public class LoginVo extends BaseVo {
    private String username;
    private String password;
    private String validateCode;
    private String _csrf;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public String getCsrf() {
        return _csrf;
    }

    public void setCsrf(String _csrf) {
        this._csrf = _csrf;
    }
}
