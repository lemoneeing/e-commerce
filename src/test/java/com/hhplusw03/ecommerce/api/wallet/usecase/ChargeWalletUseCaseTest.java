package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@Import({ChargeWalletUseCase.class, WalletService.class})
class ChargeWalletUseCaseTest {
    @MockBean
    WalletService svc;
    @Autowired
    ChargeWalletUseCase uc;

    @Test
    public void 지갑_충전_유스케이스(){

    }
}