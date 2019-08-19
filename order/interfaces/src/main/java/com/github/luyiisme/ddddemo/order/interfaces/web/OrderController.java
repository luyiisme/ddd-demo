package com.github.luyiisme.ddddemo.order.interfaces.web;

import com.github.luyiisme.ddddemo.order.domain.mapper.LineItemMapper;
import com.github.luyiisme.ddddemo.order.domain.mapper.OrderMapper;
import com.github.luyiisme.ddddemo.order.domain.mapper.ProductMapper;
import com.github.luyiisme.ddddemo.order.domain.order.LineItem;
import com.github.luyiisme.ddddemo.order.domain.order.Order;
import com.github.luyiisme.ddddemo.order.interfaces.dto.OrderDTO;
import com.github.luyiisme.ddddemo.user.interfaces.facade.UserFacade;
import com.github.luyiisme.ddddemo.user.interfaces.facade.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 16:24
 **/
@RestController
public class OrderController {
    @Autowired
    private UserFacade userFacade;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private ProductMapper productMapper;


    @GetMapping("/users/{userName}/orders")
    public String getOrders(@PathVariable("userName") String name) {
        UserDTO dto = userFacade.getUserByName(name);
        List<Order> orders = orderMapper.getOrdersByUserId(dto.getId());

        return orders.stream().map(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserName(dto.getName());
            List<LineItem> items = lineItemMapper.getList(1);
            orderDTO.setItems(items.stream().map(transfer2LineItemDTO()).collect(Collectors.toList()));

            return orderDTO;
        }).collect(Collectors.toList()).toString();

    }

    private Function<LineItem, OrderDTO.LineItemDTO> transfer2LineItemDTO() {
        return li -> {
            OrderDTO.LineItemDTO lineItemDTO = new OrderDTO.LineItemDTO();
            lineItemDTO.setName(li.getName());
            lineItemDTO.setPrice(li.getPrice().toString());
            lineItemDTO.setQuantity(li.getQuantity());
            lineItemDTO.setProductName(productMapper.getById(li.getProductId()).getName());
            return lineItemDTO;
        };
    }
}
