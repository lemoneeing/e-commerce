package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponseDto implements ResponseDto{
    /*
    * Wallet 생성 응답에 Mapping
    */

    private String userId;
    private String balance;

    public WalletResponseDto(Long userId, Long balance){
        //DTO 의 멤버 변수 형변환은 어디서 이루어지는 것이 좋을까?

        this.userId = String.valueOf(userId);
        this.balance = String.valueOf(balance);
    }
}
