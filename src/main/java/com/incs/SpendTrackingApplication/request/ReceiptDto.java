package com.incs.SpendTrackingApplication.request;

import java.util.Date;

public class ReceiptDto {
    private String productId;
    private String productName;
    private int productQuantity;
    private double productPrice;
    private double debitedAmount;
    private double availableWalletAmount;
    private Date transactionDate;

    public ReceiptDto() {
    }

    public ReceiptDto(String productId, String productName, int productQuantity, double productPrice, double debitedAmount, double availableWalletAmount, Date transactionDate) {
        this.productId = productId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.productPrice = productPrice;
        this.debitedAmount = debitedAmount;
        this.availableWalletAmount = availableWalletAmount;
        this.transactionDate = transactionDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getDebitedAmount() {
        return debitedAmount;
    }

    public void setDebitedAmount(double debitedAmount) {
        this.debitedAmount = debitedAmount;
    }

    public double getAvailableWalletAmount() {
        return availableWalletAmount;
    }

    public void setAvailableWalletAmount(double availableWalletAmount) {
        this.availableWalletAmount = availableWalletAmount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "ReceiptDto{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productQuantity=" + productQuantity +
                ", productPrice=" + productPrice +
                ", debitedAmount=" + debitedAmount +
                ", availableWalletAmount=" + availableWalletAmount +
                ", transactionDate=" + transactionDate +
                '}';
    }
}

