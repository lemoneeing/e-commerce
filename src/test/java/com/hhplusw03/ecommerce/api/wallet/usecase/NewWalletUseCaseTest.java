package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletCreator;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({NewWalletUseCase.class, WalletCreator.class})
public class NewWalletUseCaseTest {
    @MockBean
    WalletCreator creator;
    @MockBean
    WalletReader reader;

    @Autowired
    NewWalletUseCase newWalletUc;

    @Test
    public void 지갑_생성_서비스(){
        Long userId = 1L;
        when(reader.checkWalletExistByUserId(userId)).thenReturn(false);
        when(creator.createWallet(userId)).thenReturn(new Wallet(userId).toDto());

        ResponseEntity<ResponseDto> walletResDto = newWalletUc.execute(String.valueOf(userId));

        Assertions.assertThat(walletResDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(walletResDto.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(walletResDto.getBody()).hasFieldOrPropertyWithValue("balance", "0");

        verify(reader, times(1)).checkWalletExistByUserId(userId);
        verify(creator, times(1)).createWallet(userId);
    }

    @Test
    public void 이미_존재하는_지갑은_생성_실패(){
        Long userId = 1L;
        when(reader.checkWalletExistByUserId(userId)).thenReturn(true);

        ResponseEntity<ResponseDto> alreadyCreatedDto = newWalletUc.execute(String.valueOf(userId));

        Assertions.assertThat(alreadyCreatedDto).isInstanceOf(ResponseEntity.class);
        Assertions.assertThat(alreadyCreatedDto.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(alreadyCreatedDto.getBody()).hasFieldOrPropertyWithValue("message", "Already Created Wallet.");

        verify(reader, times(1)).checkWalletExistByUserId(userId);
        verify(creator, times(0)).createWallet(userId);
    }
}