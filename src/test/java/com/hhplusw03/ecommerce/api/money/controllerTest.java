package com.hhplusw03.ecommerce.api.money;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class controllerTest {
    @Autowired
    controller ctr;

    @Test
    public void 머니_조회(){
        String uId = "1";

        Assertions.assertThat(ctr.showMoney(uId)).contains("money: " + ctr.fakeWallet.getMoney(uId));
    }

    @Test
    public void 머니_충전(){
        String uId = "1";
        Long initMoney = ctr.fakeWallet.getMoney(uId);
        Long amount = 10000L;

        ctr.chargeMoney(uId, amount);
        Assertions.assertThat(ctr.fakeWallet.getMoney(uId)).isEqualTo(initMoney + amount);
    }
}