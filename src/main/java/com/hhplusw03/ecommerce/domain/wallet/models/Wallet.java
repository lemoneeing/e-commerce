package com.hhplusw03.ecommerce.domain.wallet.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Wallet {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    Long userId;

    @Column
    Long balance;

    public Wallet(Long userId){
        this.userId = userId;
        this.balance = 0L;
    }

    public Wallet(Long userId, Long initAmount){
        this.userId = userId;
        this.balance = initAmount;
    }

    public WalletDto toDto(){
        return new WalletDto(this.userId, this.balance);
    }

    public Long setBalance(Long amount){
        this.balance = amount;
        return this.balance;
    }
}
