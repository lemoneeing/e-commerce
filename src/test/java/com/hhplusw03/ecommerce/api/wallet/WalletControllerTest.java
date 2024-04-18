package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.request.ChargeReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.request.WalletReqDto;
import com.hhplusw03.ecommerce.api.responseDto.ErrorResDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.BalanceUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.ChargeUseCase;
import com.hhplusw03.ecommerce.api.wallet.usecase.NewWalletUseCase;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WalletController.class)
public class WalletControllerTest{
    @Autowired
    private MockMvc mvc;

    @MockBean
    NewWalletUseCase createUc;
    @MockBean
    BalanceUseCase balanceUc;
    @MockBean
    ChargeUseCase chargeUc;

    private final String BASE_URL = "/wallet";

    @Autowired
    ObjectMapper objMapper;

    @Test
    public void 지갑_생성() throws Exception {

        String userId = "1";

        given(createUc.execute(userId))
                .willReturn(new ResponseEntity<>(new WalletResDto(Long.parseLong(userId), 0L), HttpStatus.OK));

        String newReqContent = objMapper.writeValueAsString(new WalletReqDto(userId));

        mvc.perform(post(BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value("0"));

        // 지정한 instance(createUc) 가 지정한 함수 (execute) 을 실제로 몇 회 (1) 호출했는지 확인.
        verify(createUc, times(1)).execute(userId);
    }


    @Test
    public void 이미_지갑이_생성된_사용자의_생성_요청은_실패() throws Exception {

        String userId = "1";
        createUc.execute(userId);

        given(createUc.execute(userId))
                .willReturn(new ResponseEntity<>(new ErrorResDto("Already Created Wallet."), HttpStatus.BAD_REQUEST));

        String newReqContent = objMapper.writeValueAsString(new WalletReqDto(userId));

        mvc.perform(post(BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Already Created Wallet."));

        verify(createUc, times(2)).execute(userId);
    }

    @Test
    public void 잔액_조회() throws Exception{

        String userId = "1";

        given(balanceUc.execute(userId))
                .willReturn(new ResponseEntity<>(new WalletResDto(1L, 0L), HttpStatus.OK));

        String newReqContent = objMapper.writeValueAsString(new WalletReqDto(userId));

        mvc.perform(get(BASE_URL + "/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value("0"));

        verify(balanceUc, times(1)).execute(userId);
    }

    @Test
    public void 존재하지_않는_사용자의_잔액_조회_실패() throws Exception {
        String userId = "9999";

        given(balanceUc.execute(userId))
                .willReturn(new ResponseEntity<>(new ErrorResDto("Not Found User."), HttpStatus.BAD_REQUEST));

        String newReqContent = objMapper.writeValueAsString(new WalletReqDto(userId));

        mvc.perform(get(BASE_URL + "/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Not Found User."));

        verify(balanceUc, times(1)).execute(userId);
    }

    @Test
    public void 지갑_충전() throws Exception {

        String userId = "1";
        String amount = "1000000";

        given(chargeUc.execute(userId, amount))
                .willReturn(new ResponseEntity<>(new WalletResDto(1L, Long.parseLong(amount)), HttpStatus.OK));

        String newReqContent = objMapper.writeValueAsString(new ChargeReqDto(userId, amount));

        mvc.perform(patch(BASE_URL + "/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value(amount));

        verify(chargeUc, times(1)).execute(userId, amount);
    }

    @Test
    public void 최소_금액_미만_충전_실패() throws Exception {

        String userId = "1";
        String amount = "9999";

        given(chargeUc.execute(userId, amount))
                .willReturn(new ResponseEntity<>(new ErrorResDto("Too little amount to charge."), HttpStatus.BAD_REQUEST));

        String newReqContent = objMapper.writeValueAsString(new ChargeReqDto(userId, amount));

        mvc.perform(patch(BASE_URL + "/charge")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Too little amount to charge."));

        verify(chargeUc, times(1)).execute(userId, amount);
    }
}