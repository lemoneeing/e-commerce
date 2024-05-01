package com.hhplusw03.ecommerce.domain.wallet.repository;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletJpaRepository extends JpaRepository<WalletEntity, Long> {
    Optional<WalletEntity> findByUserId(Long userId);
}
