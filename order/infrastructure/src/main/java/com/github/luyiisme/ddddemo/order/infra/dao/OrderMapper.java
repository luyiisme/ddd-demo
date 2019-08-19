package com.github.luyiisme.ddddemo.order.infra.dao;


import com.github.luyiisme.ddddemo.order.infra.dao.model.OrderDO;
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
    List<OrderDO> getList();

    @Select("SELECT * FROM torder where user_id=#{userId}")
    @Results(value = {
            @Result(property = "orderNo", column = "order_no"),
            @Result(property = "userId", column = "user_id")
            })
    List<OrderDO> getOrdersByUserId(long userId);

    @Select("SELECT * FROM torder where id=#{id}")
    @Results(value = {
            @Result(property = "orderNo", column = "order_no"),
            @Result(property = "userId", column = "user_id")
    })
    OrderDO getById(long id);
}
