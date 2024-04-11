package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;

public interface WalletCreatorRepository {
    public Wallet save(Wallet wallet);
}
