package com.hhplusw03.ecommerce.api.wallet;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.DisplayWalletUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final DisplayWalletUseCase displayWalletUc;

    @GetMapping("/balance")
    @ResponseBody
    public ResponseDto showBalance(@RequestParam("user") Long userId){
        // 사용자 Id 로 잔액 조회
        return displayWalletUc.execute(userId);
    }

    // 사용자 Id 로 Money 충전
}