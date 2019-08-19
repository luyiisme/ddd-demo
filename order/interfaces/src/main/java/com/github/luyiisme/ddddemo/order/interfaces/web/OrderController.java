package com.github.luyiisme.ddddemo.order.interfaces.web;

import com.github.luyiisme.ddddemo.order.domain.OrderRepository;
import com.github.luyiisme.ddddemo.order.domain.order.Order;
import com.github.luyiisme.ddddemo.order.infra.dao.LineItemMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.OrderMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.ProductMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.model.LineItemDO;
import com.github.luyiisme.ddddemo.order.infra.dao.model.OrderDO;
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
    private OrderRepository orderRepository;


    @GetMapping("/users/{userName}/orders")
    public String getOrders(@PathVariable("userName") String name) {
        UserDTO dto = userFacade.getUserByName(name);
        Order order= orderRepository.getById(1);

            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setUserName(dto.getName());
            List<LineItemDO> items = lineItemMapper.getList(1);
            orderDTO.setItems(items.stream().map(transfer2LineItemDTO()).collect(Collectors.toList()));
            return orderDTO.toString();

    }

    private Function<LineItemDO, OrderDTO.LineItemDTO> transfer2LineItemDTO() {
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
