package com.hhplusw03.ecommerce.api.money;

import com.hhplusw03.ecommerce.api.money.dto.fakeUserWallet;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/money")
@RestController
public class controller {
    @Autowired
    fakeUserWallet fakeWallet;

    // 사용자 Id 로 잔액 조회
    @GetMapping("?userId={userId}")
    public String showMoney(@RequestParam String userId){
        return "userId: " + userId +"\nmoney: " + fakeWallet.getMoney(userId);
    }

    // 사용자 Id 로 Money 충전
    @GetMapping("/charge/?userId={userId}&moneyAmount={amount}")
    public String chargeMoney(@RequestParam String userId, @RequestParam Long amount){
        fakeWallet.addMoney(userId, amount);
        return "userId: " + userId +"\nmoney: " + fakeWallet.getMoney(userId);
    }

}