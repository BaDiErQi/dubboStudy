package com.example.consumer.service.impl;

import com.example.consumer.service.OrderService;
import com.example.inter.bean.UserAddress;
import com.example.inter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserService userService;

    @Override
    public List<UserAddress> initOrder() {
        return userService.getUserAddressList("1");
    }
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
        context.start();
        OrderService demoService = (OrderService) context.getBean(OrderServiceImpl.class);
        List<UserAddress> hello = demoService.initOrder();
        for (int i = 0; i < hello.size(); i++) {
            UserAddress userAddress =  hello.get(i);
            System.out.println(userAddress.getUserAddress());
        }
        System.in.read();
    }
}
