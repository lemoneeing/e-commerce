package com.hhplusw03.ecommerce.api.money.dto;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@NoArgsConstructor
public class fakeUserWallet {
    public Map<String, Long> wallet = new HashMap<>();

    public Long getMoney(String userId){
        if (!wallet.containsKey(userId)){
            wallet.put(userId, 0L);
        }
        return wallet.getOrDefault(userId, 0L);
    }
}
