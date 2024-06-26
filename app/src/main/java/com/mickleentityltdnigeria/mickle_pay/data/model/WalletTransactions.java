 package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@IgnoreExtraProperties
public class WalletTransactions  implements Serializable {

    public Timestamp timestamp;
    public String walletID;
    public String authID;
    public String customerIP;
    public String customerID;
    public String transactDesc;
    public double debitAmount;
    public double creditAmount;
    public Date valueDate;
    public String transactionType;
    public String transactionID;
    public String beneficiaryWalletID; //@Nullable

    public WalletTransactions() {
    }

    public WalletTransactions(Timestamp timestamp, String walletID, String authID, String customerIP, String customerID, String transactDesc, double debitAmount, double creditAmount, Date valueDate, String transactionType, String transactionID, String beneficiaryWalletID) {
        this.timestamp = timestamp;
        this.walletID = walletID;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.transactDesc = transactDesc;
        this.debitAmount = debitAmount;
        this.creditAmount = creditAmount;
        this.valueDate = valueDate;
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
    public double getDebitAmount() {
        return debitAmount;
    }

    @Exclude
    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    @Exclude
    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
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
