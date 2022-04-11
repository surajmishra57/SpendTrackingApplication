package com.incs.SpendTrackingApplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Wallet {
    @Id
    private String walletId;
    private Double walletAmount;
    @OneToOne(mappedBy = "userWallet")
    @JsonIgnore
    private User userWallet;

    public Wallet(){

    }
    public Wallet(String walletId, Double walletAmount, User userWallet) {
        this.walletId = walletId;
        this.walletAmount = walletAmount;
        this.userWallet = userWallet;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public Double getWalletAmount() {
        return walletAmount;
    }

    public void setWalletAmount(Double walletAmount) {
        this.walletAmount = walletAmount;
    }

    public User getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(User userWallet) {
        this.userWallet = userWallet;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "walletId='" + walletId + '\'' +
                ", walletAmount=" + walletAmount +
                ", userWallet=" + userWallet +
                '}';
    }
}
