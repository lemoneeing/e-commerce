package com.hhplusw03.ecommerce.api.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplusw03.ecommerce.api.wallet.dto.request.ChargeData;
import com.hhplusw03.ecommerce.api.wallet.dto.request.NewReqDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResponseDto;
import com.hhplusw03.ecommerce.api.wallet.usecase.NewWalletUseCase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.BDDMockito.given;
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
                .willReturn(new WalletResponseDto(Long.parseLong(userId), 0L));

        String newReqContent = objMapper.writeValueAsString(new NewReqDto(userId));

        mvc.perform(post(BASE_URL + "/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newReqContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").exists())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.balance").exists())
                .andExpect(jsonPath("$.balance").value("0"));

        verify(createUc).execute(userId);
    }
    @BeforeAll
    public static void beforeAll(){
        Long firstUserID = 1L;
        Wallet firstWallet = new Wallet(firstUserID);
        walletModifierRepoStub.repo.put(firstUserID, firstWallet);
    }

    @Test
    public void 존재하지_않는_사용자의_잔액_조회_실패() throws Exception {
        String uId = "9999";

        mvc.perform(get(BASE_URL + "/balance?userId=" + uId))
                .andExpect((ResultMatcher) jsonPath("$[*].message", Matchers.everyItem(Matchers.containsString("Not Found User"))));
    }

    @Test
    public void 머니_충전() throws Exception {
        String userId = "1";
        String amount = "1000";

        String content = objMapper.writeValueAsString(new ChargeData(userId, amount));

        mvc.perform(patch(BASE_URL + "/balance/charge").
                        contentType(MediaType.APPLICATION_JSON).
                        content(content))
                .andExpect((ResultMatcher) jsonPath("$[*].message", Matchers.everyItem(Matchers.containsString("Not Found User"))));
    }
}