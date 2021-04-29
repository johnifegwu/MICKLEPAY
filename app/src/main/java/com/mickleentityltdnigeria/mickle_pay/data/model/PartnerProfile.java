package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class PartnerProfile implements Serializable {

    public Timestamp timestamp;
    public String customerName;
    public String customerEmail;
    public String customerAddress;
    public String customerCity;
    public String customerZipCode;
    public String customerState;
    public String customerCountry;
    public Map<String, PhoneLine> customerPhoneLines;

    public PartnerProfile() {
    }

    public PartnerProfile(Timestamp timestamp, String customerName, String customerEmail, String customerAddress, String customerCity, String customerZipCode, String customerState, String customerCountry, Map<String, PhoneLine> customerPhoneLines) {
        this.timestamp = timestamp;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerZipCode = customerZipCode;
        this.customerState = customerState;
        this.customerCountry = customerCountry;
        this.customerPhoneLines = customerPhoneLines;
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
    public String getCustomerName() {
        return customerName;
    }

    @Exclude
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Exclude
    public String getCustomerEmail() {
        return customerEmail;
    }

    @Exclude
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    @Exclude
    public String getCustomerAddress() {
        return customerAddress;
    }

    @Exclude
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Exclude
    public String getCustomerCity() {
        return customerCity;
    }

    @Exclude
    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    @Exclude
    public String getCustomerZipCode() {
        return customerZipCode;
    }

    @Exclude
    public void setCustomerZipCode(String customerZipCode) {
        this.customerZipCode = customerZipCode;
    }

    @Exclude
    public String getCustomerState() {
        return customerState;
    }

    @Exclude
    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    @Exclude
    public String getCustomerCountry() {
        return customerCountry;
    }

    @Exclude
    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    @Exclude
    public Map<String, PhoneLine> getCustomerPhoneLines() {
        return customerPhoneLines;
    }

    @Exclude
    public void setCustomerPhoneLines(Map<String, PhoneLine> customerPhoneLines) {
        this.customerPhoneLines = customerPhoneLines;
    }

}
