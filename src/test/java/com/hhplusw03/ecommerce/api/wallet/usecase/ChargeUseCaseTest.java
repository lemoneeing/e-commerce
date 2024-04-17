package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.wallet.dto.response.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletModifier;
import com.hhplusw03.ecommerce.domain.wallet.components.WalletReader;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({ChargeUseCase.class, WalletReader.class, WalletModifier.class})
class ChargeUseCaseTest {
    @MockBean
    WalletReader reader;
    @MockBean
    WalletModifier modifier;

    Map<Long, Wallet> walletRepoStub = new HashMap<Long, Wallet>();

    @Autowired
    ChargeUseCase chargeUc;

//    @Test
//    public void 지갑_충전() throws Exception {
//
//        String userId = "1";
//        String amount = "1000000";
//
//        given(chargeUc.execute(userId, amount))
//                .willReturn(new ResponseEntity<>(new WalletResDto(1L, Long.parseLong(amount)), HttpStatus.OK));
//
//        String newReqContent = objMapper.writeValueAsString(new ChargeReqDto(userId, amount));
//
//        mvc.perform(patch(BASE_URL + "/charge")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(newReqContent))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.userId").exists())
//                .andExpect(jsonPath("$.userId").value(userId))
//                .andExpect(jsonPath("$.balance").exists())
//                .andExpect(jsonPath("$.balance").value(amount));
//
//        verify(chargeUc, times(1)).execute(userId, amount);
//    }

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
        verify(modifier, times(0)).modifyBalanceByUserIdAndAmount(wallet, amount);
    }
}