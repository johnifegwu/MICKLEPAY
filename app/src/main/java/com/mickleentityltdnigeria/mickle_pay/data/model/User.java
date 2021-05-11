package com.mickleentityltdnigeria.mickle_pay.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@IgnoreExtraProperties
public class User implements Serializable {

    public String userID;
    public Timestamp timestamp;
    public String userType;
    public String userName;
    public boolean isLoggedIn;
    public String UUID;
    public long loginExpiryTime;

    public User() {
        this.setLoginExpiryTime();
    }

    public User(String userID, Timestamp timestamp, String userType,String userName, boolean isLoggedIn, String UUID) {
        this.userID = userID;
        this.timestamp = timestamp;
        this.userType = userType;
        this.userName = userName;
        this.isLoggedIn = isLoggedIn;
        this.UUID = UUID;
        this.setLoginExpiryTime();
    }

    @Exclude
    public void setLoginExpiryTime(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        this.loginExpiryTime = timestamp.getTime();
    }

    @Exclude
    public boolean isLoggedInExpired(){
        Timestamp timestamp = new Timestamp(new Date().getTime() + 1800000);
        if(loginExpiryTime <= timestamp.getTime() ){
            return true;
        }else{
            return false;
        }
    }

    @Exclude
    public String getUserID() {
        return userID;
    }

    @Exclude
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Exclude
    public Timestamp getTimestamp() {
        return timestamp;
    }

    @Exclude
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Exclude
    public String getUserType() {
        return userType;
    }

    @Exclude
    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Exclude
    public String getUserName() {
        return userName;
    }

    @Exclude
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Exclude
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Exclude
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    @Exclude
    public String getUUID() {
        return UUID;
    }

    @Exclude
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    @Exclude
    public long getLoginExpiryTime() {
        return loginExpiryTime;
    }

}
