package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.NotFoundUserResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletCreator;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateWalletUseCase {
    private final WalletCreator walletCreator;
    private final WalletReader walletReader;

    // Wallet 의 상태를 보여줌.
    public ResponseDto execute(Long userId){
        // Wallet 생성 시도
        this.walletCreator.createWalletByUserId(userId);

        // Wallet 이 잘 생성되었는지 확인
        if (walletReader.checkWalletExistByUserId(userId)) {
            return new WalletResponseDto(userId, walletReader.readBalanceByUserId(userId));
        }
        else {
            return new NotFoundUserResponseDto();
        }
    }
}
