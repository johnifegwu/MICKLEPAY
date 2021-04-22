package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.Country;

import java.util.List;

public interface CountryEvents {

    void onCountriesFetched(List<Country> countryList);

    void onCountriesUpdated(List<Country> countryList);
}
