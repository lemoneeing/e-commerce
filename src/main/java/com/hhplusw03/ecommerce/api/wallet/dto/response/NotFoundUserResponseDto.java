package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundUserResponseDto implements ResponseDto {
    private final String message = "Not Found User.";
}
