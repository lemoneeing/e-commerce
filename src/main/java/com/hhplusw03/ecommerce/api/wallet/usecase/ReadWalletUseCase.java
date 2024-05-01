package com.hhplusw03.ecommerce.api.wallet.usecase;

import com.hhplusw03.ecommerce.api.responseDto.ResponseDto;
import com.hhplusw03.ecommerce.api.wallet.dto.response.WalletResDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletDto;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletResult;
import com.hhplusw03.ecommerce.domain.wallet.application.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadWalletUseCase {
    private final WalletService walletSvc;

    @Autowired
    public ReadWalletUseCase(WalletService svc){
        this.walletSvc = svc;
    }

    public ResponseDto execute(String userId){

        WalletResult readWallet = this.walletSvc.readWallet(userId);

        return new WalletResDto((WalletDto) readWallet);
    }
}
