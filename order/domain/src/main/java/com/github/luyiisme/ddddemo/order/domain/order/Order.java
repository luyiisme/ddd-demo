package com.github.luyiisme.ddddemo.order.domain.order;

import com.github.luyiisme.ddddemo.order.infra.dao.model.OrderDO;
import com.github.luyiisme.ddddemo.order.domain.valueobject.User;
import lombok.Data;

import java.util.List;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-19 20:25
 **/
@Data
public class Order {
    private long id;
    private String orderNo;
    private User user;
    private Status status;
    private List<LineItem> lineItems;

    public static enum Status {

        /**
         * Placed, but not payed yet. Still changeable.
         */
        PAYMENT_EXPECTED,

        /**
         * {@link OrderDO} was payed. No changes allowed to it anymore.
         */
        PAID,

        /**
         * The {@link OrderDO} is currently processed.
         */
        PREPARING,

        /**
         * The {@link OrderDO} is ready to be picked up by the customer.
         */
        READY,

        /**
         * The {@link OrderDO} was completed.
         */
        TAKEN;
    }

}
