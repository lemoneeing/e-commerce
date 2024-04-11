package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletCreatorRepository;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletCoreCreatorRepository implements WalletCreatorRepository {

    private final WalletJpaRepository walletJpaRepo;
    @Override
    public Wallet save(Wallet wallet) {
        return this.walletJpaRepo.save(wallet);
    }

}
