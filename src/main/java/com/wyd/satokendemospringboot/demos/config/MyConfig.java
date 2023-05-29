package com.wyd.satokendemospringboot.demos.config;

import com.wyd.satokendemospringboot.demos.entity.Permission;
import com.wyd.satokendemospringboot.demos.entity.po.PermissionPo;
import com.wyd.satokendemospringboot.demos.service.PermissionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MyConfig {

    @Resource
    private PermissionService permissionService;


    @Bean("permissionMap")
    public Map<String, List<PermissionPo>> getPermissionMap(){
        Map<String, List<PermissionPo>> permissionMap = new ConcurrentHashMap<>();

        return permissionMap;
    }
}
