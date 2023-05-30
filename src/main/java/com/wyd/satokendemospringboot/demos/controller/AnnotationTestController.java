package com.wyd.satokendemospringboot.demos.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.common.result.MyResultUtil;
import com.wyd.satokendemospringboot.demos.dao.MyUserDao;
import com.wyd.satokendemospringboot.demos.entity.MyUser;
import com.wyd.satokendemospringboot.demos.util.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class AnnotationTestController {

    @Resource
    private MyUserDao myUserDao;

    private Map<Long, String> userIdAndName = new HashMap<>();

    @SaCheckLogin
    @RequestMapping(value = "/readArticals", method = RequestMethod.GET)
    public MyResult readArticals(){
        // 登录后才能调用的接口
        MyResult myResult = new MyResult<>();
        setUser();
        myResult.setData(userIdAndName.get( Long.valueOf((String) StpUtil.getLoginId()) ) + "阅读中...");
        return MyResultUtil.getTrueResult(myResult);
    }



    @SaCheckPermission(Constants.WRITE_ARTICLE)
    @RequestMapping(value = "/writeArticals", method = RequestMethod.GET)
    public MyResult writeArticals(){
        MyResult myResult = new MyResult<>();
        setUser();
        myResult.setData(userIdAndName.get( Long.valueOf((String) StpUtil.getLoginId()) ) +  "写作中...");
        return MyResultUtil.getTrueResult(myResult);
    }

    @SaCheckRole( value = {Constants.GOODS_ADMIN, Constants.ADMIN}, mode = SaMode.OR)
    @RequestMapping( value = "/deleteGoods", method = RequestMethod.GET)
    public MyResult deleteGoods(){
        MyResult myResult = new MyResult<>();
        setUser();
        myResult.setData(userIdAndName.get( Long.valueOf((String) StpUtil.getLoginId()) ) + "删除商品中...");
        return MyResultUtil.getTrueResult(myResult);
    }

    private void setUser() {
        Long loginId =  Long.valueOf( (String) StpUtil.getLoginId()) ;
        if (userIdAndName.get(loginId) == null) {
            MyUser myUser = myUserDao.queryById(loginId);
            userIdAndName.put(loginId, myUser.getUserName());
        }
    }

}
