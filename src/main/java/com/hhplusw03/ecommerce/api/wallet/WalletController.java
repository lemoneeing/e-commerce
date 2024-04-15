package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.request.NewWalletReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.NewWalletUseCase;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {

    private final NewWalletUseCase createWalletUc;
//    private final DisplayWalletUseCase displayWalletUc;
//    private final ChargeUseCase chargeUc;

    @PostMapping("/new")
    public ResponseDto newWallet(@RequestBody NewWalletReqDto reqDto){
        // 지갑 생성

        // Request body 에 "ueseId" 라는 key 가 없을 때 response 를 400 으로 주려면 어떻게...?
        return createWalletUc.execute(reqDto.getUserId());
    }

//    @GetMapping("/balance")
//    public ResponseDto showBalance(@RequestParam Long userId){
//        // 사용자 Id 로 잔액 조회
//        return displayWalletUc.execute(userId);
//    }

    // 사용자 Id 로 Money 충전
//    @PatchMapping("/charge")
//    public ResponseDto chargeWallet(@RequestBody Map<String, String> requestBodyMap){
//        // 지갑 충전
//        // Request body 에 "ueseId" 라는 key 가 없을 때 response 를 400 으로 주려면 어떻게...?
//        Long userId = Long.parseLong(requestBodyMap.get("userId"));
//        Long amount = Long.parseLong(requestBodyMap.get("amount"));
//        return chargeUc.execute(userId, amount);
//    }
//    @PatchMapping("/charge")
//    public ResponseDto chargeWallet(@RequestBody ChargeData chargeData) {
//        //지갑 충전
//        Long userId = Long.parseLong(chargeData.getUserId());
//        Long amount = Long.parseLong(chargeData.getAmount());
//        return chargeUc.execute(userId, amount);
//    }
}