package com.hhplusw03.ecommerce.api.wallet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hhplusw03.ecommerce.api.wallet.dto.request.ChargeData;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletModifierRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest
public class WalletControllerTest implements InitializingBean {

    class WalletModifierRepositoryStub implements WalletModifierRepository{
        public final Map<Long, Wallet> repo = new HashMap<>();

        @Override
        public Wallet modify(Wallet wallet, Long money) {
            Long userId = wallet.getUserId();

            wallet.setBalance(money);

            repo.put(userId, wallet);

            return repo.get(userId);
        }
    }

    @Autowired
    static WalletModifierRepositoryStub walletModifierRepoStub;

    @Autowired
    private MockMvc mvc;

    private final String BASE_URL = "/wallet";

    @Autowired
    ObjectMapper objMApper;


    @Override
    public void afterPropertiesSet() throws Exception {

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

        String content = objMApper.writeValueAsString(new ChargeData(userId, amount));

        mvc.perform(patch(BASE_URL + "/balance/charge").contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect((ResultMatcher) jsonPath("$[*].message", Matchers.everyItem(Matchers.containsString("Not Found User"))));
    }
}