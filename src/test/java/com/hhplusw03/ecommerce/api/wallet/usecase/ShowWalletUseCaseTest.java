package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletResult;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({ShowWalletUseCase.class, WalletService.class})
class ShowWalletUseCaseTest {
    @MockBean
    WalletService svc;

    @Autowired
    ShowWalletUseCase uc;

    @Test
    public void 지감_조회_유스케이스(){
        String userId = "1";

        WalletResult walletRes = new WalletDto(Long.parseLong(userId), 0L);
        when(svc.readWallet(userId)).thenReturn(walletRes);

        ResponseDto readWallet = uc.execute(userId);

        Assertions.assertThat(readWallet).isInstanceOf(WalletResDto.class);
        Assertions.assertThat(((WalletResDto)readWallet).getUserId()).isEqualTo(userId);
        Assertions.assertThat(((WalletResDto)readWallet).getBalance()).isEqualTo("0");
    }
}