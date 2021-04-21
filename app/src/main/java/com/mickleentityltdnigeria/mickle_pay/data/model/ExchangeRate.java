package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class ExchangeRate implements Serializable {

    public Timestamp timestamp;
    public String createdBy;
    public String currencyCode;
    public Map<String, Rate> exchangeRates;

    public ExchangeRate() {
    }

    public ExchangeRate(Timestamp timestamp, String createdBy, String currencyCode, Map<String, Rate> exchangeRates) {
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.currencyCode = currencyCode;
        this.exchangeRates = exchangeRates;
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
    public String getCreatedBy() {
        return createdBy;
    }

    @Exclude
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
    public Map<String, Rate> getExchangeRates() {
        return exchangeRates;
    }

    @Exclude
    public void setExchangeRates(Map<String, Rate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

}
