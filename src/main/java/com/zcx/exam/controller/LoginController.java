package com.zcx.exam.controller;

import com.zcx.exam.common.ApiConst;
import com.zcx.exam.common.ResultBody;
import com.zcx.exam.entity.User;
import com.zcx.exam.service.LoginService;
import com.zcx.exam.service.UserService;
import com.zcx.exam.utils.CookieUtils;
import com.zcx.exam.utils.JsonUtil;
import com.zcx.exam.utils.LogUtil;
import com.zcx.exam.utils.ValidateCodeUtils;
import com.zcx.exam.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

// 登录模块
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @GetMapping(ApiConst.API_LOGIN)
    public String toLogin() {
        return "login/login";
    }

    @PostMapping(value = ApiConst.API_LOGIN)
    public String onLogin(LoginVo loginVo,
                          HttpServletRequest request,
                          Model model) {

        try {
            LogUtil.warn(this.getClass(), "登录信息入参："+ JsonUtil.toJson(loginVo));
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                // 获取cookie中的validateCode键值
                String validateCodeUUid = CookieUtils.getCookie(request, "validateCodeUUid");
                String validateCode1 = (String) request.getSession().getAttribute(validateCodeUUid);
                if (!loginVo.getValidateCode().equalsIgnoreCase(validateCode1)) {
                    model.addAttribute("errMsg", "验证码错误");
                    LogUtil.warn(this.getClass(), "验证码错误");
                    return "login/login";
                }
                // todo 增加查询用户状态
                String status = loginService.queryUserStatus(loginVo.getUsername());
                if ("1".equals(status)) {
                    model.addAttribute("errMsg", "不存在该用户，请重新登录");
                    LogUtil.warn(this.getClass(), "不存在该用户，请重新登录");
                    return "login/login";
                }

                UsernamePasswordToken token = new UsernamePasswordToken();
                token.setUsername(loginVo.getUsername());
                token.setPassword(loginVo.getPassword().toCharArray());

                subject.login(token);
            }
            model.addAttribute("model", "9,999,666");
            User user = (User) subject.getPrincipal();
            model.addAttribute("user", user);
            LogUtil.info(this.getClass(), "用户 {"+user.getUsername()+"} 登录成功");
            return "index";
        } catch (UnknownAccountException ex) {
            LogUtil.error(this.getClass(), "登录失败: 用户名或密码错误", ex);
            model.addAttribute("errMsg", "用户名或密码错误");
        } catch (IncorrectCredentialsException ex) {
            LogUtil.error(this.getClass(), "登录失败: 用户名或密码错误", ex);
            model.addAttribute("errMsg", "用户名或密码错误");
        } catch (LockedAccountException ex) {
            LogUtil.error(this.getClass(), "登录失败: 用户已被锁定，请联系管理员处理", ex);
            model.addAttribute("errMsg", "用户已被锁定，请联系管理员处理");
        } catch (AuthenticationException ex) {
            LogUtil.error(this.getClass(), "登录失败: 系统繁忙，请稍后再试", ex);
            model.addAttribute("errMsg", "系统繁忙，请稍后再试");
        } catch(Exception e){
            LogUtil.error(this.getClass(), "登录失败: 系统繁忙，请稍后再试", e);
            model.addAttribute("errMsg", "系统繁忙，请稍后再试");
        }
        return "login/login";
    }

    @GetMapping(ApiConst.API_HOME_CONSOLE)
    public String onHome() {
        return "home/console";
    }

    @GetMapping(ApiConst.API_LOGOUT)
    public String onLogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        LogUtil.info(this.getClass(), "用户已注销");
        return "login/login";
    }

    /**
     * 获取图形验证码
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/getValidateCode")
    public void getValidateCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成uuid随机字符串，并保存到cookie中
        String uuid = UUID.randomUUID().toString();
        Cookie validateCodeUUid = new Cookie("validateCodeUUid", uuid);
        response.addCookie(validateCodeUUid);
        // 生成图形验证码，并保存到缓存中
        String s = ValidateCodeUtils.createImage(request, response);
        request.getSession().setAttribute(uuid, s);
        LogUtil.info(this.getClass(), "生成图形验证码: {"+s+"}");
    }

    @RequestMapping("/initUser")
    public String toAdd() {
        return "user/register";
    }

    @PostMapping("/initUser")
    @ResponseBody
    public ResultBody userAdd(User user, HttpServletRequest request) {
        ResultBody resultBody = new ResultBody();
        try {
            Integer i = userService.findByNameCount(user.getMobile());
            if (i != 0) {
                LogUtil.warn(this.getClass(), "该手机号已被注册，请您更换其他手机号进行注册！");
                return ResultBody.failure("该手机号已被注册，请您更换其他手机号进行注册！");
            }
            userService.initUser(user);
            LogUtil.info(this.getClass(), "用户 {"+user.getUsername()+"} 注册成功" );
        } catch (Exception e) {
            LogUtil.error(this.getClass(), "注册失败: {"+e.getMessage()+"}", e);
            return ResultBody.failure("注册失败，请重试！");
        }
        return ResultBody.success(new ArrayList<>(), "注册成功！");
    }
}
