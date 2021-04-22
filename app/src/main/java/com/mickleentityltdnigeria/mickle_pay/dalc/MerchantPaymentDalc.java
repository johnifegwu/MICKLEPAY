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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.MerchantPaymentEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.Exchange;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
import com.mickleentityltdnigeria.mickle_pay.data.model.MerchantPayment;
import com.mickleentityltdnigeria.mickle_pay.data.model.SendMoney;
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
import java.util.Objects;

public class MerchantPaymentDalc {

    private FirebaseDatabase database;
    private final DatabaseReference merchantPaymentDB;
    private final DatabaseReference walletDB;
    private final DatabaseReference transactionChargeDB;
    private final DatabaseReference exchangeDB;

    private final MerchantPaymentEvents merchantPaymentEvents;

    public MerchantPaymentDalc(MerchantPaymentEvents merchantPaymentEvents) {
        this.merchantPaymentEvents = merchantPaymentEvents;
        this.database = FirebaseDatabase.getInstance();
        this.merchantPaymentDB = database.getReference(DBReferences.MERCHANT_PAYMENTS());
        this.walletDB = database.getReference(DBReferences.WALLET());
        this.transactionChargeDB = database.getReference(DBReferences.TRANSACTION_CHARGES());
        this.exchangeDB = database.getReference(DBReferences.EXCHANGE());
    }

