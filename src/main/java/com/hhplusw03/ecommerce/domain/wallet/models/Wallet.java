package com.hhplusw03.ecommerce.domain.wallet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    Long userId;

    Long balance;

    public Wallet(Long userId){
        this.userId = userId;
        this.balance = 0L;
    }

    public WalletDto toDto(){
        return WalletDto.builder()
                .userId(this.userId)
                .balance(this.balance)
                .build();
    }
}
