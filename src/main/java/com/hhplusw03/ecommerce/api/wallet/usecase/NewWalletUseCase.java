package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.AlreadyCreatedWalletResDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.NotFoundUserResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletCreator;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewWalletUseCase {
    private final WalletCreator walletCreator;
    private final WalletReader walletReader;

    // Wallet 의 상태를 보여줌.
    public ResponseEntity<ResponseDto> execute(String userId){

        Long ownerId = Long.parseLong(userId);
        
        // 이미 동일한 userId로 생성된 Wallet 이 존재함. -> AlreadyCreatedWalletResponseDto 반환
        if (walletReader.checkWalletExistByUserId(ownerId)){
            return ResponseEntity.status(400).body( new AlreadyCreatedWalletResDto());
        }
        else {

            // Wallet 생성 시도
            this.walletCreator.createWallet(ownerId);

            // Wallet 이 잘 생성되었는지 확인
            if (walletReader.checkWalletExistByUserId(ownerId)) {
                return ResponseEntity.status(200).body(new WalletResponseDto(ownerId, walletReader.readBalanceByUserId(ownerId)));
            } else {
                return ResponseEntity.status(404).body(new NotFoundUserResponseDto());
            }
        }
    }
}
