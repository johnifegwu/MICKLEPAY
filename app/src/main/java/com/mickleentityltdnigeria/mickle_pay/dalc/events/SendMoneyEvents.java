package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.SendMoney;
import com.mickleentityltdnigeria.mickle_pay.data.model.TransactionCharges;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;

import java.util.List;

public interface SendMoneyEvents {

    void onSendMoneySucceeded(List<Wallet> debitWallets, List<Wallet> creditWallets, List<TransactionCharges> charges, List<SendMoney> sendMonies);

}
