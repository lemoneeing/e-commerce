package com.hhplusw03.ecommerce.api.wallet.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChargeReqDto {
    private String userId;
    private String amount;
}
