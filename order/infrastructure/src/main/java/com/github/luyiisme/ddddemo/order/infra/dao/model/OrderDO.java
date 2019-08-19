package com.github.luyiisme.ddddemo.order.infra.dao.model;

import lombok.Data;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-18 20:47
 **/
@Data
public class OrderDO {
    private long id;
    private String orderNo;
    //who's
    private long userId;
    private String status;





}
