package com.hhplusw03.ecommerce.domain.wallet.components;

import com.hhplusw03.ecommerce.domain.wallet.infrastructure.WalletCoreReaderRepository;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletReader {

    private final WalletCoreReaderRepository walletRepo;

    public Long readBalanceByUserId(Long userId){
        try {
            Wallet wallet = walletRepo.findByUserId(userId);
            return wallet.getBalance();
        } catch (RuntimeException re){
            return -1L;
        }
    }

    public Boolean checkWalletExistByUserId(Long userId){
        try {
            Wallet wallet = walletRepo.findByUserId(userId);
            return true;
        }
        catch (RuntimeException re){
            return false;
        }
    }
}
