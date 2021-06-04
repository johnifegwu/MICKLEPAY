package com.mickleentityltdnigeria.mickle_pay.dalc;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.BankDepositEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.BankDeposit;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
import com.mickleentityltdnigeria.mickle_pay.data.model.TransactionCharges;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;
import com.mickleentityltdnigeria.mickle_pay.data.model.WalletTransactions;
import com.mickleentityltdnigeria.mickle_pay.util.CurrentUser;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankDepositDalc {

    private FirebaseDatabase database;
    private final DatabaseReference bankDepositDB;
    private final DatabaseReference walletDB;
    private final DatabaseReference transactionChargeDB;

    private final BankDepositEvents bankDepositEvents;

    public BankDepositDalc(BankDepositEvents bankDepositEvents) {
        this.bankDepositEvents = bankDepositEvents;
        this.database = FirebaseDatabase.getInstance();
        this.bankDepositDB = database.getReference(DBReferences.BANK_DEPOSITS());
        this.walletDB = database.getReference(DBReferences.WALLET());
        this.transactionChargeDB = database.getReference(DBReferences.TRANSACTION_CHARGES());
    }

    public void addBankDeposit(BankDeposit bankDeposit) throws Exception {
        if(!bankDeposit.creditWalletCurrency.equals(bankDeposit.bankAccountCurrency)){
            throw new Exception("Create a WALLET for " + bankDeposit.getBankAccountCurrency() + " and deposit to it. You can then use Currency Exchange to exchange to this particular WALLET");
        }
        String ID = bankDepositDB.push().getKey();
        bankDeposit.setID(ID);
        bankDepositDB.child(ID).setValue(bankDeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                bankDepositEvents.onBankDepositAdded(bankDeposit);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                bankDepositEvents.onBankDepositsUpdateFailed(e);
            }
        });
    }

    public void processBankDeposit(BankDeposit bankDeposit, List<ChargeDefinition> charges){
        if(CurrentUser.userType.equals(Types.userType.ADMIN()) && bankDeposit.getProcessStatus().equals(Types.TransactStatus.APPROVED())){
            //credit customer wallet
            String creditWalletID = bankDeposit.getCreditWalletID();
            double chargeValue = 0;
            for (ChargeDefinition c : charges) {
                if(!c.isDisabled()) {
                    chargeValue += (bankDeposit.getCreditAmount() * c.getChargePercentage());
                }
            }
            double finalChargeValue = chargeValue;
            ValueEventListener onDataChangedListener2 = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        if (snapshot.exists()) {
                            //
                            if (snapshot.hasChildren()) {
                                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                    Wallet creditWallet = userSnapshot.getValue(Wallet.class);
                                    assert creditWallet != null;
                                    //creditWallet.setWalletBalance(creditWallet.getWalletBalance() + (bankDeposit.getCreditAmount() - finalChargeValue));
                                    Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                    WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, bankDeposit.getAuthID(), bankDeposit.getCustomerIP(), creditWallet.getCustomerID(), Types.CARD_DEPOSIT(), 0, bankDeposit.getCreditAmount(), new Date(), Types.BANK_DEPOSIT(), bankDeposit.getAuthID(), creditWalletID);
                                    map.put(new Timestamp(new Date().getTime()), wallTran);
                                    if (finalChargeValue < 0 || finalChargeValue > 0) {
                                        WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, bankDeposit.getAuthID(), bankDeposit.getCustomerIP(), creditWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_DEPOSIT(), finalChargeValue, 0, new Date(), Types.ChargeType.CHARGE_ON_DEPOSIT(), bankDeposit.getAuthID(), creditWalletID);
                                        map.put(new Timestamp(new Date().getTime()), wallTran2);
                                    }
                                    creditWallet.setWalletTransactions(map);
                                    //validate creditAmount.
                                    if(bankDeposit.getCreditAmount() > 0) {
                                        //save wallet to the system.
                                        walletDB.child(creditWallet.getID()).setValue(creditWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                //save charges
                                                for (ChargeDefinition c : charges) {
                                                    if(!c.isDisabled()){
                                                        Timestamp ts = new Timestamp(new Date().getTime());
                                                        TransactionCharges tc = new TransactionCharges("",CurrentUser.userID, ts, bankDeposit.getAuthID(), bankDeposit.getCustomerIP(), creditWallet.getCustomerID(), creditWalletID, bankDeposit.getCreditAmount(), c.getChargeType(), c.getChargePercentage(), -(bankDeposit.getCreditAmount() * c.chargePercentage));
                                                        String ID = transactionChargeDB.push().getKey();
                                                        tc.setID(ID);
                                                        assert ID != null;
                                                        transactionChargeDB.child(ID).setValue(tc);
                                                    }
                                                }
                                                //save Bank Deposit
                                                bankDeposit.setProcessed(true);
                                                bankDepositDB.child(bankDeposit.getID()).setValue(bankDeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        //raise event
                                                        bankDepositEvents.onBankDepositProcessedSuccessfully(bankDeposit);
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        bankDepositEvents.onBankDepositsUpdateFailed(e);
                                                    }
                                                });
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                bankDepositEvents.onBankDepositsUpdateFailed(e);
                                            }
                                        });
                                    }
                                    break;
                                }
                            }
                        }
                    }else {
                        bankDepositEvents.onWalletDataNotFound(new Exception("Wallet data not found"));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    bankDepositEvents.onWalletDataNotFound(new Exception(error.getMessage()));
                }
            };
            Query query2 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                    .orderByChild("walletID")
                    .equalTo(creditWalletID);
            query2.addListenerForSingleValueEvent(onDataChangedListener2);
            //
        }else{
            //save bank deposit to the system
            bankDeposit.isProcessed = true;
            bankDepositDB.child(bankDeposit.getID()).setValue(bankDeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    bankDepositEvents.onBankDepositProcessedSuccessfully(bankDeposit);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    bankDepositEvents.onBankDepositsUpdateFailed(e);
                }
            });
        }

    }

    public void getUnProcessedBankDeposits(){
        List<BankDeposit> result = new ArrayList<>();
        ValueEventListener onDataChangedListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                BankDeposit bankDeposit = userSnapshot.getValue(BankDeposit.class);
                                assert bankDeposit != null;
                                result.add(bankDeposit);
                            }
                            bankDepositEvents.onBankDepositsFetched(result);
                        }
                    }
                } else {
                    bankDepositEvents.onBankDepositsNotFound(new Exception("No Bank Deposit available."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bankDepositEvents.onBankDepositsNotFound(new Exception(error.getMessage()));
            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference(DBReferences.BANK_DEPOSITS())
                .orderByChild("isProcessed")
                .equalTo(false);
        query1.addListenerForSingleValueEvent(onDataChangedListener1);

    }

    public void getProcessedBankDeposits(){
        List<BankDeposit> result = new ArrayList<>();
        ValueEventListener onDataChangedListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                BankDeposit bankDeposit = userSnapshot.getValue(BankDeposit.class);
                                assert bankDeposit != null;
                                result.add(bankDeposit);
                            }
                            bankDepositEvents.onBankDepositsFetched(result);
                        }
                    }
                } else {
                    bankDepositEvents.onBankDepositsNotFound(new Exception("No Bank Deposit available."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bankDepositEvents.onBankDepositsNotFound(new Exception(error.getMessage()));
            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference(DBReferences.BANK_DEPOSITS())
                .orderByChild("isProcessed")
                .equalTo(true);
        query1.addListenerForSingleValueEvent(onDataChangedListener1);

    }

    public void getBankDepositByWalletID(String creditWalletID){
        List<BankDeposit> result = new ArrayList<>();
        ValueEventListener onDataChangedListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                BankDeposit bankDeposit = userSnapshot.getValue(BankDeposit.class);
                                assert bankDeposit != null;
                                result.add(bankDeposit);
                            }
                            bankDepositEvents.onBankDepositsFetched(result);
                        }
                    }
                } else {
                    bankDepositEvents.onBankDepositsNotFound(new Exception("No Bank Deposit available."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                bankDepositEvents.onBankDepositsNotFound(new Exception(error.getMessage()));
            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference(DBReferences.BANK_DEPOSITS())
                .orderByChild("creditWalletID")
                .equalTo(creditWalletID);
        query1.addListenerForSingleValueEvent(onDataChangedListener1);

    }


}
