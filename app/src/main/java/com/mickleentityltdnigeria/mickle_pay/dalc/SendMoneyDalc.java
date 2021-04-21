package com.mickleentityltdnigeria.mickle_pay.dalc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
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
import com.mickleentityltdnigeria.mickle_pay.util.DBRefrences;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendMoneyDalc {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference sendMoneyDB = database.getReference(DBRefrences.SEND_MONEY());
    DatabaseReference walletDB = database.getReference(DBRefrences.WALLET());
    DatabaseReference transactionChargeDB = database.getReference(DBRefrences.TRANSACTION_CHARGES());

    private SendMoneyEvents sendMoneyEvents;

    public SendMoneyDalc(SendMoneyEvents sendMoneyEvents) {
        this.sendMoneyEvents = sendMoneyEvents;
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
            chargeValue += (transactValue * c.chargePercentage);
        }
        //perform debit transactions
        debitWallet.setWalletBalance(debitWallet.getWalletBalance() - transactValue);
        Map<Timestamp, WalletTransactions> map = new HashMap<>();
        WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), debitWalletID, authID, customerIP, debitWallet.getCustomerID(), Types.SEND_MONEY(), -transactValue, Types.SEND_MONEY(), authID, creditWalletID);
        map.put(new Timestamp(new Date().getTime()), wallTran);
        debitWallet.setWalletTransactions(map);
        //
        //perform credit transactions
        creditWallet.setWalletBalance(creditWallet.getWalletBalance() + (transactValue - chargeValue));
        Map<Timestamp, WalletTransactions> map2 = new HashMap<>();
        WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.INTERNAL_DEPOSIT(), transactValue, Types.INTERNAL_DEPOSIT(), authID, creditWalletID);
        map2.put(new Timestamp(new Date().getTime()), wallTran2);
        if (chargeValue < 0 || chargeValue > 0) {
            WalletTransactions wallTran3 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_DEPOSIT(), -chargeValue, Types.ChargeType.CHARGE_ON_DEPOSIT(), authID, creditWalletID);
            map2.put(new Timestamp(new Date().getTime()), wallTran3);
        }
        creditWallet.setWalletTransactions(map2);
        //
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
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBRefrences.WALLET())
                .orderByChild("walletID")
                .equalTo(debitWalletID);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //
                Query query = FirebaseDatabase.getInstance().getReference(DBRefrences.WALLET())
                        .orderByChild("walletID")
                        .equalTo(debitWalletID);
                query.addListenerForSingleValueEvent(onDataChangedListener);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //
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
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        };
        //
        Query query2 = FirebaseDatabase.getInstance().getReference(DBRefrences.WALLET())
                .orderByChild("walletID")
                .equalTo(creditWalletID);
        query2.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //
                Query query = FirebaseDatabase.getInstance().getReference(DBRefrences.WALLET())
                        .orderByChild("walletID")
                        .equalTo(creditWalletID);
                query.addListenerForSingleValueEvent(onDataChangedListener2);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //save data
        List<Wallet> result1 = new ArrayList<>();
        List<Wallet> result2 = new ArrayList<>();
        List<TransactionCharges> result3 =  new ArrayList<>();
        List<SendMoney> result4 =  new ArrayList<>();
        //validate wallet balance.
        if(debitWallet.getWalletBalance() < 0){
            throw new Exception("You do not have enough money in your WALLET to perform this transaction.");
        }
        //save wallet to the system.
        walletDB.child(debitWalletID).setValue(debitWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                //compile result event
                result1.add(debitWallet);
                //save wallet to the system.
                walletDB.child(creditWalletID).setValue(creditWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //raise event
                        result2.add(creditWallet);
                        //save charges
                        for(ChargeDefinition c: charges){
                            Timestamp ts = new Timestamp(new Date().getTime());
                            TransactionCharges tr = new TransactionCharges("",ts, authID, customerIP,creditWallet.getCustomerID(),creditWalletID,transactValue,c.getChargeType(),c.getChargePercentage(), -(transactValue * c.chargePercentage));
                            String ID = transactionChargeDB.push().getKey();
                            tr.setID(ID);
                            transactionChargeDB.child(ID).setValue(tr);
                            result3.add(tr);
                        }
                        //save sendMoney
                        Timestamp ts = new Timestamp(new Date().getTime());
                        SendMoney sendMoney = new SendMoney("",ts,authID,customerIP,debitWallet.getCustomerID(),Types.SEND_MONEY(),debitWalletID,debitWallet.getWalletCurrency(),-transactValue,creditWalletID,creditWallet.getWalletCurrency(),(transactValue - finalChargeValue));
                        String ID = sendMoneyDB.push().getKey();
                        sendMoney.setID(ID);
                        sendMoneyDB.child(ID).setValue(sendMoney).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                //raise event
                                result4.add(sendMoney);
                                sendMoneyEvents.onSendMoneySucceeded(result1, result2, result3, result4);
                            }
                        });
                    }
                });
            }
        });
    }


}
