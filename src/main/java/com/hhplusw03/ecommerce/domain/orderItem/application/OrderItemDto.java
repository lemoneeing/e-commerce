package com.hhplusw03.ecommerce.domain.orderItem.application;

import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItem;

public class OrderItemDto {
    Long orderId;
    Long itemId;
    Long orderCount;
    Long orderPrice;

    public OrderItemDto(OrderItem oi){
        this.orderId = oi.getOrderId();
        this.itemId = oi.getItemId();
        this.orderCount = oi.getOrderCount();
        this.orderPrice = oi.getPrice();
    }

    public Long getPrice(){
        return this.orderPrice;
    }
}
