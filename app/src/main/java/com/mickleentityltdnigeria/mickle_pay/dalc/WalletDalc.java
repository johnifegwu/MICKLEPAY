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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.WalletEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import com.mickleentityltdnigeria.mickle_pay.util.IDGenerator;

import java.util.ArrayList;
import java.util.List;

public class WalletDalc {

    private FirebaseDatabase database;
    private final DatabaseReference walletDB;

    private final WalletEvents walletEvents;

    public WalletDalc(WalletEvents walletEvents) {
        this.walletEvents = walletEvents;
        this.database = FirebaseDatabase.getInstance();
        this.walletDB = database.getReference(DBReferences.WALLET());
    }

    public void addNewWallet(Wallet newWallet){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            boolean isWalletExist = false;
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                               if(wallet.getWalletCurrency().equals(newWallet.getWalletCurrency())){
                                   isWalletExist = true;
                                   break;
                               }
                            }
                            if(isWalletExist){
                                walletEvents.onDuplicateWallet(new Exception("Wallet already exist."));
                            }else{
                                //get new walletID
                                newWallet.setWalletID(IDGenerator.getInstance().getWalletID(newWallet.getWalletCurrency()));
                                String ID = walletDB.push().getKey();
                                newWallet.setID(ID);
                                assert ID != null;
                                walletDB.child(ID).setValue(newWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        walletEvents.onWalletAdded(newWallet);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        walletEvents.onWalletFailed(e);
                                    }
                                });
                            }
                        }
                    }
                } else {
                    //get new walletID
                    newWallet.setWalletID(IDGenerator.getInstance().getWalletID(newWallet.getWalletCurrency()));
                    String ID = walletDB.push().getKey();
                    newWallet.setID(ID);
                    assert ID != null;
                    walletDB.child(ID).setValue(newWallet).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            walletEvents.onWalletAdded(newWallet);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            walletEvents.onWalletFailed(e);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               // walletEvents.onWalletNotFound(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("customerID")
                .equalTo(newWallet.getCustomerID());
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getWalletsByCustomerID(String customerID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                           List<Wallet> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                result.add(wallet);
                            }
                            walletEvents.onWalletFetched(result);
                        }
                    }
                } else {
                    walletEvents.onWalletNotFound(new Exception("No record found."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                walletEvents.onWalletNotFound(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("customerID")
                .equalTo(customerID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getWalletsByWalletID(String walletID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Wallet> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Wallet wallet = userSnapshot.getValue(Wallet.class);
                                result.add(wallet);
                            }
                            walletEvents.onWalletFetched(result);
                        }
                    }
                } else {
                    walletEvents.onWalletNotFound(new Exception("No record found."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                walletEvents.onWalletNotFound(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.WALLET())
                .orderByChild("walletID")
                .equalTo(walletID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

}
