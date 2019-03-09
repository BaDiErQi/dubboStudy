package com.example.bootconsumer.service;

import com.example.inter.bean.UserAddress;

import java.util.List;

public interface OrderService {
    List<UserAddress> initOrder(String orderId);
}
