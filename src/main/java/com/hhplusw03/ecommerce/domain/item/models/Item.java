package com.hhplusw03.ecommerce.domain.item.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@AllArgsConstructor
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
    String CategoryId;

    public ItemDto toDto(){
        return new ItemDto(this);
    }

    public Long setStock(Long stock){
        this.stock = stock;
        return this.stock;
    }
}
