package com.hhplusw03.ecommerce.domain.item.application.Impl;

import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import com.hhplusw03.ecommerce.domain.item.repository.ItemRepository;
import com.hhplusw03.ecommerce.domain.item.application.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepo;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepo){
        this.itemRepo = itemRepo;
    }

    @Override
    public ItemResult registerItem(String name, Long price, String seller, Long stock){
        ItemDto itemDto = new ItemDto(name, price, seller, stock);
        itemRepo.register(name, price, seller, stock);
        return itemDto;
    }

    @Override
    public ItemResult readItem(Long itemId) {
        return new ItemDto(itemRepo.readItem(itemId));
    }
}
