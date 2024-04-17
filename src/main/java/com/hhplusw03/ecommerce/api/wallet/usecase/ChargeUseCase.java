package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ErrorResDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletModifier;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeUseCase {
    private final WalletReader walletReader;
    private final WalletModifier walletModifier;

    public ResponseEntity<ResponseDto> execute(String userId, String amount){

        Long ownerId = Long.parseLong(userId);
        Long amount2Charge = Long.parseLong(amount);

        // 10000 원 미만 금액은 충전 불가
        if (amount2Charge < 10000) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Too little amount to charge."));

        Long initBal = walletReader.readBalanceByUserId(ownerId);


        // 직접 Wallet Class 를 사용한 것은 아니지만, readWalletByUserId 의 반환 Type 이 Wallet 일 때, 정말 의존성이 없다고 할 수 있는 것인가?
        walletModifier.modifyBalanceByUserIdAndAmount(walletReader.readWalletByUserId(ownerId), initBal + amount2Charge);

        return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(ownerId, walletReader.readBalanceByUserId(ownerId)));
    }
}
