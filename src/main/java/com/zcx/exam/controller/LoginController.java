package com.zcx.exam.controller;


import com.zcx.exam.common.ApiConst;
import com.zcx.exam.common.ResultBody;
import com.zcx.exam.entity.User;
import com.zcx.exam.service.LoginService;
import com.zcx.exam.service.UserService;
import com.zcx.exam.utils.CookieUtils;
import com.zcx.exam.utils.ObjectUtils;
import com.zcx.exam.utils.ValidateCodeUtils;
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

//登录模块
@Controller
public class LoginController {


    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @GetMapping(ApiConst.API_LOGIN)
    public String toLogin() {
        return "login/login";
    }

    @PostMapping(ApiConst.API_LOGIN)
    public String onLogin(@RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String validateCode,
                          HttpServletRequest request,
                          Model model) {

        try {
            Subject subject = SecurityUtils.getSubject();
            if (!subject.isAuthenticated()) {
                //获取cookie中的validateCode键值
                String validateCodeUUid = CookieUtils.getCookie(request,"validateCodeUUid");
                String validateCode1 = (String) request.getSession().getAttribute(validateCodeUUid);
                if(!validateCode.equalsIgnoreCase(validateCode1)){
                    model.addAttribute("errMsg","验证码错误");
                    return "login/login";
                }
                //todo 增加查询用户状态
                String status = loginService.queryUserStatus(username);
                if("1".equals(status)){
                    model.addAttribute("errMsg","不存在该用户，请重新登录");
                    return "login/login";
                }

                UsernamePasswordToken token = new UsernamePasswordToken();
                token.setUsername(username);
                token.setPassword(password.toCharArray());

                subject.login(token);
            }
            model.addAttribute("model","9,999,666");
            User user = (User) subject.getPrincipal();
            model.addAttribute("user", user);
            return "index";
        }catch (UnknownAccountException ex) {
            ex.printStackTrace();
            model.addAttribute("errMsg", "用户名或密码错误");
        }catch (IncorrectCredentialsException ex) {
            ex.printStackTrace();
            model.addAttribute("errMsg", "用户名或密码错误");
        }catch (LockedAccountException ex) {
            ex.printStackTrace();
            model.addAttribute("errMsg", "用户已被锁定，请联系管理员处理");
        }catch (AuthenticationException ex) {
            ex.printStackTrace();
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
        //生成uuid随机字符串，并保存到cookie中
        String uuid = UUID.randomUUID().toString();
        Cookie validateCodeUUid = new Cookie("validateCodeUUid", uuid);
        response.addCookie(validateCodeUUid);
        //生成图形验证码，并保存到缓存中
        String s = ValidateCodeUtils.createImage(request, response);
//        Cache cache = cacheManager.getCache("validateCode-cache");
//        cache.put(uuid,s);
        request.getSession().setAttribute(uuid,s);
    }

    @RequestMapping("/initUser")
    public String toAdd(){
        return "user/register";
    }
    @PostMapping("/initUser")
    @ResponseBody
    public ResultBody userAdd(User user, HttpServletRequest request){
        ResultBody resultBody = new ResultBody();
        try{
            Integer i = userService.findByNameCount(user.getMobile());
            if (i != 0) {
                return resultBody.failure("该手机号已被注册，请您更换其他手机号进行注册！");
            }
            userService.initUser(user);
        }catch (Exception e){
            e.printStackTrace();
            return resultBody.failure("注册失败，请重试！");
        }
        return resultBody.success(new ArrayList<>(),"注册成功！");
    }
}
