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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ChargeDefinitionEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import java.util.ArrayList;
import java.util.List;

public class ChargeDefinitionDalc {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference chargeDefinitionDB = database.getReference(DBReferences.CHARGE_DEFINITION());

    private ChargeDefinitionEvents chargeDefinitionEvents;

    public ChargeDefinitionDalc(ChargeDefinitionEvents chargeDefinitionEvents) {
        this.chargeDefinitionEvents = chargeDefinitionEvents;
    }

    public void addChargeDefinition(ChargeDefinition chargeDefinition){
        String ID = chargeDefinitionDB.push().getKey();
        chargeDefinition.setID(ID);
        assert ID != null;
        chargeDefinitionDB.child(ID).setValue(chargeDefinition).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                chargeDefinitionEvents.onChargeDefinitionAdded(chargeDefinition);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                chargeDefinitionEvents.onChargeDefinitionNotFound(e);
            }
        });
    }

    public void updateChargeDefinition(ChargeDefinition chargeDefinition){
        chargeDefinitionDB.child(chargeDefinition.getID()).setValue(chargeDefinition).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                chargeDefinitionEvents.onChargeDefinitionUpdated(chargeDefinition);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                chargeDefinitionEvents.onChargeDefinitionNotFound(e);
            }
        });
    }

    public void getChargeDefinitions(String chargeType){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<ChargeDefinition> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                ChargeDefinition chargeDefinition = userSnapshot.getValue(ChargeDefinition.class);
                                result.add(chargeDefinition);
                            }
                            chargeDefinitionEvents.onChargeDefinitionFetched(result);
                        }
                    }
                } else {
                    chargeDefinitionEvents.onChargeDefinitionNotFound(new Exception("No record not found"));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                chargeDefinitionEvents.onChargeDefinitionNotFound(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.CHARGE_DEFINITION())
                .orderByChild("chargeType")
                .equalTo(chargeType);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

}
