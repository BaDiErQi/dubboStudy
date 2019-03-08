package com.example.provider.service.impl;

import com.example.inter.bean.UserAddress;
import com.example.inter.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:provider.xml");
        context.start();
        System.in.read();
    }

}
