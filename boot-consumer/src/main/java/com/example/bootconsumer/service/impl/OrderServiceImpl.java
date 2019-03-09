package com.example.bootconsumer.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.bootconsumer.service.OrderService;
import com.example.inter.bean.UserAddress;
import com.example.inter.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Reference
    private UserService userService;

    @Override
    public List<UserAddress> initOrder(String orderId) {
        return userService.getUserAddressList(orderId);
    }

}
