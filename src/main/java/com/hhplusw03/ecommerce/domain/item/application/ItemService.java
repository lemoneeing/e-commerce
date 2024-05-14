package com.hhplusw03.ecommerce.domain.item.application;

public interface ItemService{
    public ItemResult registerItem(String name, Long price, String seller, Long stock);
    public ItemResult readItem(Long itemId);
    public Boolean checkStock(Long itemId);
}
