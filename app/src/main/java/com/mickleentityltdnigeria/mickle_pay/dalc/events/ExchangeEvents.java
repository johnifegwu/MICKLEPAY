package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;

import com.google.firebase.database.DatabaseError;
import com.mickleentityltdnigeria.mickle_pay.data.model.Exchange;
import com.mickleentityltdnigeria.mickle_pay.data.model.TransactionCharges;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;

import java.util.List;

public interface ExchangeEvents {

    void onExchangeSucceeded(List<Wallet> debitWallets, List<Wallet> creditWallets, List<TransactionCharges> charges, List<Exchange> exchanges);

    void onExchangeFailed(@NonNull Exception e);

    void onWalletDataNotFound(@NonNull Exception e);

}
