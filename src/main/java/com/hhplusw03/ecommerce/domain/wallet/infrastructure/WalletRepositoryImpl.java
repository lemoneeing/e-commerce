package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletJpaRepository;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WalletRepositoryImpl implements WalletRepository {
    private final WalletJpaRepository walletJpaRepo;
    @Autowired
    public WalletRepositoryImpl(WalletJpaRepository jpaRepo){
        this.walletJpaRepo = jpaRepo;
    }

    @Override
    public WalletEntity saveWallet(Long userId){
        return this.walletJpaRepo.save(new WalletEntity(userId));
    }

    @Override
    public WalletEntity readWallet(Long userId){
        return this.walletJpaRepo.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("다음 사용자ID 는 존재하지 않습니다. - " + userId));
    }

    @Override
    public WalletEntity modifyBalance(WalletEntity wallet, Long newBalance){
        wallet.setBalance(newBalance);
        return this.walletJpaRepo.save(wallet);
    }
}
