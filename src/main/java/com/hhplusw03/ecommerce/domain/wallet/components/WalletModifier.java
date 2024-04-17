package com.hhplusw03.ecommerce.domain.wallet.components;

import com.hhplusw03.ecommerce.domain.wallet.infrastructure.WalletCoreModifierRepository;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletModifier {

    private final WalletCoreModifierRepository walletRepo;

    public WalletDto modifyBalance(Long ownerId, Long amount){
        WalletDto dto = new WalletDto(ownerId, amount);
        return walletRepo.modify(dto);
    }
}
