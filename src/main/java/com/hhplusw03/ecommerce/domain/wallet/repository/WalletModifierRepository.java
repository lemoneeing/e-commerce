package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletDto;

public interface WalletModifierRepository {
    public WalletDto modify(WalletDto wallet);
}
