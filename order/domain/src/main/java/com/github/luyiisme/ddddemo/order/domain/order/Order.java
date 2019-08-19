package com.github.luyiisme.ddddemo.order.domain.order;

import lombok.Data;

/**
 * @author: kevin.luy@antfin.com
 * @create: 2019-08-18 20:47
 **/
@Data
public class Order {
    private long id;
    private String orderNo;
    //who's
    private long userId;
    private Status status;




    public static enum Status {

        /**
         * Placed, but not payed yet. Still changeable.
         */
        PAYMENT_EXPECTED,

        /**
         * {@link Order} was payed. No changes allowed to it anymore.
         */
        PAID,

        /**
         * The {@link Order} is currently processed.
         */
        PREPARING,

        /**
         * The {@link Order} is ready to be picked up by the customer.
         */
        READY,

        /**
         * The {@link Order} was completed.
         */
        TAKEN;
    }
}
