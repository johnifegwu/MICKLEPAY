package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.User;

import java.util.List;

public interface UserEvents {

    Void onUserDataFetched(List<User> users);

    Void onUserAdded(List<User> users);

    Void onUserUpdated(List<User> users);

    Void onUserDataNotFound(Exception e);

    Void onDuplicateUserFound(Exception e);

    Void onError(Exception e);

}
