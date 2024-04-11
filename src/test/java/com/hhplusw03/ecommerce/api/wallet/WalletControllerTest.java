package com.hhplusw03.ecommerce.api.wallet;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@AutoConfigureMockMvc
@SpringBootTest
public class WalletControllerTest {
    @Autowired
    private MockMvc mvc;
    private final String BASE_URL = "/wallet";
    @Test
    public void 존재하지_않는_사용자의_잔액_조회_실패() throws Exception {
        String uId = "9999";

        mvc.perform(get(BASE_URL + "/balance?userId=" + uId))
                .andExpect((ResultMatcher) jsonPath("$[*].message", Matchers.everyItem(Matchers.containsString("Not Found User"))));
    }
}