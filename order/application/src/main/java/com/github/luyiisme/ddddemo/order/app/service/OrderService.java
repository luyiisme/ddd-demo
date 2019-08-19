package com.github.luyiisme.ddddemo.order.app.service;

import com.github.luyiisme.ddddemo.order.domain.order.Order;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 18:11
 **/
public interface OrderService {
    Order getOrdersByUser(long id);
}
