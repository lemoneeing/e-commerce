package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.ChargeUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.CreateWalletUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.DisplayWalletUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final DisplayWalletUseCase displayWalletUc;
    private final CreateWalletUseCase createWalletUc;
    private final ChargeUseCase chargeUc;

    @PostMapping("/new")
    public ResponseDto createWallet(@RequestBody Map<String, String> requestBodyMap){

        // Request body 에 "ueseId" 라는 key 가 없을 때 response 를 400 으로 주려면 어떻게...?
        Long userId = Long.parseLong(requestBodyMap.get("userId"));
        return createWalletUc.execute(userId);
    }

    @GetMapping("/balance")
    public ResponseDto showBalance(@RequestParam Long userId){
        // 사용자 Id 로 잔액 조회
        return displayWalletUc.execute(userId);
    }

    // 사용자 Id 로 Money 충전
    @PatchMapping("/charge")
    public ResponseDto chargeWallet(@RequestBody Map<String, String> requestBodyMap){

        // Request body 에 "ueseId" 라는 key 가 없을 때 response 를 400 으로 주려면 어떻게...?
        Long userId = Long.parseLong(requestBodyMap.get("userId"));
        Long amount = Long.parseLong(requestBodyMap.get("amount"));
        return chargeUc.execute(userId, amount);
    }
}