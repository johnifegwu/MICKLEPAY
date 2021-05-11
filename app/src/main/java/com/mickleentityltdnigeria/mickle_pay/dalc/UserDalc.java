package com.mickleentityltdnigeria.mickle_pay.dalc;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.UserEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.User;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

import java.util.ArrayList;
import java.util.List;

public class UserDalc {

    private FirebaseDatabase database;
    private final DatabaseReference userDB;

    UserEvents userEvents;

    public UserDalc(UserEvents userEvents) {
        this.userEvents = userEvents;
        this.database = FirebaseDatabase.getInstance();
        this.userDB = database.getReference(DBReferences.USERS());
    }

    public void addUser(User user, FirebaseUser fbUser){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                userEvents.onDuplicateUserFound(new Exception("User: " + user.getUserName() + " already exist in the system."));
                            }
                        }
                    }
                } else {
                   //Add new user to the system.
                    user.setUserID(fbUser.getUid());
                    userDB.child(fbUser.getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid){
                            List<User> users = new ArrayList<>();
                            users.add(user);
                            userEvents.onUserAdded(users);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            userEvents.onError(e);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.USERS())
                .orderByChild("userName")
                .equalTo(user.getUserName());
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void updateUser(User user){
        userDB.child(user.getUserID()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                List<User> users = new ArrayList<>();
                users.add(user);
                userEvents.onUserUpdated(users);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                userEvents.onError(e);
            }
        });
    }

    public void getUserByUserID(String userID){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<User> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                User user = userSnapshot.getValue(User.class);
                                result.add(user);
                            }
                            userEvents.onUserDataFetched(result);
                        }
                    }
                } else {
                    userEvents.onUserDataNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.USERS())
                .orderByChild("userID")
                .equalTo(userID);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

    public void getUserByUserName(String userName){
        ValueEventListener onDataChangedListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            List<User> result = new ArrayList<>();
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                User user = userSnapshot.getValue(User.class);
                                result.add(user);
                            }
                            userEvents.onUserDataFetched(result);
                        }
                    }
                } else {
                    userEvents.onUserDataNotFound(new Exception("No record found for the current user in the system."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userEvents.onError(new Exception(error.getMessage()));
            }
        };
        //
        Query query = FirebaseDatabase.getInstance().getReference(DBReferences.USERS())
                .orderByChild("userName")
                .equalTo(userName);
        query.addListenerForSingleValueEvent(onDataChangedListener);
    }

}
