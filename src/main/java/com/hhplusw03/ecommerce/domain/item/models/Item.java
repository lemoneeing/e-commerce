package com.hhplusw03.ecommerce.domain.item.models;

import com.hhplusw03.ecommerce.domain.item.components.ItemDto;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @Column(unique = true)
    String name;
    @Column
    Long price;
    @Column
    String seller;
    @Column
    Long stock;
    @Column
    String status;
//    @Column
//    String category;
    
    // 상위 참조라 하면 안될 것 같음.
//    public ItemDto toDto(){
//        return new ItemDto(this);
//    }

    public Item(String name, Long price, String seller, Long stock){
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.stock = stock;
//        this.category = category;
    }

    public Long setStock(Long stock){
        this.stock = stock;
        return this.stock;
    }
}
