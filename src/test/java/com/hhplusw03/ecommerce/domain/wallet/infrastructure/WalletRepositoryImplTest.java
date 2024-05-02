package com.hhplusw03.ecommerce.domain.wallet.infrastructure;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletJpaRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({WalletRepositoryImpl.class})
class WalletRepositoryImplTest {
    @MockBean
    private WalletJpaRepository jpaRepo;
    @Autowired
    private WalletRepositoryImpl coreRepo;

//    @BeforeEach
//    void setUp() {
//        jpaRepo = mock(WalletJpaRepository.class);
//
//        coreRepo = new WalletRepositoryImpl(jpaRepo);
//    }

    @Test
    public void 지갑_생성(){
        Long userId = 4L;

        WalletEntity wallet = new WalletEntity(userId);
        when(this.jpaRepo.save(new WalletEntity(userId))).thenReturn(wallet);

        WalletEntity savedWallet = this.coreRepo.saveWallet(userId); // 반환 값이 null 인 이유 ????
        Assertions.assertThat(savedWallet).isEqualTo(wallet);
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