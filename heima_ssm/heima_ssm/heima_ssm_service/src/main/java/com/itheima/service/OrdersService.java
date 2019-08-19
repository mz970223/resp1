package com.itheima.service;

import com.itheima.domain.Orders;

import java.util.List;

public interface OrdersService {

    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
