package com.hhplusw03.ecommerce.domain.wallet.components;

import com.hhplusw03.ecommerce.domain.wallet.infrastructure.WalletCoreModifierRepository;
import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletModifier {

    private final WalletCoreModifierRepository walletRepo;

    public void modifyBalanceByUserIdAndAmount(Wallet wallet, Long totalMoney){
        walletRepo.modify(wallet, totalMoney);
    }
}
