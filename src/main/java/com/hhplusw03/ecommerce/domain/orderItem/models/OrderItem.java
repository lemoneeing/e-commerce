package com.hhplusw03.ecommerce.domain.orderItem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;


@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long orderId;
    Long itemId;
    Long orderCount;
    Long price;

    public OrderItem(Long orderId, Long itemId, Long orderCount, Long price){
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderCount = orderCount;
        this.price = price;
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
