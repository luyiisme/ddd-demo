package com.github.luyiisme.ddddemo.order.infra;

import com.github.luyiisme.ddddemo.order.domain.OrderRepository;
import com.github.luyiisme.ddddemo.order.domain.order.LineItem;
import com.github.luyiisme.ddddemo.order.domain.order.Order;
import com.github.luyiisme.ddddemo.order.domain.order.Product;
import com.github.luyiisme.ddddemo.order.infra.dao.LineItemMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.OrderMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.ProductMapper;
import com.github.luyiisme.ddddemo.order.infra.dao.model.OrderDO;
import com.github.luyiisme.ddddemo.order.infra.dao.model.ProductDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 20:40
 **/
@Component
public class OrderRepositoryImpl implements OrderRepository {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private LineItemMapper lineItemMapper;
    @Autowired
    private ProductMapper productMapper;

    @Transactional
    @Override
    public void save(Order order) {
        //TODO
    }

    @Override
    public Order getById(long id) {
        OrderDO orderDO = orderMapper.getById(id);
        if (orderDO == null) {
            return null;
        }
        List<LineItem> lineItems = lineItemMapper.getList((int) id).stream().map(lineItemDO -> {
            LineItem lineItem = new LineItem();
            lineItem.setId(lineItemDO.getId());
            lineItem.setName(lineItemDO.getName());
            lineItem.setOrderId(lineItemDO.getOrderId());
            lineItem.setPrice(lineItemDO.getPrice());
            lineItem.setQuantity(lineItemDO.getQuantity());
            ProductDO productDO = productMapper.getById(lineItemDO.getProductId());
            lineItem.setProduct(transfer2Product(productDO));
            return lineItem;
        }).collect(Collectors.toList());

        final BeanCopier copier = BeanCopier.create(OrderDO.class, Order.class, false);
        Order order = new Order();
        copier.copy(orderDO,order,null);
        order.setLineItems(lineItems);
        return order;
    }

    private Product transfer2Product(ProductDO productDO) {
        Product prod = new Product();
        prod.setId(productDO.getId());
        prod.setName(productDO.getName());
        return prod;
    }
}