    public void collectPayment(Wallet customerWallet, Wallet merchantWallet, Wallet subMerchantWallet, String transactionDesc, String transactionCurrency, double transactionTotal, double mainMerchantSubTotal, double subMerchantSubTotal, List<ChargeDefinition> charges, Map<String, ExchangeRate> exchangeRates, String authID, String merchantIP) throws Exception {
        if(!customerWallet.getWalletCurrency().equals(merchantWallet.getWalletCurrency()) || !customerWallet.getWalletCurrency().equals(transactionCurrency) || !merchantWallet.getWalletCurrency().equals(transactionCurrency)){
            throw new Exception("Customer's Wallet Currency not compatible with Merchant's Wallet currency.");
        }
        if(transactionTotal < (mainMerchantSubTotal + subMerchantSubTotal) || transactionTotal > (mainMerchantSubTotal + subMerchantSubTotal)){
            throw new Exception("Invalid transaction: amount paid less than or greater than transaction value.");
        }
        if(!customerWallet.getCustomerID().equals(merchantWallet.getCustomerID()) && !customerWallet.getCustomerID().equals(subMerchantWallet.getCustomerID())){
            throw new Exception("Invalid WALLET ID provided.");
        }
        ValueEventListener onDataChangedListener3 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                assert wallet != null;
                                double exchangedValue = subMerchantSubTotal;
                                //perform exchange if sub merchants currency is different from transaction currency.
                                Exchange exchange = new Exchange();
                                boolean isExchanged = false;
                                if(!subMerchantWallet.getWalletCurrency().equals(transactionCurrency)){
                                    //get exchanged value and gains
                                    isExchanged = true;
                                    exchangedValue = Exchange.calcExchangeValue(transactionCurrency, subMerchantSubTotal, subMerchantWallet.getWalletCurrency(), exchangeRates);
                                    double exchangeGained = Exchange.calcExchangeGained(transactionCurrency, subMerchantSubTotal, subMerchantWallet.getWalletCurrency(), exchangeRates);
                                    double exchangeRate = Objects.requireNonNull(Objects.requireNonNull(exchangeRates.get(transactionCurrency)).getExchangeRates().get(subMerchantWallet.getWalletCurrency())).getExchangeRate();
                                    //
                                    exchange.setCreditAmount(exchangedValue);
                                    exchange.setCreditWalletCurrency(subMerchantWallet.getWalletCurrency());
                                    exchange.setCreditWalletID(subMerchantWallet.getWalletID());
                                    exchange.setAuthID(authID);
                                    exchange.setCustomerIP(merchantIP);
                                    exchange.setCustomerID(merchantWallet.getCustomerID());
                                    exchange.setDebitAmount(subMerchantSubTotal);
                                    exchange.setDebitWalletCurrency(customerWallet.getWalletCurrency());
                                    exchange.setDebitWalletID(customerWallet.getWalletID());
                                    exchange.setExchangeDesc("Exchange from " + customerWallet.getWalletCurrency() + " to " + subMerchantWallet.getWalletCurrency());
                                    exchange.setExchangeGained(exchangeGained);
                                    exchange.setExchangeRate(exchangeRate);
                                }
                                double chargeValue = 0;
                                for (ChargeDefinition c : charges) {
                                    if(!c.isDisabled()) {
                                        chargeValue += (exchangedValue * c.getChargePercentage());
                                    }
                                }
                                subMerchantWallet.setWalletBalance(wallet.getWalletBalance() + (exchangedValue - chargeValue));
                                subMerchantWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), subMerchantWallet.getWalletID(), authID, merchantIP, subMerchantWallet.getCustomerID(), Types.MERCHANT_PAYMENT(), exchangedValue, Types.MERCHANT_PAYMENT(), authID, subMerchantWallet.getWalletID());
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                if (chargeValue < 0 || chargeValue > 0) {
                                    WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), subMerchantWallet.getWalletID(), authID, merchantIP, subMerchantWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_PAYMENT(), -exchangedValue, Types.ChargeType.CHARGE_ON_PAYMENT(), authID, subMerchantWallet.getWalletID());
                                    map.put(new Timestamp(new Date().getTime()), wallTran2);
                                }
                                subMerchantWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(customerWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    double finalExchangedValue = exchangedValue;
                                    boolean finalIsExchanged = isExchanged;
                                    walletDB.child(subMerchantWallet.getID()).setValue(subMerchantWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //save charges
                                            for(ChargeDefinition c: charges){
                                                if(!c.isDisabled()) {
                                                    Timestamp ts = new Timestamp(new Date().getTime());
                                                    TransactionCharges tc = new TransactionCharges("", ts, authID, merchantIP, subMerchantWallet.getCustomerID(), subMerchantWallet.getWalletID(), finalExchangedValue, c.getChargeType(), c.getChargePercentage(), -(finalExchangedValue * c.chargePercentage));
                                                    String ID = transactionChargeDB.push().getKey();
                                                    tc.setID(ID);
                                                    assert ID != null;
                                                    transactionChargeDB.child(ID).setValue(tc);
                                                }
                                            }
                                            //save Merchant payment
                                            Timestamp ts = new Timestamp(new Date().getTime());
                                            MerchantPayment merchantPayment = new MerchantPayment("",ts,authID,merchantIP,merchantWallet.getCustomerID(),transactionDesc,customerWallet.getWalletID(),customerWallet.getWalletCurrency(),transactionTotal,merchantWallet.getWalletID(),merchantWallet.getWalletCurrency(),mainMerchantSubTotal,subMerchantWallet.getWalletID(),subMerchantWallet.getWalletCurrency(), subMerchantSubTotal);
                                            String ID = merchantPaymentDB.push().getKey();
                                            merchantPayment.setID(ID);
                                            assert ID != null;
                                            merchantPaymentDB.child(ID).setValue(merchantPayment).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    if(finalIsExchanged){
                                                        Timestamp ts = new Timestamp(new Date().getTime());
                                                        String ID = exchangeDB.push().getKey();
                                                        exchange.setID(ID);
                                                        exchange.setTimestamp(ts);
                                                        exchangeDB.child(ID).setValue(exchange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                merchantPaymentEvents.onPaymentSuccessful(merchantPayment);
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                merchantPaymentEvents.onPaymentFailed(e);
                                                            }
                                                        });
                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    merchantPaymentEvents.onPaymentFailed(e);
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            merchantPaymentEvents.onPaymentFailed(e);
                                        }
                                    });
                                }
                                break;
                            }
                        }
                    }
                } else {
                    merchantPaymentEvents.onWalletDataNotFound(new Exception("Invalid Sub Merchant WALLET ID provided."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantPaymentEvents.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };
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
                                double chargeValue = 0;
                                for (ChargeDefinition c : charges) {
                                    if(!c.isDisabled()) {
                                        chargeValue += (mainMerchantSubTotal * c.getChargePercentage());
                                    }
                                }
                                merchantWallet.setWalletBalance(wallet.getWalletBalance() + (mainMerchantSubTotal - chargeValue));
                                merchantWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), merchantWallet.getWalletID(), authID, merchantIP, merchantWallet.getCustomerID(), Types.MERCHANT_PAYMENT(), mainMerchantSubTotal, Types.MERCHANT_PAYMENT(), authID, merchantWallet.getWalletID());
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                if (chargeValue < 0 || chargeValue > 0) {
                                    WalletTransactions wallTran2 = new WalletTransactions(new Timestamp(new Date().getTime()), merchantWallet.getWalletID(), authID, merchantIP, merchantWallet.getCustomerID(), Types.ChargeType.CHARGE_ON_PAYMENT(), -mainMerchantSubTotal, Types.ChargeType.CHARGE_ON_PAYMENT(), authID, merchantWallet.getWalletID());
                                    map.put(new Timestamp(new Date().getTime()), wallTran2);
                                }
                                merchantWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(customerWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    walletDB.child(merchantWallet.getID()).setValue(merchantWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            //save charges
                                            for(ChargeDefinition c: charges){
                                                if(!c.isDisabled()) {
                                                    Timestamp ts = new Timestamp(new Date().getTime());
                                                    TransactionCharges tc = new TransactionCharges("", ts, authID, merchantIP, merchantWallet.getCustomerID(), merchantWallet.getWalletID(), mainMerchantSubTotal, c.getChargeType(), c.getChargePercentage(), -(mainMerchantSubTotal * c.chargePercentage));
                                                    String ID = transactionChargeDB.push().getKey();
                                                    tc.setID(ID);
                                                    assert ID != null;
                                                    transactionChargeDB.child(ID).setValue(tc);
                                                }
                                            }
                                            Query query3 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                                                    .orderByChild("walletID")
                                                    .equalTo(subMerchantWallet.getWalletID());
                                            query3.addListenerForSingleValueEvent(onDataChangedListener3);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            merchantPaymentEvents.onPaymentFailed(e);
                                        }
                                    });
                                }
                                break;
                            }
                        }
                    }
                } else {
                    merchantPaymentEvents.onWalletDataNotFound(new Exception("Invalid Merchant WALLET ID provided."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantPaymentEvents.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };
        ValueEventListener onDataChangedListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                assert wallet != null;
                                customerWallet.setWalletBalance(wallet.getWalletBalance() - transactionTotal);
                                customerWallet.setWalletTransactions(wallet.getWalletTransactions());
                                Map<Timestamp, WalletTransactions> map = new HashMap<>();
                                WalletTransactions wallTran = new WalletTransactions(new Timestamp(new Date().getTime()), customerWallet.getWalletID(), authID, merchantIP, customerWallet.getCustomerID(), Types.MERCHANT_PAYMENT(), -transactionTotal, Types.MERCHANT_PAYMENT(), authID, merchantWallet.getWalletID());
                                map.put(new Timestamp(new Date().getTime()), wallTran);
                                customerWallet.setWalletTransactions(map);
                                //validate wallet balance.
                                if(customerWallet.getWalletBalance() >= 0){
                                    //save wallet to the system.
                                    walletDB.child(customerWallet.getID()).setValue(customerWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Query query2 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                                                    .orderByChild("walletID")
                                                    .equalTo(merchantWallet.getWalletID());
                                            query2.addListenerForSingleValueEvent(onDataChangedListener2);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            merchantPaymentEvents.onPaymentFailed(e);
                                        }
                                    });
                                }else{
                                    merchantPaymentEvents.onPaymentFailed( new Exception("The Customer do not have enough money left in his/her WALLET to complete this transaction."));
                                }
                                break;
                            }
                        }
                    }
                } else {
                    merchantPaymentEvents.onWalletDataNotFound(new Exception("Invalid Customer WALLET ID provided."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantPaymentEvents.onWalletDataNotFound(new Exception(error.getMessage()));
            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("walletID")
                .equalTo(customerWallet.getWalletID());
        query1.addListenerForSingleValueEvent(onDataChangedListener1);

    }

}
