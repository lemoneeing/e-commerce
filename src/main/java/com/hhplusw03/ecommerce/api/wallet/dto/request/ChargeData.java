package com.hhplusw03.ecommerce.api.wallet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
public class ChargeData {
    private String userId;
    private String amount;
}
