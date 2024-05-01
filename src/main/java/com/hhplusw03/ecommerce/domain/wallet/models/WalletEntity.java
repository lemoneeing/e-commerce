package com.hhplusw03.ecommerce.domain.wallet.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name="Wallet")
public class WalletEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    Long userId;

    @Column
    Long balance;

    public WalletEntity(Long userId){
        this.userId = userId;
        this.balance = 0L;
    }
    
    // Test 때, 잔액이 0 이상인 Wallet 을 만들기 위해 필요
    public WalletEntity(Long userId, Long initAmount){
        this.userId = userId;
        this.balance = initAmount;
    }

    public Long setBalance(Long amount){
        this.balance = amount;
        return this.balance;
    }
}
