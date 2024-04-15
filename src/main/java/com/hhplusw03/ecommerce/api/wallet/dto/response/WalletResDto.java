package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResDto implements ResponseDto{
    /*
    * Wallet 반환 응답
    */

    private String userId;
    private String balance;

    public WalletResDto(Long userId, Long balance){
        //DTO 의 멤버 변수 형변환은 어디서 이루어지는 것이 좋을까?

        this.userId = String.valueOf(userId);
        this.balance = String.valueOf(balance);
    }
}
