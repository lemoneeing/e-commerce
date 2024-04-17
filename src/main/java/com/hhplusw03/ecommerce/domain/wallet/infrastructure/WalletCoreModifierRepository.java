package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletModifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WalletCoreModifierRepository implements WalletModifierRepository {

    private final WalletJpaRepository walletJpaRepo;

    @Override
    public WalletDto modify(WalletDto dto) {
        return walletJpaRepo.save(dto.toEntity()).toDto();
    }
}
