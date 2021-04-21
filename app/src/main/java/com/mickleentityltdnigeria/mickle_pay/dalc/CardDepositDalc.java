package com.mickleentityltdnigeria.mickle_pay.dalc;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.CardDepositEvent;
import com.mickleentityltdnigeria.mickle_pay.util.DBRefrences;

public class CardDepositDalc {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference cardDepositDB = database.getReference(DBRefrences.CARD_DEPOSITS());
    DatabaseReference walletDB = database.getReference(DBRefrences.WALLET());
    DatabaseReference transactionChargeDB = database.getReference(DBRefrences.TRANSACTION_CHARGES());

    CardDepositEvent cardDepositEvent;

    public CardDepositDalc(CardDepositEvent cardDepositEvent) {
        this.cardDepositEvent = cardDepositEvent;
    }

}
