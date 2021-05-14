package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class MerchantPayment implements Serializable {

    public String ID;
    public String userID;
    public Timestamp timestamp;
    public String authID;
    public String merchantIP;
    public String merchantID;
    public String transactionDesc;
    public String customerWalletID;
    public String customerWalletCurrency;
    public double transactionTotal;
    public String mainMerchantWalletID;
    public String mainMerchantWalletCurrency;
    public double mainMerchantCreditAmount;
    public String subMerchantWalletID;
    public String subMerchantWalletCurrency;
    public double subMerchantCreditAmount;

    public MerchantPayment() {
    }

    public MerchantPayment(String ID, String userID, Timestamp timestamp, String authID, String merchantIP, String merchantID, String transactionDesc, String customerWalletID, String customerWalletCurrency, double transactionTotal, String mainMerchantWalletID, String mainMerchantWalletCurrency, double mainMerchantCreditAmount, String subMerchantWalletID, String subMerchantWalletCurrency, double subMerchantCreditAmount) {
        this.ID = ID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.merchantIP = merchantIP;
        this.merchantID = merchantID;
        this.transactionDesc = transactionDesc;
        this.customerWalletID = customerWalletID;
        this.customerWalletCurrency = customerWalletCurrency;
        this.transactionTotal = transactionTotal;
        this.mainMerchantWalletID = mainMerchantWalletID;
        this.mainMerchantWalletCurrency = mainMerchantWalletCurrency;
        this.mainMerchantCreditAmount = mainMerchantCreditAmount;
        this.subMerchantWalletID = subMerchantWalletID;
        this.subMerchantWalletCurrency = subMerchantWalletCurrency;
        this.subMerchantCreditAmount = subMerchantCreditAmount;
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
    public String getMerchantIP() {
        return merchantIP;
    }

    @Exclude
    public void setMerchantIP(String merchantIP) {
        this.merchantIP = merchantIP;
    }

    @Exclude
    public String getMerchantID() {
        return merchantID;
    }

    @Exclude
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
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
    public String getCustomerWalletID() {
        return customerWalletID;
    }

    @Exclude
    public void setCustomerWalletID(String customerWalletID) {
        this.customerWalletID = customerWalletID;
    }

    @Exclude
    public String getCustomerWalletCurrency() {
        return customerWalletCurrency;
    }

    @Exclude
    public void setCustomerWalletCurrency(String customerWalletCurrency) {
        this.customerWalletCurrency = customerWalletCurrency;
    }

    @Exclude
    public double getTransactionTotal() {
        return transactionTotal;
    }

    @Exclude
    public void setTransactionTotal(double transactionTotal) {
        this.transactionTotal = transactionTotal;
    }

    @Exclude
    public String getMainMerchantWalletID() {
        return mainMerchantWalletID;
    }

    @Exclude
    public void setMainMerchantWalletID(String mainMerchantWalletID) {
        this.mainMerchantWalletID = mainMerchantWalletID;
    }

    @Exclude
    public String getMainMerchantWalletCurrency() {
        return mainMerchantWalletCurrency;
    }

    @Exclude
    public void setMainMerchantWalletCurrency(String mainMerchantWalletCurrency) {
        this.mainMerchantWalletCurrency = mainMerchantWalletCurrency;
    }

    @Exclude
    public double getMainMerchantCreditAmount() {
        return mainMerchantCreditAmount;
    }

    @Exclude
    public void setMainMerchantCreditAmount(double mainMerchantCreditAmount) {
        this.mainMerchantCreditAmount = mainMerchantCreditAmount;
    }

    @Exclude
    public String getSubMerchantWalletID() {
        return subMerchantWalletID;
    }

    @Exclude
    public void setSubMerchantWalletID(String subMerchantWalletID) {
        this.subMerchantWalletID = subMerchantWalletID;
    }

    @Exclude
    public String getSubMerchantWalletCurrency() {
        return subMerchantWalletCurrency;
    }

    @Exclude
    public void setSubMerchantWalletCurrency(String subMerchantWalletCurrency) {
        this.subMerchantWalletCurrency = subMerchantWalletCurrency;
    }

    @Exclude
    public double getSubMerchantCreditAmount() {
        return subMerchantCreditAmount;
    }

    @Exclude
    public void setSubMerchantCreditAmount(double subMerchantCreditAmount) {
        this.subMerchantCreditAmount = subMerchantCreditAmount;
    }
}
