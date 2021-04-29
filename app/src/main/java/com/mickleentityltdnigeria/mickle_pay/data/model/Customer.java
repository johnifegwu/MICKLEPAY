package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

@IgnoreExtraProperties
public class Customer implements Serializable {

    public String customerID;
    public Timestamp timestamp;
    public String userID;
    public String customerType;
    public String customerName;
    public String customerEmail;
    public String customerAddress;
    public String customerCity;
    public String customerZipCode;
    public String customerState;
    public String customerCountry;
    public String customerDefaultCurrency;
    public Map<String, PhoneLine> customerPhoneLines;
    public Map<String, PartnerProfile> partnersProfile;
    public boolean isActive;
    public String activatedBy;
    public Timestamp timeActivated;

    public Customer() {
    }

    public Customer(String customerID, Timestamp timestamp, String userID, String customerType, String customerName, String customerEmail, String customerAddress, String customerCity, String customerZipCode, String customerState, String customerCountry, String customerDefaultCurrency, Map<String, PhoneLine> customerPhoneLines, Map<String, PartnerProfile> partnersProfile, boolean isActive, String activatedBy, Timestamp timeActivated) {
        this.customerID = customerID;
        this.timestamp = timestamp;
        this.userID = userID;
        this.customerType = customerType;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerZipCode = customerZipCode;
        this.customerState = customerState;
        this.customerCountry = customerCountry;
        this.customerDefaultCurrency = customerDefaultCurrency;
        this.customerPhoneLines = customerPhoneLines;
        this.partnersProfile = partnersProfile;
        this.isActive = isActive;
        this.activatedBy = activatedBy;
        this.timeActivated = timeActivated;
    }

    @Exclude
    public String getCustomerID() {
        return customerID;
    }

    @Exclude
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
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
    public String getUserID() {
        return userID;
    }

    @Exclude
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Exclude
    public String getCustomerType() {
        return customerType;
    }

    @Exclude
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
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
    public String getCustomerDefaultCurrency() {
        return customerDefaultCurrency;
    }

    @Exclude
    public void setCustomerDefaultCurrency(String customerDefaultCurrency) {
        this.customerDefaultCurrency = customerDefaultCurrency;
    }

    @Exclude
    public Map<String, PhoneLine> getCustomerPhoneLines() {
        return customerPhoneLines;
    }

    @Exclude
    public void setCustomerPhoneLines(Map<String, PhoneLine> customerPhoneLines) {
        this.customerPhoneLines = customerPhoneLines;
    }

    @Exclude
    public Map<String, PartnerProfile> getPartnersProfile() {
        return partnersProfile;
    }

    @Exclude
    public void setPartnersProfile(Map<String, PartnerProfile> partnersProfile) {
        this.partnersProfile = partnersProfile;
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
