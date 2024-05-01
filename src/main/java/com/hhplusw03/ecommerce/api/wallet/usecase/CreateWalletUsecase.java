package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateWalletUsecase {
    private final WalletService walletSvc;

    @Autowired
    public CreateWalletUsecase(WalletService svc){
        this.walletSvc = svc;
    }

    // Wallet 을 생성하고, 생성된 Wallet 정보를 반환함.
    public ResponseDto execute(String userId){
//        if (walletSvc.checkUserOwnsWallet(ownerId)){
//            // 요청 받은 userId 를 갖는 Wallet 이 이미 존재하므로 400, AlreadyCreatedWalletResponseDto 반환
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Already Created Wallet."));
//        }
//        else {
//            // 요청 받은 userId 를 갖는 Wallet 이 없으므로 새로 생성
//            return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(ownerId, this.walletCreator.createWallet(ownerId).getBalance()));
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto((WalletDto)walletRes));
        return new WalletResDto((WalletDto)this.walletSvc.createWallet(userId));
    }
}
