package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ErrorResDto;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletCreator;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewWalletUseCase {
    private final WalletCreator walletCreator;
    private final WalletReader walletReader;

    // Wallet 을 생성하고, 생성된 Wallet 정보를 반환함.
    public ResponseEntity<ResponseDto> execute(String userId){

        Long ownerId = Long.parseLong(userId);

        if (walletReader.checkWalletExisted(ownerId)){
            // 요청 받은 userId 를 갖는 Wallet 이 이미 존재하므로 400, AlreadyCreatedWalletResponseDto 반환
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Already Created Wallet."));
        }
        else {
            // 요청 받은 userId 를 갖는 Wallet 이 없으므로 새로 생성
            return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(ownerId, this.walletCreator.createWallet(ownerId).getBalance()));
        }
    }
}
