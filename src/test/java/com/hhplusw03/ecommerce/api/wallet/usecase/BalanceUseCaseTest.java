package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
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
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@Import({BalanceUseCase.class, WalletReader.class})
class BalanceUseCaseTest {

    @MockBean
    WalletReader reader;
    @Autowired
    BalanceUseCase balanceUc;
    @Test
    public void 잔액_조회_서비스(){

        Long userId = 1L;
        when(reader.readBalanceByUserId(userId)).thenReturn(0L);

        ResponseEntity<ResponseDto> walletResDto = balanceUc.execute(String.valueOf(userId));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("balance", "0");

        verify(reader, times(1)).readBalanceByUserId(userId);
    }

    @Test
    public void 존재하지_않는_사용자의_잔액_조회_실패() {
        Long userId = 9999L;
        when(reader.readBalanceByUserId(userId)).thenReturn(-1L);

        ResponseEntity<ResponseDto> walletResDto = balanceUc.execute(String.valueOf(userId));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("message", "Not Found User.");

        verify(reader, times(1)).readBalanceByUserId(userId);
    }
}