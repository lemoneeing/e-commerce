package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.request.ChargeReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.request.WalletReqDto;
import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.ChargeUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.NewWalletUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.BalanceUseCase;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {

    private final NewWalletUseCase createWalletUc;
    private final BalanceUseCase balanceUc;
    private final ChargeUseCase chargeUc;

    @PostMapping("/new")
    public ResponseEntity<ResponseDto> newWallet(@RequestBody WalletReqDto reqDto){
        // 지갑 생성

        return createWalletUc.execute(reqDto.getUserId());
    }

    @GetMapping("/balance")
    public ResponseEntity<ResponseDto>  showBalance(@RequestBody WalletReqDto reqDto){
        // 사용자 Id 로 잔액 조회
        return balanceUc.execute(reqDto.getUserId());
    }

    // 사용자 Id 를 갖는 Wallet 에 Money 충전
    @PatchMapping("/charge")
    public ResponseEntity<ResponseDto> chargeWallet(@RequestBody ChargeReqDto reqDto) {
        //지갑 충전
        return chargeUc.execute(reqDto.getUserId(), reqDto.getAmount());
    }
}