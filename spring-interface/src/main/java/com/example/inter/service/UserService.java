package com.example.inter.service;

import com.example.inter.bean.UserAddress;

import java.util.List;

public interface UserService {
    public List<UserAddress> getUserAddressList(String userId);
}
