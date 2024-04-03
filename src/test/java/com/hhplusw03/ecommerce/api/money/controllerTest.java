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
}