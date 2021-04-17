package com.mickleentityltdnigeria.mickle_pay.data.model;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Country {

    public int countryID;
    public String countryNumber;
    public String dialCode;
    public String currencyCode;
    public String countryName;

    public Country() {
    }

    public Country(int countryID, String countryNumber, String dialCode, String currencyCode, String countryName) {
        this.countryID = countryID;
        this.countryNumber = countryNumber;
        this.dialCode = dialCode;
        this.currencyCode = currencyCode;
        this.countryName = countryName;
    }

    @Exclude
    public int getCountryID() {
        return countryID;
    }

    @Exclude
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    @Exclude
    public String getCountryNumber() {
        return countryNumber;
    }

    @Exclude
    public void setCountryNumber(String countryNumber) {
        this.countryNumber = countryNumber;
    }

    @Exclude
    public String getDialCode() {
        return dialCode;
    }

    @Exclude
    public void setDialCode(String dialCode) {
        this.dialCode = dialCode;
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
    public String getCountryName() {
        return countryName;
    }

    @Exclude
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}

