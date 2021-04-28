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
import com.mickleentityltdnigeria.mickle_pay.dalc.events.DocumentEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.Document;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.util.ArrayList;
import java.util.List;

public class DocumentDalc {

    private FirebaseDatabase database;
    private final DatabaseReference documentDB;

    private final DocumentEvents documentEvents;

    public DocumentDalc(DocumentEvents documentEvents) {
        this.documentEvents = documentEvents;
        this.database = FirebaseDatabase.getInstance();
        this.documentDB = database.getReference(DBReferences.DOCUMENTS());
    }

    public void addDocument(Document document){
        String ID = documentDB.push().getKey();
        document.setDocumentID(ID);
        assert ID != null;
        documentDB.child(ID).setValue(document).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Document> result = new ArrayList<>();
                result.add(document);
                documentEvents.onDocumentAdded(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                documentEvents.onError(e);
            }
        });
    }

    public void updateDocument(Document document){
        documentDB.child(document.getDocumentID()).setValue(document).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<Document> result = new ArrayList<>();
                result.add(document);
                documentEvents.onDocumentUpdated(result);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                documentEvents.onError(e);
            }
        });
    }

    public void deleteDocument(String documentID){
        documentDB.child(documentID).setValue(null).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                documentEvents.onDocumentDeleted(documentID);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                documentEvents.onError(e);
            }
        });
    }

    public void getDocumentsByOwnerID(String OwnerID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Document> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Document document = userSnapshot.getValue(Document.class);
                                result.add(document);
                            }
                            documentEvents.onDocumentsFetched(result);
                        }
                    }
                } else {
                    documentEvents.onDocumentNotFound(new Exception("No document found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                documentEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.DOCUMENTS())
                .orderByChild("OwnerID")
                .equalTo(OwnerID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getNewDocuments(){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<Document> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                Document document = userSnapshot.getValue(Document.class);
                                result.add(document);
                            }
                            documentEvents.onDocumentsFetched(result);
                        }
                    }
                } else {
                    documentEvents.onDocumentNotFound(new Exception("No document found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                documentEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.DOCUMENTS())
                .orderByChild("approvalStatus")
                .equalTo(Types.ApprovalStatus.PENDING());
        query.addValueEventListener(onDataChangedListener);
    }

}
