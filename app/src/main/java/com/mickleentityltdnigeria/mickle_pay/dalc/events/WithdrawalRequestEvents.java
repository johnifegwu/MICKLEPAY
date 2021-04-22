package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;
import com.mickleentityltdnigeria.mickle_pay.data.model.WithdrawalRequest;

import java.util.List;

public interface WithdrawalRequestEvents {

    void onWithdrawalRequestAdded(WithdrawalRequest withdrawalRequest);

    void onWithdrawalRequestProcessedSuccessfully(WithdrawalRequest withdrawalRequest);

    void onWithdrawalRequestFetched(List<WithdrawalRequest> withdrawalRequests);

    void onWithdrawalRequestNotFound(@NonNull Exception e);

    void onWithdrawalRequestUpdateFailed(@NonNull Exception e);

    void onWalletDataNotFound(@NonNull Exception e);
}
