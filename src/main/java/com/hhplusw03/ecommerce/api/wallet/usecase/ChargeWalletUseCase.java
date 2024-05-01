package com.hhplusw03.ecommerce.api.wallet.usecase;

import org.springframework.stereotype.Service;

@Service
public class ChargeWalletUseCase {
//    private final WalletReader walletReader;
//    private final WalletModifier walletModifier;

//    public ResponseEntity<ResponseDto> execute(String userId, String amount){
//
//        Long ownerId = Long.parseLong(userId);
//        Long amount2Charge = Long.parseLong(amount);
//
//        // 최소 금액 (10000 원) 미만은 충전 불가
//        if (amount2Charge < 10000) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResDto("Too little amount to charge."));
//        }
//
//        // 최소 금액 (10000 원) 이상
//        Long initBal = walletReader.readBalanceByUserId(ownerId);
//        WalletDto walletDto = walletModifier.modifyBalance(ownerId, initBal + amount2Charge);
//        return ResponseEntity.status(HttpStatus.OK).body(new WalletResDto(walletDto.getUserId(), walletDto.getBalance()));
//    }
}
