package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WalletRepositoryImplTest {
    private WalletJpaRepository jpaRepo;
    private WalletRepositoryImpl coreRepo;

    @BeforeEach
    void setUp() {
        this.jpaRepo = mock(WalletJpaRepository.class);
        coreRepo = new WalletRepositoryImpl(jpaRepo);
    }

    @Test
    public void 지갑_생성(){
        Long userId = 4L;

        WalletEntity wallet = new WalletEntity(userId);
        when(this.jpaRepo.save(any(WalletEntity.class))).thenAnswer(i->i.getArguments()[0]); // thenAnswer() 의 동작 원리에 대해 아직 잘 모름...

        WalletEntity savedWallet = this.coreRepo.saveWallet(userId);
        Assertions.assertThat(savedWallet).isInstanceOf(WalletEntity.class);
    }

    @Test
    public void 지갑_충전(){
        Long userId = 1L;

        WalletEntity wallet = new WalletEntity(userId);
        when(this.jpaRepo.findByUserId(userId)).thenReturn(Optional.of(wallet));

        Long newBalance = 50000L;
        wallet.setBalance(newBalance);
        when(this.jpaRepo.save(wallet)).thenReturn(wallet);

        WalletEntity chargedWallet = this.coreRepo.modifyBalance(wallet, newBalance); // 여기도 역시나 null 이 반환... why?!?!?
        Assertions.assertThat(chargedWallet).isEqualTo(wallet);
    }
}