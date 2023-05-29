package com.wyd.satokendemospringboot.demos.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.common.result.MyResultUtil;
import com.wyd.satokendemospringboot.demos.dao.MyUserDao;
import com.wyd.satokendemospringboot.demos.entity.MyUser;
import com.wyd.satokendemospringboot.demos.entity.query.UserQuery;
import com.wyd.satokendemospringboot.demos.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private MyUserDao myUserDao;

    @Override
    public MyResult passwordLogin(UserQuery userQuery) {

        MyUser myUser = myUserDao.queryByNameAndPassword(userQuery.getUserName(), userQuery.getPassword());
        if (myUser == null) return MyResultUtil.getBussinessErrorResult("用户不存在！");

        // 登录成功
        StpUtil.login(myUser.getId());
        // 前后端分离情况下，后端没法返回Cookie给前端
        MyResult myResult = new MyResult();
        myResult.setData(StpUtil.getTokenInfo());
        return MyResultUtil.getTrueResult(myResult);
    }

    @Override
    public MyResult telephoneLogin(UserQuery userQuery) {
        return null;
    }
}
