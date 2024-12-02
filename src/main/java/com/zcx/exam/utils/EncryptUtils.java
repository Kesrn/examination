package com.zcx.exam.utils;

import org.apache.commons.lang3.StringUtils;

public class EncryptUtils {

    // 手机号码前三后四脱敏
    public static String mobileEncrypt(String mobile){
        if(StringUtils.isEmpty(mobile) || mobile.length()!=11){
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    // 身份证前三后四脱敏
    public static String idCardEncrypt(String idCard){
        if(StringUtils.isEmpty(idCard) || (idCard.length() != 15 && idCard.length() != 18)){
            return idCard;
        }
        return idCard.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
    }

    // 姓名第一位脱敏(不考虑复姓，特殊姓氏)
    public static String nameEncrypt(String name){
        if(StringUtils.isEmpty(name) || name.length() < 1){
            return name;
        }
        return name.replaceAll("(?<=[\\u4e00-\\u9fa5]{1})[\\u4e00-\\u9fa5]", "*");
    }

}