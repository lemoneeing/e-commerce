package com.hhplusw03.ecommerce.api.wallet.dto.response;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResDto implements ResponseDto {
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

    public WalletResDto(WalletDto dto){
        this.userId = String.valueOf(dto.getUserId());
        this.balance = String.valueOf(dto.getBalance());
    }
}
