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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.CustomerEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.Customer;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

import java.util.ArrayList;
import java.util.List;

public class CustomerDalc {

    private FirebaseDatabase database;
    private final DatabaseReference customerDB;

    private CustomerEvents customerEvents;

    public CustomerDalc(CustomerEvents customerEvents) {
        this.customerEvents = customerEvents;
        this.database = FirebaseDatabase.getInstance();
        this.customerDB = database.getReference(DBReferences.CUSTOMERS());
    }

    public void addCustomer(Customer customer){
        String ID = customerDB.push().getKey();
        customer.setCustomerID(ID);
        customerDB.child(ID).setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Customer> result = new ArrayList<>();
                result.add(customer);
                customerEvents.onCustomerAdded(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                customerEvents.onError(e);
            }
        });
    }

    public void updateCustomer(Customer customer){
        customerDB.child(customer.getCustomerID()).setValue(customer).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Customer> result = new ArrayList<>();
                result.add(customer);
                customerEvents.onCustomerUpdated(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                customerEvents.onError(e);
            }
        });
    }

    public void getCustomerByUserID(String userID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Customer> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Customer customer = userSnapshot.getValue(Customer.class);
                                result.add(customer);
                            }
                            customerEvents.onCustomerDataFetched(result);
                        }
                    }
                } else {
                    customerEvents.onCustomerRecordNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                customerEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.CUSTOMERS())
                .orderByChild("userID")
                .equalTo(userID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getCustomerByCustomerID(String customerID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Customer> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Customer customer = userSnapshot.getValue(Customer.class);
                                result.add(customer);
                            }
                            customerEvents.onCustomerDataFetched(result);
                        }
                    }
                } else {
                    customerEvents.onCustomerRecordNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                customerEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.CUSTOMERS())
                .orderByChild("customerID")
                .equalTo(customerID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

}
