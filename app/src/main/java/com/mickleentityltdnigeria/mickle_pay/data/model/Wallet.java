package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

@IgnoreExtraProperties
public class Wallet implements Serializable {

    public String ID;
    public String userID;
    public Timestamp timestamp;
    public String customerID;
    public String walletID;
    public String walletCurrency;
    public Map<Timestamp, WalletTransactions> walletTransactions;

    public Wallet() {
    }

    public Wallet(String ID, String userID, Timestamp timestamp, String walletID, String customerID, String walletCurrency, Map<Timestamp, WalletTransactions> walletTransactions) {
        this.ID = ID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.walletID = walletID;
        this.customerID = customerID;
        this.walletCurrency = walletCurrency;
        this.walletTransactions = walletTransactions;
    }

    @Exclude
    public void getBalance(double AvailableBal, double UnClearedBal){
        for(WalletTransactions tr: this.walletTransactions.values()){
            if(tr.getValueDate().getTime() <= new Date().getTime()){
                AvailableBal += tr.getCreditAmount();
                AvailableBal -= tr.getDebitAmount();
            }
            UnClearedBal += tr.getCreditAmount();
            UnClearedBal -= tr.getDebitAmount();
        }
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
    public Map<Timestamp, WalletTransactions> getWalletTransactions() {
        return walletTransactions;
    }

    @Exclude
    public void setWalletTransactions(Map<Timestamp, WalletTransactions> walletTransactions) {
        this.walletTransactions = walletTransactions;
    }
}
