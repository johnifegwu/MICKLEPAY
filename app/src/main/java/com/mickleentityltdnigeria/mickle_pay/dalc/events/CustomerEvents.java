package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.Customer;

import java.util.List;

public interface CustomerEvents {

    void onCustomerDataFetched(List<Customer> customers);

    void onCustomerAdded(List<Customer> customers);

    void onCustomerUpdated(List<Customer> customers);

    void onCustomerRecordNotFound(Exception e);

    void onError(Exception e);

}
