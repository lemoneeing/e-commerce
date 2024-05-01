package com.hhplusw03.ecommerce.domain.wallet.application;

import com.hhplusw03.ecommerce.domain.wallet.models.WalletEntity;


public class WalletDto extends WalletResult {
    private Long userId;
    private Long balance;

    public WalletDto(Long userId, Long balance){
        super(true);
        this.userId = userId;
        this.balance = balance;
    }

    public WalletDto(WalletEntity walletEntity){
        super(true);
        this.userId = walletEntity.getUserId();
        this.balance = walletEntity.getBalance();
    }

    public WalletEntity toEntity(){
        return new WalletEntity(this.userId);
    }

    public Long getUserId(){
        return this.userId;
    }

    public Long getBalance(){
        return this.balance;
    }
}
