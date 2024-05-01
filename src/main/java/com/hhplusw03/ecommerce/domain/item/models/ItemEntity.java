package com.hhplusw03.ecommerce.domain.item.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="Item")
public class ItemEntity{
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
//    @Column
//    String status;
//    @Column
//    String category;
    
    // 상위 참조라 하면 안될 것 같음.
//    public ItemDto toDto(){
//        return new ItemDto(this);
//    }

    public ItemEntity(String name, Long price, String seller, Long stock){
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.stock = stock;
//        this.status = status;
//        this.category = category;
    }

    public Long setStock(Long stock){
        this.stock = stock;
        return this.stock;
    }
}
