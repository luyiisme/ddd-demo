package com.github.luyiisme.ddddemo.order.interfaces.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 19:41
 **/
@Data
public class OrderDTO {
    private String userName;
    private List<LineItemDTO> items=new ArrayList<>();



    @Data
    public static class LineItemDTO{
        private String name;
        private int quantity;
        private String price;
        private String productName;
    }
}
