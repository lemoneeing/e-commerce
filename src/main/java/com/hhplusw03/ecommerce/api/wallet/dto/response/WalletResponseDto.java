package com.hhplusw03.ecommerce.api.wallet.dto.response;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponseDto extends ResponseDto{
    Long userId;
    Long balance;

    public WalletResponseDto(Long userId, Long balance){
        this.userId = userId;
        this.balance = balance;
    }
}
