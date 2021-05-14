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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.SendMoneyEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.SendMoney;
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

public class SendMoneyDalc {

    private FirebaseDatabase database;
    private final DatabaseReference sendMoneyDB;
    private final DatabaseReference walletDB;
    private final DatabaseReference transactionChargeDB;

    private final SendMoneyEvents sendMoneyEvents;

    public SendMoneyDalc(SendMoneyEvents sendMoneyEvents) {
        this.sendMoneyEvents = sendMoneyEvents;
        this.database = FirebaseDatabase.getInstance();
        this.sendMoneyDB = database.getReference(DBReferences.SEND_MONEY());
        this.walletDB = database.getReference(DBReferences.WALLET());
        this.transactionChargeDB = database.getReference(DBReferences.TRANSACTION_CHARGES());
    }

    public void sendMoney(Wallet debitWallet, Wallet creditWallet, double transactValue, List<ChargeDefinition> charges, String authID, String customerIP) throws Exception {
        //Check currency code
        if (!debitWallet.getWalletCurrency().equals(creditWallet.getWalletCurrency())) {
            throw new Exception("The Wallet you are sending money to must be of the same Currency.");
        }
        //check ownership
        if(debitWallet.getCustomerID().equals(creditWallet.getCustomerID())){
            throw new Exception("You can not send money to your self.");
        }
        String debitWalletID = debitWallet.getWalletID();
        String creditWalletID = creditWallet.getWalletID();
        double chargeValue = 0;
        for (ChargeDefinition c : charges) {
            if(!c.isDisabled()) {
                chargeValue += (transactValue * c.getChargePercentage());
            }
        }
        //save data
        List<Wallet> result1 = new ArrayList<>();
        List<Wallet> result2 = new ArrayList<>();
        List<TransactionCharges> result3 =  new ArrayList<>();
        List<SendMoney> result4 =  new ArrayList<>();
        double finalChargeValue = chargeValue;

        ValueEventListener onDataChangedListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                assert wallet != null;
                                creditWallet.setWalletBalance(wallet.getWalletBalance() + (transactValue - finalChargeValue));
                                creditWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.INTERNAL_DEPOSIT(), transactValue, Types.INTERNAL_DEPOSIT(), authID, creditWalletID);
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                if (finalChargeValue < 0 || finalChargeValue > 0) {
                                    WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_DEPOSIT(), -finalChargeValue, Types.ChargeType.CHARGE_ON_DEPOSIT(), authID, creditWalletID);
                                    map.put(new Timestamp(new Date().getTime()), wallTran2);
                                }
                                creditWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(debitWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    walletDB.child(creditWallet.getID()).setValue(creditWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //raise event
                                            result2.add(creditWallet);
                                            //save charges
                                            for(ChargeDefinition c: charges){
                                                if(!c.isDisabled()) {
                                                    Timestamp ts = new Timestamp(new Date().getTime());
                                                    TransactionCharges tc = new TransactionCharges("", CurrentUser.userID, ts, authID, customerIP, creditWallet.getCustomerID(), creditWalletID, transactValue, c.getChargeType(), c.getChargePercentage(), -(transactValue * c.chargePercentage));
                                                    String ID = transactionChargeDB.push().getKey();
                                                    tc.setID(ID);
                                                    assert ID != null;
                                                    transactionChargeDB.child(ID).setValue(tc);
                                                    result3.add(tc);
                                                }
                                            }
                                            //save sendMoney
                                            Timestamp ts = new Timestamp(new Date().getTime());
                                            SendMoney sendMoney = new SendMoney("", CurrentUser.userID, ts, authID,customerIP,debitWallet.getCustomerID(),Types.SEND_MONEY(),debitWalletID,debitWallet.getWalletCurrency(),-transactValue,creditWalletID,creditWallet.getWalletCurrency(),(transactValue - finalChargeValue));
                                            String ID = sendMoneyDB.push().getKey();
                                            sendMoney.setID(ID);
                                            assert ID != null;
                                            sendMoneyDB.child(ID).setValue(sendMoney).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    //raise event
                                                    result4.add(sendMoney);
                                                    sendMoneyEvents.onSendMoneySucceeded(result1, result2, result3, result4);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    sendMoneyEvents.onSendMoneyFailed(e);
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            sendMoneyEvents.onSendMoneyFailed(e);
                                        }
                                    });
                                }
                                break;
                            }
                        }
                    }
                }else{
                    sendMoneyEvents.onWalletDataNotFound(new Exception("Wallet data not found"));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sendMoneyEvents.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };

        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                assert wallet != null;
                                debitWallet.setWalletBalance(wallet.getWalletBalance() - transactValue);
                                debitWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), debitWalletID, authID, customerIP, debitWallet.getCustomerID(), Types.SEND_MONEY(), -transactValue, Types.SEND_MONEY(), authID, creditWalletID);
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                debitWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(debitWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    walletDB.child(debitWallet.getID()).setValue(debitWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //compile result
                                            result1.add(debitWallet);
                                            Query query2 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                                                    .orderByChild("walletID")
                                                    .equalTo(creditWalletID);
                                            query2.addListenerForSingleValueEvent(onDataChangedListener2);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            sendMoneyEvents.onSendMoneyFailed(e);
                                        }
                                    });
                                }else{
                                    sendMoneyEvents.onSendMoneyFailed( new Exception("You do not have enough money left in your WALLET to complete this transaction."));
                                }
                                break;
                            }
                        }
                    }
                }else{
                    sendMoneyEvents.onWalletDataNotFound(new Exception("Wallet data not found"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                sendMoneyEvents.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("walletID")
                .equalTo(debitWalletID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }


}
