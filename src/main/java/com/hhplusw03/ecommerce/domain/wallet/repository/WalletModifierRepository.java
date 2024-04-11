package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;

public interface WalletModifierRepository {
    public Wallet modify(Wallet wallet, Long money);
}
