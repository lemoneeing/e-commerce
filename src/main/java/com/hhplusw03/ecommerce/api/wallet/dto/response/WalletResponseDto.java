package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponseDto implements ResponseDto{
    private Long userId;
    private Long balance;

    public WalletResponseDto(Long userId, Long balance){
        this.userId = userId;
        this.balance = balance;
    }
}
