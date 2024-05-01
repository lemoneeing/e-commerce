package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.request.WalletReqDto;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.CreateWalletUsecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final CreateWalletUsecase createWalletUc;
    @Autowired
    public WalletController(CreateWalletUsecase createUc){
        this.createWalletUc = createUc;
    }

    @PostMapping("/new")
    public ResponseEntity<ResponseDto> newWallet(@RequestBody WalletReqDto reqDto){
        return ResponseEntity.status(HttpStatus.OK).body(createWalletUc.execute(reqDto.getUserId()));
    }

//    @GetMapping("/balance")
//    public ResponseEntity<ResponseDto>  showBalance(@RequestBody WalletReqDto reqDto){
//        // 사용자 Id 로 잔액 조회
//        return balanceUc.execute(reqDto.getUserId());
//    }
//
//    // 사용자 Id 를 갖는 Wallet 에 Money 충전
//    @PatchMapping("/charge")
//    public ResponseEntity<ResponseDto> chargeWallet(@RequestBody ChargeReqDto reqDto) {
//        //지갑 충전
//        return chargeUc.execute(reqDto.getUserId(), reqDto.getAmount());
//    }
}