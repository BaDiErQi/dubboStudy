package com.example.bootconsumer.controller;

import com.example.bootconsumer.service.OrderService;
import com.example.inter.bean.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<UserAddress> getOrder(@RequestParam("id") String orderId) {
        return orderService.initOrder(orderId);
    }
}
