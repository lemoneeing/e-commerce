package com.hhplusw03.ecommerce.domain.wallet.components;

import com.hhplusw03.ecommerce.domain.wallet.infrastructure.WalletCoreCreatorRepository;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletCreator {

    private final WalletCoreCreatorRepository walletRepo;

    public WalletDto createWallet(Long userId){
        Wallet wallet = new Wallet(userId);
        walletRepo.create(wallet);

        return wallet.toDto();
    }
}
