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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.Exchange;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
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
import java.util.Objects;

public class ExchangeDalc {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference exchangeDB = database.getReference(DBRefrences.EXCHANGE());
    DatabaseReference walletDB = database.getReference(DBRefrences.WALLET());
    DatabaseReference transactionChargeDB = database.getReference(DBRefrences.TRANSACTION_CHARGES());

    private ExchangeEvents exchangeEvents;

    public ExchangeDalc(ExchangeEvents exchangeEvents) {
        this.exchangeEvents = exchangeEvents;
    }

    public void exchangeMoney(Wallet debitWallet, Wallet creditWallet, double transactValue, List<ChargeDefinition> charges, Map<String, ExchangeRate> exchangeRates, String authID, String customerIP) throws Exception {
        //Check currency code
        if (debitWallet.getWalletCurrency().equals(creditWallet.getWalletCurrency())) {
            throw new Exception("The Wallet you are exchanging money to must not be of a different Currency.");
        }
        //check ownership
        if(!debitWallet.getCustomerID().equals(creditWallet.getCustomerID())){
            throw new Exception("You can only exchange money to your own WALLET.");
        }
        String debitWalletID = debitWallet.getWalletID();
        String creditWalletID = creditWallet.getWalletID();
        //get exchanged value and gains
        double exchangedValue = Exchange.calcExchangeValue(debitWallet.getWalletCurrency(),transactValue,creditWallet.getWalletCurrency(),exchangeRates);
        double exchangeGained = Exchange.calcExchangeGained(debitWallet.getWalletCurrency(),transactValue,creditWallet.getWalletCurrency(),exchangeRates);
        double exchangeRate = Objects.requireNonNull(exchangeRates.get(debitWallet.getWalletCurrency())).getExchangeRates().get(creditWallet.getWalletCurrency()).getExchangeRate();
        //
        double chargeValue = 0;
        for (ChargeDefinition c : charges) {
            chargeValue += (exchangedValue * c.chargePercentage);
        }
        //save data
        List<Wallet> result1 = new ArrayList<>();
        List<Wallet> result2 = new ArrayList<>();
        List<TransactionCharges> result3 =  new ArrayList<>();
        List<Exchange> result4 =  new ArrayList<>();
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
                                creditWallet.setWalletBalance(wallet.getWalletBalance() + (exchangedValue - finalChargeValue));
                                creditWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.EXCHANGE(), exchangedValue, Types.EXCHANGE(), authID, creditWalletID);
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                if (finalChargeValue < 0 || finalChargeValue > 0) {
                                    WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), creditWalletID, authID, customerIP, creditWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_EXCHANGE(), -finalChargeValue, Types.ChargeType.CHARGE_ON_EXCHANGE(), authID, creditWalletID);
                                    map.put(new Timestamp(new Date().getTime()), wallTran2);
                                }
                                creditWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(debitWallet.getWalletBalance() >= 0) {
                                    //save wallet to the system.
                                    walletDB.child(creditWalletID).setValue(creditWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //raise event
                                            result2.add(creditWallet);
                                            //save charges
                                            for (ChargeDefinition c : charges) {
                                                Timestamp ts = new Timestamp(new Date().getTime());
                                                TransactionCharges tr = new TransactionCharges("", ts, authID, customerIP, creditWallet.getCustomerID(), creditWalletID, transactValue, c.getChargeType(), c.getChargePercentage(), -(exchangedValue * c.chargePercentage));
                                                String ID = transactionChargeDB.push().getKey();
                                                tr.setID(ID);
                                                transactionChargeDB.child(ID).setValue(tr);
                                                result3.add(tr);
                                            }
                                            //save Exchange
                                            Timestamp ts = new Timestamp(new Date().getTime());
                                            Exchange exchange = new Exchange("", ts, authID, customerIP, debitWallet.getCustomerID(), "Exchange from " + debitWallet.getWalletCurrency() + " to " + creditWallet.getWalletCurrency(), debitWalletID, debitWallet.getWalletCurrency(), transactValue, exchangeRate, creditWalletID, creditWallet.getWalletCurrency(), exchangedValue, exchangeGained);
                                            String ID = exchangeDB.push().getKey();
                                            exchange.setID(ID);
                                            exchangeDB.child(ID).setValue(exchange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    //raise event
                                                    result4.add(exchange);
                                                    exchangeEvents.onExchangeSucceeded(result1, result2, result3, result4);
                                                }
                                            });
                                        }
                                    });
                                }
                                break;
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
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
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), debitWalletID, authID, customerIP, debitWallet.getCustomerID(), Types.EXCHANGE(), -transactValue, Types.EXCHANGE(), authID, creditWalletID);
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                debitWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(debitWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    walletDB.child(debitWalletID).setValue(debitWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //compile result
                                            result1.add(debitWallet);
                                            Query query2 = FirebaseDatabase.getInstance().getReference(DBRefrences.WALLET())
                                                    .orderByChild("walletID")
                                                    .equalTo(creditWalletID);
                                            query2.addListenerForSingleValueEvent(onDataChangedListener2);
                                        }
                                    });
                                }
                                break;
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
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

}
