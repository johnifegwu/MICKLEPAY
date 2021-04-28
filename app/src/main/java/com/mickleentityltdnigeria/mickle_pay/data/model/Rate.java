package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class Rate implements Serializable {
    public String currencyCode;
    public double bidPrice;
    public double askPrice;

    public Rate() {
    }

    public Rate(String currencyCode, double bidPrice, double askPrice) {
        this.currencyCode = currencyCode;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
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
    public double getBidPrice() {
        return bidPrice;
    }

    @Exclude
    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    @Exclude
    public double getAskPrice() {
        return askPrice;
    }

    @Exclude
    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }
}
