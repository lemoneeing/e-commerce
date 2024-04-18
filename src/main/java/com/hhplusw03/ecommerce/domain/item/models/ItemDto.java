package com.hhplusw03.ecommerce.domain.item.models;

import com.hhplusw03.ecommerce.domain.item.models.Item;
import lombok.Getter;

@Getter
public class ItemDto {
    String name;
    Long price;
    String seller;
    Long stock;

    public ItemDto(Item item){
        this.name = item.getName();
        this.price = item.getPrice();
        this.seller = item.getSeller();
        this.stock = item.getStock();
    }
}
