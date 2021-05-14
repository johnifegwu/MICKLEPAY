package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class SendMoney implements Serializable {

    public String ID;
    public String userID;
    public Timestamp timestamp;
    public String authID;
    public String customerIP;
    public String customerID;
    public String transactionDesc;
    public String debitWalletID;
    public String debitWalletCurrency;
    public double debitAmount;
    public String creditWalletID;
    public String creditWalletCurrency;
    public double creditAmount;

    public SendMoney() {
    }

    public SendMoney(String ID, String userID, Timestamp timestamp, String authID, String customerIP, String customerID, String transactionDesc, String debitWalletID, String debitWalletCurrency, double debitAmount, String creditWalletID, String creditWalletCurrency, double creditAmount) {
        this.ID = ID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.transactionDesc = transactionDesc;
        this.debitWalletID = debitWalletID;
        this.debitWalletCurrency = debitWalletCurrency;
        this.debitAmount = debitAmount;
        this.creditWalletID = creditWalletID;
        this.creditWalletCurrency = creditWalletCurrency;
        this.creditAmount = creditAmount;
    }

    @Exclude
    public String getID() {
        return ID;
    }

    @Exclude
    public void setID(String ID) {
        this.ID = ID;
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
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Exclude
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
    public String getTransactionDesc() {
        return transactionDesc;
    }

    @Exclude
    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }

    @Exclude
    public String getDebitWalletID() {
        return debitWalletID;
    }

    @Exclude
    public void setDebitWalletID(String debitWalletID) {
        this.debitWalletID = debitWalletID;
    }

    @Exclude
    public String getDebitWalletCurrency() {
        return debitWalletCurrency;
    }

    @Exclude
    public void setDebitWalletCurrency(String debitWalletCurrency) {
        this.debitWalletCurrency = debitWalletCurrency;
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
    public String getCreditWalletID() {
        return creditWalletID;
    }

    @Exclude
    public void setCreditWalletID(String creditWalletID) {
        this.creditWalletID = creditWalletID;
    }

    @Exclude
    public String getCreditWalletCurrency() {
        return creditWalletCurrency;
    }

    @Exclude
    public void setCreditWalletCurrency(String creditWalletCurrency) {
        this.creditWalletCurrency = creditWalletCurrency;
    }

    @Exclude
    public double getCreditAmount() {
        return creditAmount;
    }

    @Exclude
    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }
}
