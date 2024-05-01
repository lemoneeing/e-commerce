package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.impl.WalletServiceImpl;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({CreateWalletUsecase.class, WalletServiceImpl.class})
class CreateWalletUsecaseTest {
    @MockBean
    WalletServiceImpl svc;
    @Autowired
    CreateWalletUsecase uc;

    @Test
    public void 지갑_생성_유스케이스_성공(){
        String userId = "1";

        WalletEntity walletEntity = new WalletEntity(Long.parseLong(userId));
        when(svc.createWallet(userId)).thenReturn(new WalletDto(walletEntity));

        ResponseDto walletDto = uc.execute(userId);

        Assertions.assertThat(walletDto).isInstanceOf(WalletResDto.class);
        Assertions.assertThat(((WalletResDto)walletDto).getUserId()).isEqualTo(userId);
        Assertions.assertThat(((WalletResDto)walletDto).getBalance()).isEqualTo("0");

        verify(svc, times(1)).createWallet(userId);
    }
}