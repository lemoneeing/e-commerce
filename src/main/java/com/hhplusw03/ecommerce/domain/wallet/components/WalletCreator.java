package com.hhplusw03.ecommerce.domain.wallet.components;

import com.hhplusw03.ecommerce.domain.wallet.infrastructure.WalletCoreCreatorRepository;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletCreator {

    private final WalletCoreCreatorRepository walletRepo;

    public void createWalletByUserId(Long userId){
        Wallet wallet = new Wallet(userId);
        walletRepo.create(wallet);
    }
}
