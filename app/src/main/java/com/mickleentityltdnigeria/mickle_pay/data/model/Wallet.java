package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class Wallet implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String customerID;
    public String walletID;
    public String walletCurrency;
    public double walletBalance;
    public Map<Timestamp, WalletTransactions> walletTransactions;

    public Wallet() {
    }

    public Wallet(String ID, Timestamp timestamp, String walletID, String customerID, String walletCurrency, double walletBalance, Map<Timestamp, WalletTransactions> walletTransactions) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.walletID = walletID;
        this.customerID = customerID;
        this.walletCurrency = walletCurrency;
        this.walletBalance = walletBalance;
        this.walletTransactions = walletTransactions;
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
    public String getWalletID() {
        return walletID;
    }

    @Exclude
    public void setWalletID(String walletID) {
        this.walletID = walletID;
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
    public String getWalletCurrency() {
        return walletCurrency;
    }

    @Exclude
    public void setWalletCurrency(String walletCurrency) {
        this.walletCurrency = walletCurrency;
    }

    @Exclude
    public double getWalletBalance() {
        return walletBalance;
    }

    @Exclude
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }

    @Exclude
    public Map<Timestamp, WalletTransactions> getWalletTransactions() {
        return walletTransactions;
    }

    @Exclude
    public void setWalletTransactions(Map<Timestamp, WalletTransactions> walletTransactions) {
        this.walletTransactions = walletTransactions;
    }
}
