package com.baihoomuch.cloud.service.impl;

import com.baihoomuch.cloud.entity.User;
import com.baihoomuch.cloud.repository.UserRepository;
import com.baihoomuch.cloud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: microservice-simple-provider-user
 * auther Administrator on 2018/7/5
 */
@Service //注解启动加载
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Override
    public User findByPasswordAndUsername(Long password , String username){
       return  repository.findByPasswordAndUsername(password,username);
    }
    @Override
    public User findByObjectid(Long id) {
        return repository.findByObjectid(id);
    }
}
