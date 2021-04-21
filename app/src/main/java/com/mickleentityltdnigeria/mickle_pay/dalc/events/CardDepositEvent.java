package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.CardDeposit;
import com.mickleentityltdnigeria.mickle_pay.data.model.TransactionCharges;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;

import java.util.List;

public interface CardDepositEvent {

    void onCardDepositSucceeded(List<Wallet> creditWallets, List<CardDeposit> cardDeposits, List<TransactionCharges> charges);

}
