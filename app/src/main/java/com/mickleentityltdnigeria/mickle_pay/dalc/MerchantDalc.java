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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.MerchantEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.Merchant;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

import java.util.ArrayList;
import java.util.List;

public class MerchantDalc {

    private FirebaseDatabase database;
    private final DatabaseReference merchantDB;

    private MerchantEvents merchantEventsListener;

    public MerchantDalc(MerchantEvents merchantEventsListener) {
        this.merchantEventsListener = merchantEventsListener;
        this.database = FirebaseDatabase.getInstance();
        this.merchantDB = database.getReference(DBReferences.MERCHANTS());
    }

    public void addMerchant(Merchant merchant){
        String ID = merchantDB.push().getKey();
        merchant.setMerchantID(ID);
        merchantDB.child(ID).setValue(merchant).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Merchant> result = new ArrayList<>();
                result.add(merchant);
                merchantEventsListener.onMerchantAdded(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                merchantEventsListener.onError(e);
            }
        });
    }

    public void updateMerchant(Merchant merchant){
        merchantDB.child(merchant.getMerchantID()).setValue(merchant).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Merchant> result = new ArrayList<>();
                result.add(merchant);
                merchantEventsListener.onMerchantUpdated(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                merchantEventsListener.onError(e);
            }
        });
    }

    public void getMerchantByAPIKey(String apiKey){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Merchant> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Merchant merchant = userSnapshot.getValue(Merchant.class);
                                result.add(merchant);
                            }
                            merchantEventsListener.onMerchantDataFetched(result);
                        }
                    }
                } else {
                    merchantEventsListener.onMerchantRecordNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantEventsListener.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.MERCHANTS())
                .orderByChild("apiKEY")
                .equalTo(apiKey);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getMerchantByUserID(String userID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Merchant> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Merchant merchant = userSnapshot.getValue(Merchant.class);
                                result.add(merchant);
                            }
                            merchantEventsListener.onMerchantDataFetched(result);
                        }
                    }
                } else {
                    merchantEventsListener.onMerchantRecordNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantEventsListener.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.MERCHANTS())
                .orderByChild("userID")
                .equalTo(userID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getMerchantByMerchantID(String merchantID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Merchant> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Merchant merchant = userSnapshot.getValue(Merchant.class);
                                result.add(merchant);
                            }
                            merchantEventsListener.onMerchantDataFetched(result);
                        }
                    }
                } else {
                    merchantEventsListener.onMerchantRecordNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                merchantEventsListener.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.MERCHANTS())
                .orderByChild("merchantID")
                .equalTo(merchantID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }


}
