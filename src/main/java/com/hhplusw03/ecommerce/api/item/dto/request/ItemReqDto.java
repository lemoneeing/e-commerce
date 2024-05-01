package com.hhplusw03.ecommerce.api.item.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItemReqDto{
    private String name;
    private Long price;
    private String seller;
    private Long stock;
//    private Long categoryId;
}
