package com.mickleentityltdnigeria.mickle_pay.util;

public class Types {

    private final static String CARD_DEPOSIT = "CARD_DEPOSIT";
    private final static String MERCHANT_PAYMENT = "MERCHANT_PAYMENT";
    private final static String SEND_MONEY = "SEND_MONEY";
    private final static String EXCHANGE = "EXCHANGE";

    public static String CARD_DEPOSIT(){
        return CARD_DEPOSIT;
    }

    public static String MERCHANT_PAYMENT(){
        return MERCHANT_PAYMENT;
    }

    public static String SEND_MONEY(){
        return SEND_MONEY;
    }

    public static String EXCHANGE(){
        return EXCHANGE;
    }

    static class CustomerType{

        private final static String CUSTOMER = "CUSTOMER";
        private final static String MERCHANT = "MERCHANT";

        public static String MERCHANT(){
            return MERCHANT;
        }

        public static String CUSTOMER(){
            return CUSTOMER;
        }

    }

}
