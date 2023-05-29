package com.wyd.satokendemospringboot.demos.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.common.result.MyResultUtil;
import com.wyd.satokendemospringboot.demos.entity.query.UserQuery;
import com.wyd.satokendemospringboot.demos.service.LoginService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Resource
    private LoginService loginService;

    @PostMapping("passwordLogin")
    public MyResult passwordLogin(@Validated(value = UserQuery.loginQuery.class)
                                      @RequestBody UserQuery query){
        // 账号密码登录
        return loginService.passwordLogin(query);
    }

    @PostMapping("telephoneLogin")
    public MyResult telephoneLogin(@Validated(value = UserQuery.telephoneLoginQuery.class)
                                       @RequestBody UserQuery query){
        MyResult myResult = new MyResult();
        // TODO: 2023/5/17 手机号登录逻辑

        return MyResultUtil.getTrueResult(myResult);
    }

    @RequestMapping("/logout")
    public MyResult logout(){
        // 退出登录
        StpUtil.logout();
        return MyResultUtil.getTrueResult(new MyResult());
    }

}
