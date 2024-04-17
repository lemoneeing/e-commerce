package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ErrorResDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceUseCase {
    private final WalletReader walletReader;

    // Wallet 의 상태를 보여줌.
    public ResponseEntity<ResponseDto> execute(String userId){

        Long ownerId = Long.parseLong(userId);
        Long balance = this.walletReader.readBalanceByUserId(ownerId);

        if (balance >= 0L) {
            return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(ownerId, balance));
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Not Found User."));
        }
    }
}
