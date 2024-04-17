package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletModifier;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ChargeUseCase.class, WalletReader.class, WalletModifier.class})
class ChargeUseCaseTest {
    @MockBean
    WalletReader reader;
    @MockBean
    WalletModifier modifier;

    @Autowired
    ChargeUseCase chargeUc;

    @Test
    public void 지갑_충전(){
        Long userId = 1L;
        Long amount = 10000L;

        Wallet wallet = new Wallet(userId);

        when(reader.readBalanceByUserId(userId)).thenReturn(0L);
        when(modifier.modifyBalance(userId, amount)).thenReturn(new WalletDto(userId, amount));

        ResponseEntity<ResponseDto> walletResDto = chargeUc.execute(String.valueOf(userId), String.valueOf(amount));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("userId", String.valueOf(userId));
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("balance", String.valueOf(amount));

        verify(reader, times(1)).readBalanceByUserId(userId);
        verify(modifier, times(1)).modifyBalance(userId, amount);
    }

    @Test
    public void 최소_금액_미만_충전_실패(){
        Long userId = 1L;
        Long amount = 9999L;

        Wallet wallet = new Wallet(userId);

        when(reader.readBalanceByUserId(userId)).thenReturn(0L);

        ResponseEntity<ResponseDto> walletResDto = chargeUc.execute(String.valueOf(userId), String.valueOf(amount));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("message", "Too little amount to charge.");

        verify(reader, times(0)).readBalanceByUserId(userId);
        verify(modifier, times(0)).modifyBalance(userId, amount);
    }
}