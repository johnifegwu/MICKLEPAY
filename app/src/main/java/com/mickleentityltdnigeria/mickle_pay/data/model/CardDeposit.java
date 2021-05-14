package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class CardDeposit implements Serializable {

    public String ID;
    public String userID;
    public Timestamp timestamp;
    public String authID;
    public String customerIP;
    public String customerID;
    public String transactionDesc;
    public String transactionCurrency;
    public String cardLastSixDigit;
    public String cardType;
    public String transactionStatus;
    public String creditWalletID;
    public String creditWalletCurrency;
    public double creditAmount;

    public CardDeposit() {
    }

    public CardDeposit(String ID, String userID, Timestamp timestamp, String authID, String customerIP, String customerID, String transactionDesc, String transactionCurrency, String cardLastSixDigit, String cardType, String transactionStatus, String creditWalletID, String creditWalletCurrency, double creditAmount) {
        this.ID = ID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.transactionDesc = transactionDesc;
        this.transactionCurrency = transactionCurrency;
        this.cardLastSixDigit = cardLastSixDigit;
        this.cardType = cardType;
        this.transactionStatus = transactionStatus;
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
    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    @Exclude
    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    @Exclude
    public String getCardLastSixDigit() {
        return cardLastSixDigit;
    }

    @Exclude
    public void setCardLastSixDigit(String cardLastSixDigit) {
        this.cardLastSixDigit = cardLastSixDigit;
    }

    @Exclude
    public String getCardType() {
        return cardType;
    }

    @Exclude
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    @Exclude
    public String getTransactionStatus() {
        return transactionStatus;
    }

    @Exclude
    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
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
