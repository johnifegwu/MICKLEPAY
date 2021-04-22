package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;

import com.mickleentityltdnigeria.mickle_pay.data.model.BankDeposit;

import java.util.List;

public interface BankDepositEvents {

    void onBankDepositAdded(BankDeposit bankDeposit);

    void onBankDepositProcessedSuccessfully(BankDeposit bankDeposit);

    void onBankDepositsFetched(List<BankDeposit> bankDeposits);

    void onBankDepositsNotFound(@NonNull Exception e);

    void onBankDepositsUpdateFailed(@NonNull Exception e);

    void onWalletDataNotFound(@NonNull Exception e);

}
