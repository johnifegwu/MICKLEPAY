package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class Exchange  implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String authID;
    public String userIP;
    public String userID;
    public String exchangeDesc;
    public String debitWalletID;
    public String debitWalletCurrency;
    public double debitAmount;
    public double exchangeRate;
    public String creditWalletID;
    public String creditWalletCurrency;
    public double creditAmount;
    public double exchangeGained;

    public Exchange() {
    }

    public Exchange(String ID, Timestamp timestamp, String authID, String userIP, String userID, String exchangeDesc, String debitWalletID, String debitWalletCurrency, double debitAmount, double exchangeRate, String creditWalletID, String creditWalletCurrency, double creditAmount, double exchangeGained) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.userIP = userIP;
        this.userID = userID;
        this.exchangeDesc = exchangeDesc;
        this.debitWalletID = debitWalletID;
        this.debitWalletCurrency = debitWalletCurrency;
        this.debitAmount = debitAmount;
        this.exchangeRate = exchangeRate;
        this.creditWalletID = creditWalletID;
        this.creditWalletCurrency = creditWalletCurrency;
        this.creditAmount = creditAmount;
        this.exchangeGained = exchangeGained;
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
    public String getExchangeDesc() {
        return exchangeDesc;
    }

    @Exclude
    public void setExchangeDesc(String exchangeDesc) {
        this.exchangeDesc = exchangeDesc;
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
    public double getExchangeRate() {
        return exchangeRate;
    }

    @Exclude
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
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

    @Exclude
    public double getExchangeGained() {
        return exchangeGained;
    }

    @Exclude
    public void setExchangeGained(double exchangeGained) {
        this.exchangeGained = exchangeGained;
    }

    @Exclude
    @Override
    public String toString() {
        return "Exchange{" +
                "ID='" + ID + '\'' +
                ", timestamp=" + timestamp +
                ", authID='" + authID + '\'' +
                ", userIP='" + userIP + '\'' +
                ", userID='" + userID + '\'' +
                ", exchangeDesc='" + exchangeDesc + '\'' +
                ", debitWalletID='" + debitWalletID + '\'' +
                ", debitWalletCurrency='" + debitWalletCurrency + '\'' +
                ", debitAmount=" + debitAmount +
                ", exchangeRate=" + exchangeRate +
                ", creditWalletID='" + creditWalletID + '\'' +
                ", creditWalletCurrency='" + creditWalletCurrency + '\'' +
                ", creditAmount=" + creditAmount +
                '}';
    }
}
