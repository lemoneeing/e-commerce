package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;


public interface WalletRepository {
    public WalletEntity saveWallet(Long userId);
    public WalletEntity readWallet(Long userId);
}
