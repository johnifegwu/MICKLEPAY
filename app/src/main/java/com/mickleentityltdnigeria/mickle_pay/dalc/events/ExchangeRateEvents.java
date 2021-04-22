package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;

import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;

import java.util.Map;

public interface ExchangeRateEvents {

    void onExchangeRatesFetched(Map<String, ExchangeRate> exchangeRates);

    void  onExchangeRatesUpdated(Map<String, ExchangeRate> exchangeRates);

    void onExchangeRatesNotFound(@NonNull Exception e);

}
