package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@IgnoreExtraProperties
public class Exchange  implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String authID;
    public String customerIP;
    public String customerID;
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

    public Exchange(String ID, Timestamp timestamp, String authID, String customerIP, String customerID, String exchangeDesc, String debitWalletID, String debitWalletCurrency, double debitAmount, double exchangeRate, String creditWalletID, String creditWalletCurrency, double creditAmount, double exchangeGained) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
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

    @Exclude //returns the exchanged gained
    public static double calcExchangeGained(String debitCurrency, double debitValue, String creditCurrency, Map<String, ExchangeRate> exchangeRates){
        ExchangeRate fromX = exchangeRates.get(debitCurrency);
        assert fromX != null;
        double bidPrice = Objects.requireNonNull(fromX.exchangeRates.get(creditCurrency)).bidPrice;
        double askPrice = Objects.requireNonNull(fromX.exchangeRates.get(creditCurrency)).askPrice;
        return  ((askPrice - bidPrice) * debitValue);
    }

    @Exclude //returns the exchanged value for crediting the credit wallet
    public static double calcExchangeValue(String debitCurrency, double debitValue, String creditCurrency, Map<String, ExchangeRate> exchangeRates){
        ExchangeRate fromX = exchangeRates.get(debitCurrency);
        assert fromX != null;
        return  (Objects.requireNonNull(fromX.exchangeRates.get(creditCurrency)).bidPrice * debitValue);
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
                ", userIP='" + customerIP + '\'' +
                ", userID='" + customerID + '\'' +
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
