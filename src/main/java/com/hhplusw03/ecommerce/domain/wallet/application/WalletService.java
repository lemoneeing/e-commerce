package com.hhplusw03.ecommerce.domain.wallet.application;

public interface WalletService {
    public WalletResult createWallet(String userId);
    public WalletResult readWallet(String userId);
}
