package com.hhplusw03.ecommerce.domain.item.repository;

import com.hhplusw03.ecommerce.domain.item.models.ItemEntity;

public interface ItemRepository {
    public ItemEntity register(String name, Long price, String seller, Long stock);
    public ItemEntity readItem(Long itemId);
}
