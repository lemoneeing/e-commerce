package com.hhplusw03.ecommerce.domain.wallet.application.impl;

import com.hhplusw03.ecommerce.domain.wallet.application.WalletResult;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import com.hhplusw03.ecommerce.domain.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository dao){
        this.walletRepository = dao;
    }

    @Override
    public WalletResult createWallet(String userId) {
        Long ownerId = Long.parseLong(userId);

//        //Service 에서 if 문을 쓰지 않으려면? -> 객체 지향 사용. but, 다형성이 필요 없는 지금과 같은 상황에선 굳이 해야하는가?
//        if (walletDao.checkUserOwnsWallet(ownerId)) {
//            WalletResult inValidatedDto = new WalletResult(false);
//            return inValidatedDto;
//        }
//        else{
//        }
        WalletResult walletDto = new WalletDto(walletRepository.saveWallet(ownerId));
        return walletDto;
    }

    @Override
    public WalletResult readWallet(String userId){
        Long ownerId = Long.parseLong(userId);

        WalletResult walletRes = new WalletDto(walletRepository.readWallet(ownerId));
        return walletRes;
    }
}
