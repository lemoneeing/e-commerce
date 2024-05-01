package com.hhplusw03.ecommerce.api.item.dto.response;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.domain.item.application.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemResDto implements ResponseDto {

    String name;
    Long price;
    String seller;
    Long stock;
    String status;

    public ItemResDto(ItemDto dto){
        // ItemResDto 는 Presentation layer 니까 ItemDto 를 참조해도 괜찮,,,?
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.seller = dto.getSeller();
        this.stock = dto.getStock();
//        this.status = dto.getStatus();
    }
}
