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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.CardDepositEvent;
import com.mickleentityltdnigeria.mickle_pay.data.model.CardDeposit;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.TransactionCharges;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;
import com.mickleentityltdnigeria.mickle_pay.data.model.WalletTransactions;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDepositDalc {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardDepositDB = database.getReference(DBReferences.CARD_DEPOSITS());
    DatabaseReference walletDB = database.getReference(DBReferences.WALLET());
    DatabaseReference transactionChargeDB = database.getReference(DBReferences.TRANSACTION_CHARGES());

    CardDepositEvent cardDepositEvent;

    public CardDepositDalc(CardDepositEvent cardDepositEvent) {
        this.cardDepositEvent = cardDepositEvent;
    }

    public void cardDeposit(CardDeposit cardDeposit, List<ChargeDefinition> charges){
        //
        String creditWalletID = cardDeposit.getCreditWalletID();
        double chargeValue = 0;
        for (ChargeDefinition c : charges) {
            if(!c.isDisabled()) {
                chargeValue += (cardDeposit.getCreditAmount() * c.getChargePercentage());
            }
        }
        //save data
        List<Wallet> result1 = new ArrayList<>();
        List<TransactionCharges> result2 = new ArrayList<>();
        List<CardDeposit> result3 =  new ArrayList<>();
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
                                creditWallet.setWalletBalance(creditWallet.getWalletBalance() + (cardDeposit.getCreditAmount() - finalChargeValue));
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, cardDeposit.getAuthID(), cardDeposit.getCustomerIP(), creditWallet.getCustomerID(), Types.CARD_DEPOSIT(), cardDeposit.getCreditAmount(), Types.CARD_DEPOSIT(), cardDeposit.getAuthID(), creditWalletID);
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                if (finalChargeValue < 0 || finalChargeValue > 0) {
                                    WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, cardDeposit.getAuthID(), cardDeposit.getCustomerIP(), creditWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_DEPOSIT(), -finalChargeValue, Types.ChargeType.CHARGE_ON_DEPOSIT(), cardDeposit.getAuthID(), creditWalletID);
                                    map.put(new Timestamp(new Date().getTime()), wallTran2);
                                }
                                creditWallet.setWalletTransactions(map);
                                //validate creditAmount.
                                if(cardDeposit.getCreditAmount() > 0) {
                                    //save wallet to the system.
                                    walletDB.child(creditWalletID).setValue(creditWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //raise event
                                            result1.add(creditWallet);
                                            //save charges
                                            for (ChargeDefinition c : charges) {
                                                if(!c.isDisabled()){
                                                Timestamp ts = new Timestamp(new Date().getTime());
                                                TransactionCharges tc = new TransactionCharges("", ts, cardDeposit.getAuthID(), cardDeposit.getCustomerIP(), creditWallet.getCustomerID(), creditWalletID, cardDeposit.getCreditAmount(), c.getChargeType(), c.getChargePercentage(), -(cardDeposit.getCreditAmount() * c.chargePercentage));
                                                String ID = transactionChargeDB.push().getKey();
                                                tc.setID(ID);
                                                assert ID != null;
                                                transactionChargeDB.child(ID).setValue(tc);
                                                result2.add(tc);
                                                }
                                            }
                                            //save CardDeposit
                                            Timestamp ts = new Timestamp(new Date().getTime());
                                            cardDeposit.setTimestamp(ts);
                                            String ID = cardDepositDB.push().getKey();
                                            cardDeposit.setID(ID);
                                            assert ID != null;
                                            cardDepositDB.child(ID).setValue(cardDeposit).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    //raise event
                                                    result3.add(cardDeposit);
                                                    cardDepositEvent.onCardDepositSucceeded(result1, result3, result2);
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    cardDepositEvent.onCardDepositFailed(e);
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            cardDepositEvent.onCardDepositFailed(e);
                                        }
                                    });
                                }
                                break;
                            }
                        }
                    }
                }else {
                    cardDepositEvent.onWalletDataNotFound(new Exception("Wallet data not found"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                cardDepositEvent.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };
        Query query2 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("walletID")
                .equalTo(creditWalletID);
        query2.addListenerForSingleValueEvent(onDataChangedListener2);
    }

}
