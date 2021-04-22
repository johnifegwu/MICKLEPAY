package com.mickleentityltdnigeria.mickle_pay.util;

public class DBReferences {

    private final static String SEND_MONEY = "sentMonies";
    private final static String WALLET = "wallets";
    private final static String TRANSACTION_CHARGES = "transactionCharges";
    private final static String EXCHANGE = "exchanges";
    private final static String CARD_DEPOSITS = "cardDeposits";
    private final static String CHARGE_DEFINITION = "chargeDefinitions";
    private final static String EXCHANGE_RATES = "exchangeRates";

    public static String SEND_MONEY(){
        return SEND_MONEY;
    }

    public static String WALLET(){
        return WALLET;
    }

    public static String TRANSACTION_CHARGES(){
        return TRANSACTION_CHARGES;
    }

    public static String EXCHANGE(){
        return EXCHANGE;
    }

    public static String CARD_DEPOSITS(){
        return CARD_DEPOSITS;
    }

    public static String CHARGE_DEFINITION(){
        return CHARGE_DEFINITION;
    }

    public static String EXCHANGE_RATES(){
        return EXCHANGE_RATES;
    }

}
