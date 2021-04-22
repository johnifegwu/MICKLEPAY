package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;

import com.mickleentityltdnigeria.mickle_pay.data.model.MerchantPayment;

import java.util.List;

public interface MerchantPaymentEvents {

    void onPaymentSuccessful(MerchantPayment merchantPayment);

    void onPaymentFailed(@NonNull Exception e);

    void onPaymentsFetched(List<MerchantPayment> merchantPayments);

    void onPaymentNotFound(@NonNull Exception e);

    void onWalletDataNotFound(@NonNull Exception e);
}
