package com.hhplusw03.ecommerce.domain.item.components;


import com.hhplusw03.ecommerce.domain.item.models.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    public Item register(String name, Long price, String seller, Long stock){
        return new Item(name, price, seller, stock);
    }
}
