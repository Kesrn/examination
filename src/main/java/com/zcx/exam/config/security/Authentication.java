/**
 * All rights Reserved, Designed By ZC.LangFang
 *
 * @Title: Authentication.java
 * @author: XueYang.Li
 * @date: 2019/3/1 9:47 AM
 * @version V1.0
 * @Copyright: 2019/3/1 INM Inc. All rights reserved.
 * 注意：本内容仅限于河北志晟信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.zcx.exam.config.security;


import com.zcx.exam.entity.User;
import com.zcx.exam.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

/**
 * @Title: Authentication
 * @Package: com.zc.partymember.security
 * @Describe: 认证类
 * @author: XueYang.Li
 * @date: 2019/3/1 9:47 AM
 * @version: V1.0
 **/
public class Authentication extends AuthenticatingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * 实现身份验证方法，根据传入的令牌对象获取用户身份信息
     *
     * @param authenticationToken 令牌对象，包含用户提交的登录信息
     * @return AuthenticationInfo 对象，包含用户的身份信息和凭据
     * @throws AuthenticationException 如果验证失败，抛出此异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 将令牌对象转换为 UsernamePasswordToken 类型，以便获取用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();

        // 通过登录服务查询用户名对应的信息
        User user = loginService.selectByName(username);

        // 如果用户不存在或用户名为空，抛出未知账户异常
        if (user == null || StringUtils.isEmpty(user.getUsername())) {
            throw new UnknownAccountException("用户不存在");
        }

        // 生成盐值，用于加密密码
        ByteSource salt = ByteSource.Util.bytes(user.getSalt());

        // 创建并返回身份验证信息对象
        AuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
        return authenticationInfo;
    }


//    public static void main(String[] args) {
//        String md5 = "MD5";
//        String username = "翟常轩";
//        Object password = "123456";
//        String _salt = ByteSource.Util.bytes(username).toString();
//        Object salt = ByteSource.Util.bytes(_salt);
//        int hasIter = 1024;
//
//        Object result = new SimpleHash(md5, password, salt, hasIter);
////        _salt:存入到数据库中的盐值
//        System.out.println(_salt);
//        System.out.println(salt);
//        System.out.println(result);
//    }
}
