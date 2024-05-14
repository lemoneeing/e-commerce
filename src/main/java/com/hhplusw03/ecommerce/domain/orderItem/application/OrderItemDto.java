package com.hhplusw03.ecommerce.domain.orderItem.application;

import com.hhplusw03.ecommerce.domain.orderItem.models.OrderItemEntity;

public class OrderItemDto {
    Long orderId;
    Long itemId;
    Long orderCount;
    Long price;

    public OrderItemDto(Long itemId, Long count, Long price){
        this.orderId = null;
        this.itemId = itemId;
        this.orderCount = count;
        this.price = price;
    }

    public OrderItemDto(OrderItemEntity oi){
        this.orderId = oi.getOrderId();
        this.itemId = oi.getItemId();
        this.orderCount = oi.getOrderCount();
        this.price = oi.getPrice();
    }

    public Long getOrderId(){
        return this.orderId;
    }

    public Long getItemId(){
        return this.itemId;
    }

    public Long getOrderCount(){
        return this.orderCount;
    }

    public Long getPrice(){
        return this.price;
    }
}
