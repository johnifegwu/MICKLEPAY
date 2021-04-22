package com.mickleentityltdnigeria.mickle_pay.dalc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.WithdrawalRequestEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.WithdrawalRequest;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

public class WithdrawalRequestDalc {

    private FirebaseDatabase database;
    private final DatabaseReference withdrawalRequestDB;
    private final DatabaseReference walletDB;
    private final DatabaseReference transactionChargeDB;

    private final WithdrawalRequestEvents withdrawalRequestEvents;

    public WithdrawalRequestDalc(WithdrawalRequestEvents withdrawalRequestEvents) {
        this.withdrawalRequestEvents = withdrawalRequestEvents;
        this.database = FirebaseDatabase.getInstance();
        this.withdrawalRequestDB = database.getReference(DBReferences.WITHDRAWAL_REQUEST());
        this.walletDB = database.getReference(DBReferences.WALLET());
        this.transactionChargeDB = database.getReference(DBReferences.TRANSACTION_CHARGES());
    }

    public void addWithdrawalRequest(WithdrawalRequest withdrawalRequest) throws Exception {
        if(!withdrawalRequest.debitWalletCurrency.equals(withdrawalRequest.bankAccountCurrency)){
            throw new Exception("Create a WALLET for " + withdrawalRequest.getBankAccountCurrency() + " and exchange money to it. Withdraw from there.");
        }

    }
}
