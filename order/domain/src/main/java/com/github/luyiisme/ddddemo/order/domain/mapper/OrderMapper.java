package com.github.luyiisme.ddddemo.order.domain.mapper;


import com.github.luyiisme.ddddemo.order.domain.order.Order;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 09:47
 **/
public interface OrderMapper {

    @Select("SELECT * FROM torder")
    List<Order> getList();

    @Select("SELECT * FROM torder where user_id=#{userId}")
    @Results(value = {
            @Result(property = "orderNo", column = "order_no"),
            @Result(property = "userId", column = "user_id")
            })
    List<Order> getOrdersByUserId(long userId);
}
