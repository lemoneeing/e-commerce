package com.hhplusw03.ecommerce.api.item.usecase;

import com.hhplusw03.ecommerce.domain.item.application.Impl.ItemServiceImpl;
import com.hhplusw03.ecommerce.domain.item.application.ItemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterItemUseCase {
    private final ItemServiceImpl itemSvc;

    @Autowired
    public RegisterItemUseCase(ItemServiceImpl svc){
        this.itemSvc = svc;
    }
    public ItemResult execute(String name, Long price, String seller, Long stock){
        return itemSvc.registerItem(name, price, seller ,stock);
    }
}
