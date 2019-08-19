package com.github.luyiisme.ddddemo.order.app.service;

import com.github.luyiisme.ddddemo.order.infra.dao.model.OrderDO;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 18:11
 **/
public interface OrderService {
    OrderDO getOrdersByUser(long id);
}
