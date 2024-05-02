package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletResult;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ChargeWalletUseCase {
    private final WalletService walletSvc;
    @Autowired
    public ChargeWalletUseCase(WalletService svc){
        this.walletSvc = svc;
    }

    public ResponseDto execute(String userId, String amount){

//        // 최소 금액 (10000 원) 미만은 충전 불가
//        if (amount2Charge < 10000) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Too little amount to charge."));
//        }
//
//        // 최소 금액 (10000 원) 이상
//        Long initBal = walletReader.readBalanceByUserId(ownerId);
//        WalletDto walletDto = walletModifier.modifyBalance(ownerId, initBal + amount2Charge);
//        return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(walletDto.getUserId(), walletDto.getBalance()));

//        WalletResult wallet = this.walletSvc.readWallet(userId);
        return new WalletResDto((WalletDto) this.walletSvc.chargeWallet(userId, amount));
    }
}
