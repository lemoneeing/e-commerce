package com.hhplusw03.ecommerce.api.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResDto implements ResponseDto {
    public String message;
}
