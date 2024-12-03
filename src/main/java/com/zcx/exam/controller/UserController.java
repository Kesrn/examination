package com.zcx.exam.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zcx.exam.common.ApiConst;
import com.zcx.exam.common.Consts;
import com.zcx.exam.common.ResultBody;
import com.zcx.exam.entity.User;
import com.zcx.exam.service.UserService;
import com.zcx.exam.utils.LogUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

//用户模块
@Controller
@RequestMapping(ApiConst.API_USER)
public class UserController {

    @Autowired
    private UserService userService;

    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping()
    public String toList() {
        return "user/list";
    }

    @GetMapping(ApiConst.API_USER_LIST)
    @ResponseBody
    public ResultBody userList(@RequestParam(name = "page") Integer currPage,
                               @RequestParam("limit") Integer pageSize,
                               @RequestParam(value = "username", required = false) String username,
                               @RequestParam(value = "mobile", required = false) String mobile,
                               @RequestParam(value = "startDate", required = false) String startDate,
                               @RequestParam(value = "endDate", required = false) String endDate) {
        ResultBody resultBody = new ResultBody();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("username", username);
            map.put("mobile", mobile);
            map.put("startDate", startDate);
            map.put("endDate", endDate);
            PageHelper.startPage(currPage, pageSize);
            List<User> list = userService.selectAll(map);

            PageInfo<User> pageInfo = new PageInfo<>(list);
            resultBody.setCode(Consts.SUCCESS);
            resultBody.setMsg("查询成功！");
            resultBody.setData(list);
            resultBody.setCount(Long.valueOf(pageInfo.getTotal()));
        } catch (Exception e) {
            LogUtil.error(UserController.class, "查询用户列表失败", e);
            resultBody = ResultBody.failure("系统繁忙，请稍后重试！");
        }


        return resultBody;
    }


    @GetMapping(ApiConst.API_USER_CREATE)
    public String toAdd() {
        return "user/add";
    }

    @PostMapping(ApiConst.API_USER_CREATE)
    @ResponseBody
    public ResultBody userAdd(User user) {
        try {
            Integer i = userService.findByNameCount(user.getMobile());
            if (i != 0) {
                return ResultBody.failure("已存在该手机号码，请您更换手机号码！");
            }
            userService.addUser(user);
        } catch (Exception e) {
            LogUtil.error(UserController.class, "录入用户数据失败", e);
            return ResultBody.failure("用户录入数据失败，请重试！");
        }
        return ResultBody.success(new ArrayList<>(), "录入成功！");
    }

    @DeleteMapping(ApiConst.API_USER_DELETE)
    @ResponseBody
    public ResultBody userDel(@PathVariable("id") int id) {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            LogUtil.error(UserController.class, "删除用户数据失败", e);
            return ResultBody.failure("删除用户数据失败，请重试！");
        }
        return ResultBody.success(new ArrayList<>(), "删除成功！");
    }

    @GetMapping(ApiConst.API_USER_UPDATE)
    public String toUpdate(@PathVariable("id") int id, Model model) {
        User user = userService.findOne(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PostMapping(ApiConst.API_USER_UPDATE)
    @ResponseBody
    public ResultBody userUpdate(User user) {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            LogUtil.error(UserController.class, "更新用户数据失败", e);
            return ResultBody.failure("更新用户数据失败，请重试！");
        }
        return ResultBody.success(new ArrayList<>(), "更新成功！");
    }

    @GetMapping("/{id}/reset")
    @ResponseBody
    public ResultBody resetPassword(@PathVariable("id") Integer id) {
        try {
            userService.resetPassword(id);

        } catch (Exception e) {
            LogUtil.error(UserController.class, "重置密码失败", e);
            return ResultBody.failure("重置密码失败，请重试！");
        }
        return ResultBody.success(new ArrayList<>(), "更新成功！");
    }
}
