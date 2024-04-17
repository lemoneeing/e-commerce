package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResDto implements ResponseDto{
    public String message;
}
