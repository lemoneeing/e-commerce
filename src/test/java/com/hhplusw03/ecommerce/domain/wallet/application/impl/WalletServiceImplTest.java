package com.hhplusw03.ecommerce.domain.wallet.application.impl;

import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletResult;
import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Import({WalletServiceImpl.class, WalletRepository.class})
class WalletServiceImplTest {

    @MockBean
    WalletRepository dao;
    @Autowired
    WalletServiceImpl svc;

    @Test
    public void 지갑_생성_서비스_성공(){
        String userId = "1";

        WalletEntity walletEntity = new WalletEntity(Long.parseLong(userId));
        when(dao.saveWallet(Long.parseLong(userId))).thenReturn(walletEntity);

        WalletResult walletRes = svc.createWallet(userId);

        Assertions.assertThat(walletRes).isInstanceOf(WalletDto.class);
        Assertions.assertThat(walletRes.isValidated()).isEqualTo(true);
        Assertions.assertThat(((WalletDto)walletRes).getUserId()).isEqualTo(Long.parseLong(userId));
        Assertions.assertThat(((WalletDto)walletRes).getBalance()).isEqualTo(0L);

        verify(dao, times(1)).saveWallet(Long.parseLong(userId));
    }

//    @Test
//    public void 이미_존재하는_사용자ID는_지갑_생성_서비스_실패(){
//        String userId = "1";
//
//        WalletResult walletRes = svc.createWallet(userId);
//
//        Assertions.assertThat(walletRes).isInstanceOf(WalletResult.class);
//        Assertions.assertThat(walletRes.isValidated()).isEqualTo(false);
//
//        verify(dao, times(0)).saveWallet(Long.parseLong(userId));
//    }

    @Test
    public void 잔액_조회_서비스_성공(){
        String userId = "1";
        String initBalance = "1000";

        // 조회 시험
        WalletEntity walletEntity = new WalletEntity(Long.parseLong(userId), Long.parseLong(initBalance));
        when(dao.readWallet(Long.parseLong(userId))).thenReturn(walletEntity);

        WalletResult walletRes = svc.readWallet(userId);

        Assertions.assertThat(walletRes).isInstanceOf(WalletDto.class);
        Assertions.assertThat(((WalletDto)walletRes).getUserId()).isEqualTo(Long.parseLong(userId));
        Assertions.assertThat(((WalletDto)walletRes).getBalance()).isEqualTo(Long.parseLong(initBalance));

        verify(dao, times(1)).readWallet(Long.parseLong(userId));
    }

    @Test
    public void 지갑_충전_서비스(){
        String userId = "1";
        String initBalance = "0";
        //지갑 데이터 생성
        when(dao.saveWallet(Long.parseLong(userId))).thenReturn(new WalletEntity(Long.parseLong(userId), Long.parseLong(initBalance)));
        svc.createWallet(userId);

        String amountToCharge = "50000";
        // 충전 시험
        WalletEntity walletEntity = new WalletEntity(Long.parseLong(userId), Long.parseLong(initBalance));
//        when(dao.modifyWallet(Long.parseLong(userId))).thenReturn(walletEntity);

        WalletResult walletRes = svc.readWallet(userId);

        Assertions.assertThat(walletRes).isInstanceOf(WalletDto.class);
        Assertions.assertThat(((WalletDto)walletRes).getUserId()).isEqualTo(Long.parseLong(userId));
        Assertions.assertThat(((WalletDto)walletRes).getBalance()).isEqualTo(Long.parseLong(initBalance));

        verify(dao, times(1)).readWallet(Long.parseLong(userId));
    }
//    @Test
//    public void 존재하지_않는_사용자의_잔액_조회_서비스_실패(){ // -> 잔액 조회 서비스의 실패는 결국 DAP 에서 findByUserID() 가 Error 를 발생시켜야 함. 이 경우, service 시험 보다는 repository 시험이 더 적절?
//        String userId = "1";
//        String initBalance = "1000";
//
//        // 조회 시험을 위한 지갑 Data 생성
//        when(dao.saveWallet(Long.parseLong(userId))).thenReturn(new WalletEntity(Long.parseLong(userId), Long.parseLong(initBalance)));
//        svc.createWallet(userId);
//
//        // 조회 시험
//        WalletEntity walletEntity = new WalletEntity(Long.parseLong(userId), Long.parseLong(initBalance));
//        when(dao.readWallet(Long.parseLong(userId))).thenReturn(walletEntity);
//
//        WalletResult walletRes = svc.readWallet("2");
//
//        Assertions.assertThat(walletRes).isNull();
//
////        verify(dao, times(1)).checkUserOwnsWallet(Long.parseLong(userId));
//        verify(dao, times(1)).readWallet(Long.parseLong(userId));
//    }
}