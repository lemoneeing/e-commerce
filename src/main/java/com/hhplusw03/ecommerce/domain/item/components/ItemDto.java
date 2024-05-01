package com.hhplusw03.ecommerce.domain.item.components;

import com.hhplusw03.ecommerce.domain.item.models.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ItemDto {
    //Business 와 Persistence 사이의 Data 를 변환하는 목적
    String name;
    Long price;
    String seller;
    Long stock;
    String status;
    public ItemDto(Item item){
        this.name = item.getName();
        this.price = item.getPrice();
        this.seller = item.getSeller();
        this.stock = item.getStock();
        this.status = item.getStatus();
    }
    public ItemDto(String name, Long price, String seller, Long stock){
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.stock = stock;
    }
}
