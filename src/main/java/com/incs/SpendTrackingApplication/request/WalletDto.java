package com.incs.SpendTrackingApplication.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class WalletDto {
    private String walletId;
    @NotNull(message = "Wallet Amount Required")
    @Positive(message = "Amount Must Be Positive")
    private double walletAmount;

    public WalletDto() {
    }

    public WalletDto(String walletId, double walletAmount) {
        this.walletId = walletId;
        this.walletAmount = walletAmount;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(double walletAmount) {
        this.walletAmount = walletAmount;
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "walletId='" + walletId + '\'' +
                ", walletAmount=" + walletAmount +
                '}';
    }
}
