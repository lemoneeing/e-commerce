package com.hhplusw03.ecommerce.domain.wallet.application;

public class WalletResult {
    private boolean validated;

    public WalletResult(boolean validated){
        this.validated = validated;
    }

    public boolean setValidated(boolean validated){
        this.validated = validated;
        return this.validated;
    }

    public boolean isValidated(){
        return this.validated;
    }

}
