package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;

public interface WalletReaderRepository {
    public Wallet findById(Long walletId);

    public Wallet findByUserId(Long userId);

    Boolean existsByUserId(Long userId);

    public Long findBalanceByUserId(Long userId);
}
