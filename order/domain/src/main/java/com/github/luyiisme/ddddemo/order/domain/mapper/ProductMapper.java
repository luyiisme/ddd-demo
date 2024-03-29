package com.github.luyiisme.ddddemo.order.domain.mapper;

import com.github.luyiisme.ddddemo.order.domain.order.Product;
import org.apache.ibatis.annotations.Select;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 19:33
 **/
public interface ProductMapper {

    @Select("SELECT * FROM product where id=#{id}")
    Product getById(long id);

}
