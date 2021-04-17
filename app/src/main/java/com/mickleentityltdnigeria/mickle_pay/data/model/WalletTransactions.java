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
    public String userIP;
    public String userID;
    public String transactDesc;
    public double transactAmount;
    public String transactType;
    public String beneficiaryWalletID;

    public WalletTransactions() {
    }

    public WalletTransactions(Timestamp timestamp, String walletID, String authID, String userIP, String userID, String transactDesc, double transactAmount, String transactType, String beneficiaryWalletID) {
        this.timestamp = timestamp;
        this.walletID = walletID;
        this.authID = authID;
        this.userIP = userIP;
        this.userID = userID;
        this.transactDesc = transactDesc;
        this.transactAmount = transactAmount;
        this.transactType = transactType;
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
    public String getUserIP() {
        return userIP;
    }

    @Exclude
    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    @Exclude
    public String getUserID() {
        return userID;
    }

    @Exclude
    public void setUserID(String userID) {
        this.userID = userID;
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
    public String getTransactType() {
        return transactType;
    }

    @Exclude
    public void setTransactType(String transactType) {
        this.transactType = transactType;
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
