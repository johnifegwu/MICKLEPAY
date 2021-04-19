package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Rate implements Serializable {
    public String currencyCode;
    public double exchangeRate;

    public Rate() {
    }

    public Rate(String currencyCode, double exchangeRate) {
        this.currencyCode = currencyCode;
        this.exchangeRate = exchangeRate;
    }

    @Exclude
    public String getCurrencyCode() {
        return currencyCode;
    }

    @Exclude
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    @Exclude
    public double getExchangeRate() {
        return exchangeRate;
    }

    @Exclude
    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
