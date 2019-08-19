package com.github.luyiisme.ddddemo.order.domain;

import com.github.luyiisme.ddddemo.order.domain.order.Order;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 20:25
 **/
public interface OrderRepository {

    void save(Order order);

    Order getById(long id);
}
