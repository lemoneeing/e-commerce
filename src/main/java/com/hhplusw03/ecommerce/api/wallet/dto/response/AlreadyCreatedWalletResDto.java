package com.hhplusw03.ecommerce.api.wallet.dto.response;

import lombok.Getter;

@Getter
public class AlreadyCreatedWalletResDto implements ResponseDto{
    private String message = "Already Created Wallet.";
}
