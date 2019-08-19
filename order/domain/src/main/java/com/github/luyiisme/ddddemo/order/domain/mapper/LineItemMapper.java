package com.github.luyiisme.ddddemo.order.domain.mapper;

import com.github.luyiisme.ddddemo.order.domain.order.LineItem;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 19:33
 **/
public interface LineItemMapper {

    @Select("SELECT * FROM line_item where order_id=#{orderId}")
    @Results(value = {
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "productId", column = "product_id")
    })
    List<LineItem> getList(int orderId);
}
