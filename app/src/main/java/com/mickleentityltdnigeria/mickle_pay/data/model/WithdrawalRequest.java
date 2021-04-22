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
    public String bankAccountCurrency;
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

    public WithdrawalRequest(String ID, Timestamp timestamp, String authID, String customerIP, String customerID, boolean isProcessed, Timestamp dateProcessed, String processedBy, String processStatus, String debitWalletID, String debitWalletCurrency, double debitAmount, String bankAccountName, String bankAccountCurrency, String bankAccountNumber_IBAN, String bankAccountAddress, String bankName, String bankAddress, String bankSwiftCode, String intermediaryBankName, String intermediaryBankAddress, String intermediaryBankSwiftCode, String intermediaryBankAccountNumber_IBAN) {
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
        this.bankAccountCurrency = bankAccountCurrency;
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

    @Exclude
    public boolean isProcessed() {
        return isProcessed;
    }

    @Exclude
    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    @Exclude
    public Timestamp getDateProcessed() {
        return dateProcessed;
    }

    @Exclude
    public void setDateProcessed(Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Exclude
    public String getProcessedBy() {
        return processedBy;
    }

    @Exclude
    public void setProcessedBy(String processedBy) {
        this.processedBy = processedBy;
    }

    @Exclude
    public String getProcessStatus() {
        return processStatus;
    }

    @Exclude
    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Exclude
    public String getDebitWalletID() {
        return debitWalletID;
    }

    @Exclude
    public void setDebitWalletID(String debitWalletID) {
        this.debitWalletID = debitWalletID;
    }

    @Exclude
    public String getDebitWalletCurrency() {
        return debitWalletCurrency;
    }

    @Exclude
    public void setDebitWalletCurrency(String debitWalletCurrency) {
        this.debitWalletCurrency = debitWalletCurrency;
    }

    @Exclude
    public double getDebitAmount() {
        return debitAmount;
    }

    @Exclude
    public void setDebitAmount(double debitAmount) {
        this.debitAmount = debitAmount;
    }

    @Exclude
    public String getBankAccountName() {
        return bankAccountName;
    }

    @Exclude
    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    @Exclude
    public String getBankAccountCurrency() {
        return bankAccountCurrency;
    }

    @Exclude
    public void setBankAccountCurrency(String bankAccountCurrency) {
        this.bankAccountCurrency = bankAccountCurrency;
    }

    @Exclude
    public String getBankAccountNumber_IBAN() {
        return bankAccountNumber_IBAN;
    }

    @Exclude
    public void setBankAccountNumber_IBAN(String bankAccountNumber_IBAN) {
        this.bankAccountNumber_IBAN = bankAccountNumber_IBAN;
    }

    @Exclude
    public String getBankAccountAddress() {
        return bankAccountAddress;
    }

    @Exclude
    public void setBankAccountAddress(String bankAccountAddress) {
        this.bankAccountAddress = bankAccountAddress;
    }

    @Exclude
    public String getBankName() {
        return bankName;
    }

    @Exclude
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Exclude
    public String getBankAddress() {
        return bankAddress;
    }

    @Exclude
    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    @Exclude
    public String getBankSwiftCode() {
        return bankSwiftCode;
    }

    @Exclude
    public void setBankSwiftCode(String bankSwiftCode) {
        this.bankSwiftCode = bankSwiftCode;
    }

    @Exclude
    public String getIntermediaryBankName() {
        return intermediaryBankName;
    }

    @Exclude
    public void setIntermediaryBankName(String intermediaryBankName) {
        this.intermediaryBankName = intermediaryBankName;
    }

    @Exclude
    public String getIntermediaryBankAddress() {
        return intermediaryBankAddress;
    }

    @Exclude
    public void setIntermediaryBankAddress(String intermediaryBankAddress) {
        this.intermediaryBankAddress = intermediaryBankAddress;
    }

    @Exclude
    public String getIntermediaryBankSwiftCode() {
        return intermediaryBankSwiftCode;
    }

    @Exclude
    public void setIntermediaryBankSwiftCode(String intermediaryBankSwiftCode) {
        this.intermediaryBankSwiftCode = intermediaryBankSwiftCode;
    }

    @Exclude
    public String getIntermediaryBankAccountNumber_IBAN() {
        return intermediaryBankAccountNumber_IBAN;
    }

    @Exclude
    public void setIntermediaryBankAccountNumber_IBAN(String intermediaryBankAccountNumber_IBAN) {
        this.intermediaryBankAccountNumber_IBAN = intermediaryBankAccountNumber_IBAN;
    }
}
