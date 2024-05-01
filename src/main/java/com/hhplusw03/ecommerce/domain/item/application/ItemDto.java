package com.hhplusw03.ecommerce.domain.item.application;

import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;
import lombok.Getter;

@Getter
public class ItemDto extends ItemResult{
    //Business 와 Persistence 사이의 Data 를 변환하는 목적
    String name;
    Long price;
    String seller;
    Long stock;
//    String status;

    public ItemDto(ItemEntity itemEntity){
        this.name = itemEntity.getName();
        this.price = itemEntity.getPrice();
        this.seller = itemEntity.getSeller();
        this.stock = itemEntity.getStock();
//        this.status = itemEntity.getStatus();
    }
    public ItemDto(String name, Long price, String seller, Long stock){
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.stock = stock;
    }
}
