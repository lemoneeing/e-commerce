package com.hhplusw03.ecommerce.api.money;

import com.hhplusw03.ecommerce.api.money.dto.fakeUserWallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/money")
@RestController
public class controller {
    @Autowired
    fakeUserWallet fakeWallet;

    // 사용자 Id 로 잔액 조회
    @GetMapping
    @ResponseBody
    public String showMoney(@RequestParam String userId){
//    @GetMapping("/user/{userId}")
//    public String showMoney(@PathVariable String userId){
        return "userId: " + userId +"\nmoney: " + fakeWallet.getMoney(userId);
    }

    // 사용자 Id 로 Money 충전
//    @GetMapping("/charge/?userId={userId}&moneyAmount={amount}")
    @GetMapping("/charge")
    public String chargeMoney(@RequestParam String userId, @RequestParam Long amount){
        fakeWallet.addMoney(userId, amount);
        return "userId: " + userId +"\nmoney: " + fakeWallet.getMoney(userId);
    }
}