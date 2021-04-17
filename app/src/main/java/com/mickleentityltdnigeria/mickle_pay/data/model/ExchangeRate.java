package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class ExchangeRate implements Serializable {

    public Timestamp timestamp;
    public String currency;
    public Map<String, Double> exchangeRate;

    public ExchangeRate() {
    }

    public ExchangeRate(Timestamp timestamp, String currency, Map<String, Double> exchangeRate) {
        this.timestamp = timestamp;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
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
    public String getCurrency() {
        return currency;
    }

    @Exclude
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Exclude
    public Map<String, Double> getExchangeRate() {
        return exchangeRate;
    }

    @Exclude
    public void setExchangeRate(Map<String, Double> exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
