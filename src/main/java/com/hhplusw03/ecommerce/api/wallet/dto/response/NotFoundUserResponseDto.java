package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotFoundUserResponseDto extends ResponseDto {

    public NotFoundUserResponseDto(){
        this.message  = "Not Found User Response Dto";
    }
}
