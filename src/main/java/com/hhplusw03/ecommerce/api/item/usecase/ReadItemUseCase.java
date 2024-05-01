package com.hhplusw03.ecommerce.api.item.usecase;

import com.hhplusw03.ecommerce.api.item.dto.response.ItemResDto;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.domain.item.application.Impl.ItemServiceImpl;
import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadItemUseCase {

    private final ItemServiceImpl itemSvc;

    @Autowired
    public ReadItemUseCase(ItemServiceImpl svc){
        this.itemSvc = svc;
    }

    public ResponseDto execute(String itemId){
        return new ItemResDto((ItemDto) this.itemSvc.readItem(Long.parseLong(itemId)));
    }
}
