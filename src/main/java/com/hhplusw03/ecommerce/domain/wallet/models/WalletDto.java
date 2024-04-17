package com.hhplusw03.ecommerce.domain.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WalletDto {

    Long userId;

    Long balance;
}
