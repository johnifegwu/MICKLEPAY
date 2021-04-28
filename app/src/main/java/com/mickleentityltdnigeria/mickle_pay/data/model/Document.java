package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class Document implements Serializable {

    public String documentID;
    public String OwnerID;
    public Timestamp timestamp;
    public String documentType;
    public String documentImg;
    public boolean canExpire;
    public Timestamp expiresOn;
    public String approvalStatus;
    public String approvedBy;
    public Timestamp timeApproved;

    public Document() {
    }

    public Document(String documentID, String ownerID, Timestamp timestamp, String documentType, String documentImg, boolean canExpire, Timestamp expiresOn) {
        this.documentID = documentID;
        OwnerID = ownerID;
        this.timestamp = timestamp;
        this.documentType = documentType;
        this.documentImg = documentImg;
        this.canExpire = canExpire;
        this.expiresOn = expiresOn;
        this.approvalStatus = Types.ApprovalStatus.PENDING();
        this.approvedBy = "";
        this.timeApproved = timestamp;
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }

    @Exclude
    public void setDocumentID(String documentID) {
        this.documentID = documentID;
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
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Exclude
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Exclude
    public String getDocumentType() {
        return documentType;
    }

    @Exclude
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    @Exclude
    public String getDocumentImg() {
        return documentImg;
    }

    @Exclude
    public void setDocumentImg(String documentImg) {
        this.documentImg = documentImg;
    }

    @Exclude
    public boolean isCanExpire() {
        return canExpire;
    }

    @Exclude
    public void setCanExpire(boolean canExpire) {
        this.canExpire = canExpire;
    }

    @Exclude
    public Timestamp getExpiresOn() {
        return expiresOn;
    }

    @Exclude
    public void setExpiresOn(Timestamp expiresOn) {
        this.expiresOn = expiresOn;
    }

    @Exclude
    public String getApprovalStatus() {
        return approvalStatus;
    }

    @Exclude
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    @Exclude
    public String getApprovedBy() {
        return approvedBy;
    }

    @Exclude
    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    @Exclude
    public Timestamp getTimeApproved() {
        return timeApproved;
    }

    @Exclude
    public void setTimeApproved(Timestamp timeApproved) {
        this.timeApproved = timeApproved;
    }

}
