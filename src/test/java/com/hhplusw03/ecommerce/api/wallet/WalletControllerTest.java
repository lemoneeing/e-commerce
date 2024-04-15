package com.hhplusw03.ecommerce.api.wallet;

import com.hhplusw03.ecommerce.api.wallet.dto.request.WalletReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.AlreadyCreatedWalletResDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.NotFoundUserResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.BalanceUseCase;
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

    // 이게 stub 이 맞는지는 모르겠으나 가상의 WalletModifierRepository 를 구현함.
//    class WalletModifierRepositoryStub implements WalletModifierRepository{
//        public final Map<Long, Wallet> repo = new HashMap<>();
//
//        @Override
//        public Wallet modify(Wallet wallet, Long money) {
//            Long userId = wallet.getUserId();
//
//            wallet.setBalance(money);
//
//            repo.put(userId, wallet);
//
//            return repo.get(userId);
//        }
//    }
//
//    @Autowired
//    static WalletModifierRepositoryStub walletModifierRepoStub;

    @Autowired
    private MockMvc mvc;

    @MockBean
    NewWalletUseCase createUc;
    @MockBean
    BalanceUseCase balanceUc;

    private final String BASE_URL = "/wallet";

    @Autowired
    ObjectMapper objMapper;

//    @BeforeAll
//    public void beforeEach(){
//        Long firstUserID = 1L;
//        Wallet firstWallet = new Wallet(firstUserID);
//        walletModifierRepoStub.repo.put(firstUserID, firstWallet);
//    }

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
                .willReturn(new ResponseEntity<>(new AlreadyCreatedWalletResDto(), HttpStatus.BAD_REQUEST));

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
//        createUc.execute(userId); // Controller Unit Test 이므로 createUsecase 를 실행할 필요가 없는 게 맞나...? 해당 절차가 없으면 실제 시나리오와 달라지게 됨. 하지만 없어도 Test 는 동작함.

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
                .willReturn(new ResponseEntity<>(new NotFoundUserResponseDto(), HttpStatus.BAD_REQUEST));

        String newReqContent = objMapper.writeValueAsString(new WalletReqDto(userId));

        mvc.perform(get(BASE_URL + "/balance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.message").value("Not Found User."));

        verify(balanceUc, times(1)).execute(userId);
    }

//    @Test
//    public void 머니_충전() throws Exception {
//        String userId = "1";
//        String amount = "1000";
//
//        String content = objMapper.writeValueAsString(new ChargeData(userId, amount));
//
//        mvc.perform(patch(BASE_URL + "/balance/charge").
//                        contentType(MediaType.APPLICATION_JSON).
//                        content(content))
//                .andExpect((ResultMatcher) jsonPath("$[*].message", Matchers.everyItem(containsString("Not Found User"))));
//    }
}