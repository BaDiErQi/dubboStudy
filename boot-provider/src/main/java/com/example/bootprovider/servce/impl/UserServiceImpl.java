package com.example.bootprovider.servce.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.inter.bean.UserAddress;
import com.example.inter.service.UserService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<UserAddress> getUserAddressList(String userId) {
        System.out.println("消费者被调用，userid为" + userId);
        UserAddress u1 = new UserAddress("黄土高坡");
        UserAddress u2 = new UserAddress("海南岛");
        List<UserAddress> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        return list;
    }

}
