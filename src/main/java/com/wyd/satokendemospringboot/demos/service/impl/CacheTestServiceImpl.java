package com.wyd.satokendemospringboot.demos.service.impl;

import com.wyd.satokendemospringboot.demos.common.result.MyResult;
import com.wyd.satokendemospringboot.demos.dao.MyUserDao;
import com.wyd.satokendemospringboot.demos.entity.MyUser;
import com.wyd.satokendemospringboot.demos.entity.dto.CacheDTO;
import com.wyd.satokendemospringboot.demos.service.CacheTestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CacheTestServiceImpl implements CacheTestService {

    @Resource
    private MyUserDao myUserDao;


    /*
    * 存在缓存时取出，不存在则重新获取
    * */
    @Cacheable(cacheNames = "user_", key = "#userId")
    @Override
    public CacheDTO queryUserUseCaffineCache(Long userId) {
        MyUser myUser = myUserDao.queryById(userId);
        return new CacheDTO(myUser.getUserName(), myUser.getUserPassword(), new Date());
    }

    /*
    * 不管缓存是否存在，都会重新刷新缓存
    * */
    @CachePut(cacheNames = "user_", key = "#userId")
    @Override
    public CacheDTO udpateUserUseCaffineCache(Long userId) {
        return this.queryUserUseCaffineCache(userId);
    }


    /*
    * 使缓存失效
    * */
    @CacheEvict(cacheNames = "user_", key = "#userId", condition = "#userId == 1L")
    @Override
    public String removeUserCaffineCache(Long userId) {
        if (userId==1L){
            return "单纯地让 userId==1 的缓存失效了...";
        }else return "无事发生";

    }

    /*
    * 多个缓存注解的组合使用
    * */
    @Caching(put = @CachePut(cacheNames = "user_", key = "1"),
            evict = @CacheEvict(cacheNames = "user_", key = "2"))
    @Override
    public CacheDTO cachingTest() {
        return this.queryUserUseCaffineCache(1L);
    }

    @Override
    public MyResult<MyUser> queryUserWydUseGuavaCache() {
        return null;
    }
}
