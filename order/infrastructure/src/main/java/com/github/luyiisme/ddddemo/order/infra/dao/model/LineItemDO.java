package com.github.luyiisme.ddddemo.order.infra.dao.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 15:59
 **/
@Data
public class LineItemDO {
    private long id;
    //m:1属于某个order
    private long orderId;
    private String name;
    private int quantity;
    private BigDecimal price;
    //1:1 ,对应某个产品
    private long productId;
}
