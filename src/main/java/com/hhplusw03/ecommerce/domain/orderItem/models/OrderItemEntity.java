package com.hhplusw03.ecommerce.domain.orderItem.models;

import jakarta.persistence.*;


@Entity
@Table(name="OrderItem")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderId;
    Long itemId;
    Long orderCount;
    Long price;

    public OrderItemEntity(Long orderId, Long itemId, Long orderCount, Long price){
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderCount = orderCount;
        this.price = price;
    }
    public Long getOrderId(){
        return this.orderId;
    }

    public void setOrderId(Long orderId) { this.orderId = orderId; }

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
