package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.mickleentityltdnigeria.mickle_pay.util.IDGenerator;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class Merchant implements Serializable {

    public String merchantID;
    public Timestamp timestamp;
    public String merchantType;
    public String apiKEY;
    public String merchantName;
    public String merchantAddress;
    public String merchantCity;
    public String merchantZipCode;
    public String merchantState;
    public String merchantCountry;
    public String merchantDefaultCurrency;
    public Map<String, PhoneLine> merchantPhoneLines;
    public boolean isActive;
    public String activatedBy;
    public Timestamp timeActivated;

    public Merchant() {
    }

    public Merchant(String merchantID, Timestamp timestamp, String merchantType, String merchantName, String merchantAddress, String merchantCity, String merchantZipCode, String merchantState, String merchantCountry, String merchantDefaultCurrency, Map<String, PhoneLine> merchantPhoneLines, boolean isActive, String activatedBy, Timestamp timeActivated) {
        this.merchantID = merchantID;
        this.timestamp = timestamp;
        this.merchantType = merchantType;
        this.merchantName = merchantName;
        this.merchantAddress = merchantAddress;
        this.merchantCity = merchantCity;
        this.merchantZipCode = merchantZipCode;
        this.merchantState = merchantState;
        this.merchantCountry = merchantCountry;
        this.merchantDefaultCurrency = merchantDefaultCurrency;
        this.merchantPhoneLines = merchantPhoneLines;
        this.isActive = isActive;
        this.activatedBy = activatedBy;
        this.timeActivated = timeActivated;
        this.setApiKEY();
    }

    @Exclude
    public void setApiKEY(String apiKEY) {
        if(this.merchantType.equals(Types.MerchantType.SUB_MERCHANT())){
            this.apiKEY = IDGenerator.getInstance().getUUID();
        }
    }

    @Exclude
    public void setApiKEY() {
        if(this.merchantType.equals(Types.MerchantType.SUB_MERCHANT())){
            this.apiKEY = IDGenerator.getInstance().getUUID();
        }
    }

    @Exclude
    public String getApiKEY() {
        return apiKEY;
    }

    @Exclude
    public String getMerchantID() {
        return merchantID;
    }

    @Exclude
    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
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
    public String getMerchantType() {
        return merchantType;
    }

    @Exclude
    public void setMerchantType(String merchantType) {
        this.merchantType = merchantType;
    }

    @Exclude
    public String getMerchantName() {
        return merchantName;
    }

    @Exclude
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Exclude
    public String getMerchantAddress() {
        return merchantAddress;
    }

    @Exclude
    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    @Exclude
    public String getMerchantCity() {
        return merchantCity;
    }

    @Exclude
    public void setMerchantCity(String merchantCity) {
        this.merchantCity = merchantCity;
    }

    @Exclude
    public String getMerchantZipCode() {
        return merchantZipCode;
    }

    @Exclude
    public void setMerchantZipCode(String merchantZipCode) {
        this.merchantZipCode = merchantZipCode;
    }

    @Exclude
    public String getMerchantState() {
        return merchantState;
    }

    @Exclude
    public void setMerchantState(String merchantState) {
        this.merchantState = merchantState;
    }

    @Exclude
    public String getMerchantCountry() {
        return merchantCountry;
    }

    @Exclude
    public void setMerchantCountry(String merchantCountry) {
        this.merchantCountry = merchantCountry;
    }

    @Exclude
    public String getMerchantDefaultCurrency() {
        return merchantDefaultCurrency;
    }

    @Exclude
    public void setMerchantDefaultCurrency(String merchantDefaultCurrency) {
        this.merchantDefaultCurrency = merchantDefaultCurrency;
    }

    @Exclude
    public Map<String, PhoneLine> getMerchantPhoneLines() {
        return merchantPhoneLines;
    }

    @Exclude
    public void setMerchantPhoneLines(Map<String, PhoneLine> merchantPhoneLines) {
        this.merchantPhoneLines = merchantPhoneLines;
    }

    @Exclude
    public boolean isActive() {
        return isActive;
    }

    @Exclude
    public void setActive(boolean active) {
        isActive = active;
    }

    @Exclude
    public String getActivatedBy() {
        return activatedBy;
    }

    @Exclude
    public void setActivatedBy(String activatedBy) {
        this.activatedBy = activatedBy;
    }

    @Exclude
    public Timestamp getTimeActivated() {
        return timeActivated;
    }

    @Exclude
    public void setTimeActivated(Timestamp timeActivated) {
        this.timeActivated = timeActivated;
    }
}
