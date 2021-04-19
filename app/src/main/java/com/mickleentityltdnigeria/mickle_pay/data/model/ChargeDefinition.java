package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class ChargeDefinition  implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String createdBy;
    public boolean isDisabled;
    public String chargeType;
    public double chargePercentage = 0.01;

    public ChargeDefinition() {
    }

    public ChargeDefinition(String ID, Timestamp timestamp, String createdBy, boolean isDisabled, String chargeType, double chargePercentage) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.createdBy = createdBy;
        this.isDisabled = isDisabled;
        this.chargeType = chargeType;
        this.chargePercentage = chargePercentage;
    }

    @Exclude
    public String getID() {
        return ID;
    }

    @Exclude
    public void setID(String ID) {
        this.ID = ID;
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
    public boolean isDisabled() {
        return isDisabled;
    }

    @Exclude
    public void setDisabled(boolean disabled) {
        isDisabled = disabled;
    }

    @Exclude
    public String getChargeType() {
        return chargeType;
    }

    @Exclude
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    @Exclude
    public double getChargePercentage() {
        return chargePercentage;
    }

    @Exclude
    public void setChargePercentage(double chargePercentage) {
        this.chargePercentage = chargePercentage;
    }

}
