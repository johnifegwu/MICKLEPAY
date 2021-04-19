package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;

@IgnoreExtraProperties
public class WithdrawalRequest implements Serializable {

    public String ID;
    public Timestamp timestamp;
    public String authID;
    public String customerIP;
    public String customerID;
    public boolean isProcessed;
    public Timestamp dateProcessed;
    public String processedBy;
    public String processStatus;
    public String debitWalletID;
    public String debitWalletCurrency;
    public double debitAmount;
    public String bankAccountName;
    public String bankAccountNumber_IBAN;
    public String bankAccountAddress;
    public String bankName;
    public String bankAddress;
    public String bankSwiftCode;
    public String intermediaryBankName;
    public String intermediaryBankAddress;
    public String intermediaryBankSwiftCode;
    public String intermediaryBankAccountNumber_IBAN;

    public WithdrawalRequest() {
    }

    public WithdrawalRequest(String ID, Timestamp timestamp, String authID, String customerIP, String customerID, boolean isProcessed, Timestamp dateProcessed, String processedBy, String processStatus, String debitWalletID, String debitWalletCurrency, double debitAmount, String bankAccountName, String bankAccountNumber_IBAN, String bankAccountAddress, String bankName, String bankAddress, String bankSwiftCode, String intermediaryBankName, String intermediaryBankAddress, String intermediaryBankSwiftCode, String intermediaryBankAccountNumber_IBAN) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.authID = authID;
        this.customerIP = customerIP;
        this.customerID = customerID;
        this.isProcessed = isProcessed;
        this.dateProcessed = dateProcessed;
        this.processedBy = processedBy;
        this.processStatus = processStatus;
        this.debitWalletID = debitWalletID;
        this.debitWalletCurrency = debitWalletCurrency;
        this.debitAmount = debitAmount;
        this.bankAccountName = bankAccountName;
        this.bankAccountNumber_IBAN = bankAccountNumber_IBAN;
        this.bankAccountAddress = bankAccountAddress;
        this.bankName = bankName;
        this.bankAddress = bankAddress;
        this.bankSwiftCode = bankSwiftCode;
        this.intermediaryBankName = intermediaryBankName;
        this.intermediaryBankAddress = intermediaryBankAddress;
        this.intermediaryBankSwiftCode = intermediaryBankSwiftCode;
        this.intermediaryBankAccountNumber_IBAN = intermediaryBankAccountNumber_IBAN;
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
    public String getAuthID() {
        return authID;
    }

    @Exclude
    public void setAuthID(String authID) {
        this.authID = authID;
    }

    @Exclude
    public String getCustomerIP() {
        return customerIP;
    }

    @Exclude
    public void setCustomerIP(String customerIP) {
        this.customerIP = customerIP;
    }

    @Exclude
    public String getCustomerID() {
        return customerID;
    }

    @Exclude
    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    public Timestamp getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public String getDebitWalletID() {
        return debitWalletID;
    }

    public void setDebitWalletID(String debitWalletID) {
        this.debitWalletID = debitWalletID;
    }

    public String getDebitWalletCurrency() {
        return debitWalletCurrency;
    }

    public void setDebitWalletCurrency(String debitWalletCurrency) {
        this.debitWalletCurrency = debitWalletCurrency;
    }

    public double getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getBankAccountNumber_IBAN() {
        return bankAccountNumber_IBAN;
    }

    public void setBankAccountNumber_IBAN(String bankAccountNumber_IBAN) {
        this.bankAccountNumber_IBAN = bankAccountNumber_IBAN;
    }

    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankSwiftCode() {
        return bankSwiftCode;
    }

    public void setBankSwiftCode(String bankSwiftCode) {
        this.bankSwiftCode = bankSwiftCode;
    }

    public String getIntermediaryBankName() {
        return intermediaryBankName;
    }

    public void setIntermediaryBankName(String intermediaryBankName) {
        this.intermediaryBankName = intermediaryBankName;
    }

    public String getIntermediaryBankAddress() {
        return intermediaryBankAddress;
    }

    public void setIntermediaryBankAddress(String intermediaryBankAddress) {
        this.intermediaryBankAddress = intermediaryBankAddress;
    }

    public String getIntermediaryBankSwiftCode() {
        return intermediaryBankSwiftCode;
    }

    public void setIntermediaryBankSwiftCode(String intermediaryBankSwiftCode) {
        this.intermediaryBankSwiftCode = intermediaryBankSwiftCode;
    }

    public String getIntermediaryBankAccountNumber_IBAN() {
        return intermediaryBankAccountNumber_IBAN;
    }

    public void setIntermediaryBankAccountNumber_IBAN(String intermediaryBankAccountNumber_IBAN) {
        this.intermediaryBankAccountNumber_IBAN = intermediaryBankAccountNumber_IBAN;
    }
}
