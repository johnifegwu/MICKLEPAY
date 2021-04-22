package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;

import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;

import java.util.List;

public interface WalletEvents {

    void onWalletAdded(Wallet wallet);

    void onWalletFetched(List<Wallet> wallets);

    void onWalletNotFound(@NonNull Exception e);

    void onWalletFailed(@NonNull Exception e);

    void onDuplicateWallet(@NonNull Exception e);

}
