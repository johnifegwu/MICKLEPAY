package com.mickleentityltdnigeria.mickle_pay.util;

public class DBRefrences {

    private final static String SEND_MONEY = "sendMoney";
    private final static String WALLET = "wallet";
    private final static String TRANSACTION_CHARGES = "transactionCharges";
    private final static String EXCHANGE = "exchange";

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
}
