package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletCreator;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletModifier;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChargeUseCase {
    private final WalletReader walletReader;
    private final WalletModifier walletModifier;

    public ResponseDto execute(Long userId, Long amount){
        Long initBal = walletReader.readBalanceByUserId(userId);

        // 직접 Wallet Class 를 사용한 것은 아니지만, readWalletByUserId 의 반환 Type 이 Wallet 일 때, 정말 의존성이 없다고 할 수 있는 것인가?
        walletModifier.modifyBalanceByUserIdAndAmount(walletReader.readWalletByUserId(userId), initBal + amount);

        return new WalletResponseDto(userId, walletReader.readBalanceByUserId(userId));
    }
}
