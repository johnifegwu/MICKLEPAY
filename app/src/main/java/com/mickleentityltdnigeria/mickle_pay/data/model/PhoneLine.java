package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

@IgnoreExtraProperties
public class PhoneLine implements Serializable {

    public String OwnerID;
    public String phoneType;
    public String dialCode;
    public String phoneNumber;

    public PhoneLine() {
    }

    public PhoneLine(String ownerID, String phoneType, String dialCode, String phoneNumber) {
        OwnerID = ownerID;
        this.phoneType = phoneType;
        this.dialCode = dialCode;
        this.phoneNumber = phoneNumber;
    }

    @Exclude
    public String getOwnerID() {
        return OwnerID;
    }

    @Exclude
    public void setOwnerID(String ownerID) {
        OwnerID = ownerID;
    }

    @Exclude
    public String getPhoneType() {
        return phoneType;
    }

    @Exclude
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
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
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Exclude
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
