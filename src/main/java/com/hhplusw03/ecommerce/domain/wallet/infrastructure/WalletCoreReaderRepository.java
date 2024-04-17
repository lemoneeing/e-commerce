package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.Wallet;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletCoreReaderRepository implements WalletReaderRepository {

    private final WalletJpaRepository walletJpaRepo;
    @Override
    public Wallet findById(Long walletId) throws RuntimeException{
        return this.walletJpaRepo.findById(walletId).get();
    }

    @Override
    public Wallet findByUserId(Long userId) {
        Optional<Wallet> opt = this.walletJpaRepo.findByUserId(userId);  //JPA 사용법 모름...
        if (opt.isPresent()) return opt.get();
        else throw new RuntimeException("그런 사용자 지갑 없음.");
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return this.walletJpaRepo.existsByUserId(userId);
    }

    @Override
    public Long findBalanceByUserId(Long userId) {
        return null;
    }
}
