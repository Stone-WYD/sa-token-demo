package com.wyd.satokendemospringboot.demos.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.common.result.MyResultUtil;
import com.wyd.satokendemospringboot.demos.dao.MyUserDao;
import com.wyd.satokendemospringboot.demos.entity.MyUser;
import com.wyd.satokendemospringboot.demos.entity.query.UserQuery;
import com.wyd.satokendemospringboot.demos.myenum.EnableEnum;
import com.wyd.satokendemospringboot.demos.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private MyUserDao myUserDao;

    @Override
    public MyResult passwordLogin(UserQuery userQuery) {

        MyUser uQuery = new MyUser();
        uQuery.setUserName(userQuery.getUserName());
        uQuery.setUserPassword(userQuery.getPassword());
        List<MyUser> myUsers = myUserDao.queryAllByLimit(uQuery, null);
        if (CollectionUtils.isEmpty(myUsers)) return MyResultUtil.getBussinessErrorResult("用户不存在！");
        MyUser myUser = myUsers.get(0);
        // 封禁
        if (myUser.getEnable()== EnableEnum.BAN_ENUM.getCode()){
            return MyResultUtil.getBussinessErrorResult("您的账号已被封禁，请联系管理员！");
        }

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
