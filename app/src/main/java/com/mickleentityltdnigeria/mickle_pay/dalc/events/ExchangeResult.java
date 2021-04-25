package com.mickleentityltdnigeria.mickle_pay.dalc.events;

public interface ExchangeResult{
    void onResultArrived(double bidPrice, String baseCurrency, String quoteCurrency);
    void onError(Exception e);
}