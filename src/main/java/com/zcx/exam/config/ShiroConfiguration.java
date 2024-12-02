/**
 * All rights Reserved, Designed By ZC.LangFang
 * @Title:  ShiroConfiguration.java
 * @author: XueYang.Li
 * @date:   2019/3/1 9:33 AM
 * @version V1.0
 * @Copyright: 2019/3/1 INM Inc. All rights reserved.
 * 注意：本内容仅限于河北志晟信息技术股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.zcx.exam.config;


import com.zcx.exam.common.Consts;
import com.zcx.exam.config.security.Authentication;
import com.zcx.exam.common.ApiConst;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* @Title: ShiroConfiguration
* @Package: com.zc.partymember.config
* @Describe: Shiro配置
* @author: Changxuan.Zhai
* @date: 2019/3/1 9:33 AM
* @version: V1.0
**/
@Configuration
public class ShiroConfiguration {
    /**
     * 创建ShiroFilterFactoryBean实例
     * 用于定义Shiro过滤器，该过滤器负责拦截Web请求，并根据用户的身份执行相应的操作
     *
     * @param shiroException 从配置文件中读取的Shiro异常配置，用于动态设置不需要认证的URL
     * @return 返回配置好的ShiroFilterFactoryBean实例
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(
            @Value("${shiro.exceptions}") String shiroException) {

        // 创建ShiroFilterFactoryBean对象
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器，负责整体安全控制
        bean.setSecurityManager(securityManager());
        //配置登录的url和登录成功的url
        bean.setLoginUrl("/login");
        // 自定义过滤器，用于处理特定请求
        Map<String, Filter> filtersMap = bean.getFilters();
        bean.setFilters(filtersMap);
        // 配置访问权限，定义哪些URL需要进行认证，哪些不需要
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 添加静态资源例外，这些资源不需要认证即可访问
        filterChainDefinitionMap.put(ApiConst.STATIC_CSS, Consts.ANON);
        filterChainDefinitionMap.put(ApiConst.STATIC_JS, Consts.ANON);
        filterChainDefinitionMap.put("/**/*.eot", Consts.ANON);
        filterChainDefinitionMap.put("/**/*.svg", Consts.ANON);
        filterChainDefinitionMap.put("/**/*.ttf", Consts.ANON);
        filterChainDefinitionMap.put("/**/*.woff", Consts.ANON);
        filterChainDefinitionMap.put("/**/*.jpg", Consts.ANON);
        // 处理配置文件中的例外URL，动态添加不需要认证的URL
        if (!StringUtils.isEmpty(shiroException)) {
            String[] shiroExceptions = shiroException.split(",");
            for (String strUrl : shiroExceptions) {
                filterChainDefinitionMap.put(strUrl, Consts.ANON);
            }
        }
        // 配置所有其他请求需要认证
        filterChainDefinitionMap.put(ApiConst.ALL_1, Consts.AUTHC);
        filterChainDefinitionMap.put(ApiConst.ALL_2, Consts.AUTHC);
        filterChainDefinitionMap.put(ApiConst.ALL_3, Consts.AUTHC);
        // 将定义好的访问权限配置到Shiro过滤器中
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 返回配置好的ShiroFilterFactoryBean实例
        return bean;
    }

    /**
     * 创建并配置SecurityManager实例
     * SecurityManager是Shiro框架中的核心安全管理器接口，负责管理应用程序中的所有安全操作
     * 此方法定义了一个Bean，由Spring容器管理其生命周期
     *
     * @return DefaultWebSecurityManager实例，配置了自定义的Realm和SessionManager
     */
    @Bean
    public DefaultWebSecurityManager securityManager() {
        // 实例化DefaultWebSecurityManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        // 设置自定义的Realm，Realm用于从数据源获取用户信息和权限数据
        securityManager.setRealm(realm());

        // 设置自定义的WebSessionManager，用于管理Web环境下的用户会话
        securityManager.setSessionManager(webSessionManager());

        // 返回配置好的SecurityManager实例
        return securityManager;
    }

    /**
     * 配置并返回一个Realm实例
     *
     * Realm在Shiro框架中作为安全数据源，用于提供用户身份和权限信息
     * 这个方法主要负责创建和配置Authentication对象，并设置其凭证匹配器
     *
     * @return 配置好的Realm实例，用于用户身份验证和授权
     */
    @Bean
    public Realm realm() {
        // 创建Authentication对象，用于配置用户身份验证和授权的相关信息
        Authentication authentication = new Authentication();
        // 设置凭证匹配器，用于验证用户提供的凭证（如密码）与系统中存储的凭证是否匹配
        authentication.setCredentialsMatcher(credentialsMatcher());
        // 返回配置好的Authentication对象作为Realm实例
        return authentication;
    }

    /**
     * 配置并创建WebSessionManager的Bean
     * 该Bean用于管理Web session，包括设置session ID的cookie属性等
     *
     * @return WebSessionManager实例，用于Spring的依赖注入
     */
    @Bean
    public WebSessionManager webSessionManager() {
        // 创建DefaultWebSessionManager实例
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();

        // 创建一个简单的Cookie用于保存session ID
        Cookie cookie = new SimpleCookie();
        // 设置cookie的名称为"question_session"
        cookie.setName("question_session");
        // 设置cookie的最大存活时间为1小时（60分钟）
        // 这意味着cookie将在用户不活动1小时后过期
        cookie.setMaxAge(60*60);

        // 将配置好的cookie设置给sessionManager，用于管理session ID
        sessionManager.setSessionIdCookie(cookie);

        // 返回配置好的sessionManager实例
        return sessionManager;
    }

    /**
     * 配置并返回一个凭证匹配器实例
     * 该方法使用MD5算法和多次迭代来安全地验证用户凭证
     *
     * @return CredentialsMatcher 实例，用于用户身份验证时的密码匹配
     */
    @Bean
    public CredentialsMatcher credentialsMatcher() {
        // 创建一个基于哈希的凭证匹配器实例
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 设置哈希算法为MD5，用于密码加密
        matcher.setHashAlgorithmName("MD5");
        // 设置哈希迭代次数为1024，增加密码破解难度
        matcher.setHashIterations(1024);
        return matcher;
    }
}
