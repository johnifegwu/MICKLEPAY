package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class TransactionCharges implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String authID;
    public String customerIP;
    public String customerID;
    public String walletID;
    public double transactionAmount;
    public String chargeType;
    public double chargePercentage;
    public double chargeValue;

    public TransactionCharges() {
    }

    public TransactionCharges(String ID, Timestamp timestamp, String authID, String customerIP, String customerID, String walletID, double transactionAmount, String chargeType, double chargePercentage, double chargeValue) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.walletID = walletID;
        this.transactionAmount = transactionAmount;
        this.chargeType = chargeType;
        this.chargePercentage = chargePercentage;
        this.chargeValue = chargeValue;
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
    public String getWalletID() {
        return walletID;
    }

    @Exclude
    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    @Exclude
    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Exclude
    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Exclude
    public String getChargeType() {
        return chargeType;
    }

    @Exclude
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    @Exclude
    public double getChargePercentage() {
        return chargePercentage;
    }

    @Exclude
    public void setChargePercentage(double chargePercentage) {
        this.chargePercentage = chargePercentage;
    }

    @Exclude
    public double getChargeValue() {
        return chargeValue;
    }

    @Exclude
    public void setChargeValue(double chargeValue) {
        this.chargeValue = chargeValue;
    }
}
