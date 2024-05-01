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

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({WalletRepositoryImpl.class, WalletJpaRepository.class})
class WalletRepositoryImplImplTest {
    @MockBean
    private WalletJpaRepository jpaRepo;
    @Autowired
    private WalletRepositoryImpl coreRepo;// = new WalletRepositoryImpl(jpaRepo);

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
        when(jpaRepo.save(wallet)).thenReturn(wallet);

        WalletEntity savedWallet = coreRepo.saveWallet(userId); // 반환 값이 null 인 이유 ????
        Assertions.assertThat(savedWallet).isEqualTo(wallet);
    }
}