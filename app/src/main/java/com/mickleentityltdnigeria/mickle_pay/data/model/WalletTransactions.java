package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class WalletTransactions  implements Serializable {

    public Timestamp timestamp;
    public String walletID;
    public String authID;
    public String customerIP;
    public String customerID;
    public String transactDesc;
    public double transactAmount;
    public String transactionType;
    public String transactionID;
    public String beneficiaryWalletID; //@Nullable

    public WalletTransactions() {
    }

    public WalletTransactions(Timestamp timestamp, String walletID, String authID, String customerIP, String customerID, String transactDesc, double transactAmount, String transactionType, String transactionID, String beneficiaryWalletID) {
        this.timestamp = timestamp;
        this.walletID = walletID;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.transactDesc = transactDesc;
        this.transactAmount = transactAmount;
        this.transactionType = transactionType;
        this.transactionID = transactionID;
        this.beneficiaryWalletID = beneficiaryWalletID;
    }

    @Exclude
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Exclude
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Exclude
    public String getWalletID() {
        return walletID;
    }

    @Exclude
    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    @Exclude
    public String getAuthID() {
        return authID;
    }

    @Exclude
    public void setAuthID(String authID) {
        this.authID = authID;
    }

    @Exclude
    public String getCustomerIP() {
        return customerIP;
    }

    @Exclude
    public void setCustomerIP(String customerIP) {
        this.customerIP = customerIP;
    }

    @Exclude
    public String getCustomerID() {
        return customerID;
    }

    @Exclude
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    @Exclude
    public String getTransactDesc() {
        return transactDesc;
    }

    @Exclude
    public void setTransactDesc(String transactDesc) {
        this.transactDesc = transactDesc;
    }

    @Exclude
    public double getTransactAmount() {
        return transactAmount;
    }

    @Exclude
    public void setTransactAmount(double transactAmount) {
        this.transactAmount = transactAmount;
    }

    @Exclude
    public String getTransactionType() {
        return transactionType;
    }

    @Exclude
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    @Exclude
    public String getTransactionID() {
        return transactionID;
    }

    @Exclude
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    @Exclude
    public String getBeneficiaryWalletID() {
        return beneficiaryWalletID;
    }

    @Exclude
    public void setBeneficiaryWalletID(String beneficiaryWalletID) {
        this.beneficiaryWalletID = beneficiaryWalletID;
    }
}
