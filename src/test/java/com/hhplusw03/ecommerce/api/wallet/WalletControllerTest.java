package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.request.WalletReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.CreateWalletUsecase;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@Import({WalletController.class, CreateWalletUsecase.class})
class WalletControllerTest {
    @MockBean
    CreateWalletUsecase createWalletUsecase;

    @Autowired
    WalletController controller;

    @Test
    public void 지갑_생성_요청_처리_성공(){
        String userId = "1";

        WalletDto walletDto = new WalletDto(Long.parseLong(userId), 0L);
        when(createWalletUsecase.execute(userId)).thenReturn(new WalletResDto(walletDto));

        ResponseEntity<ResponseDto> walletResDto = controller.newWallet(new WalletReqDto(userId));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(((WalletResDto)walletResDto.getBody()).getUserId()).isEqualTo(userId);
        Assertions.assertThat(((WalletResDto)walletResDto.getBody()).getBalance()).isEqualTo("0");

        verify(createWalletUsecase, times(1)).execute(userId);
    }
}