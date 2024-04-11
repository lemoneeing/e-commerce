package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.NotFoundUserResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DisplayWalletUseCase {
    private final WalletReader walletReader;

    // Wallet 의 상태를 보여줌.
    public ResponseDto execute(Long userId){
        Long balance = this.walletReader.readBalanceByUserId(userId);

        if (balance >= 0L) {
            return new WalletResponseDto(userId, balance);
        }
        else {
            return new NotFoundUserResponseDto();
        }
    }
}
