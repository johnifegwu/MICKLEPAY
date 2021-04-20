package com.mickleentityltdnigeria.mickle_pay.util;

public class Types {

    private final static String CARD_DEPOSIT = "CARD_DEPOSIT";
    private final static String BANK_DEPOSIT = "BANK_DEPOSIT";
    private final static String INTERNAL_DEPOSIT = "INTERNAL_DEPOSIT";
    private final static String MERCHANT_PAYMENT = "MERCHANT_PAYMENT";
    private final static String SEND_MONEY = "SEND_MONEY";
    private final static String EXCHANGE = "EXCHANGE";
    private final static String WITHDRAWAL = "WITHDRAWAL";

    public static String CARD_DEPOSIT(){
        return CARD_DEPOSIT;
    }

    public static String BANK_DEPOSIT(){
        return BANK_DEPOSIT;
    }

    public static String INTERNAL_DEPOSIT(){
        return INTERNAL_DEPOSIT;
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

    public static String WITHDRAWAL(){
        return WITHDRAWAL;
    }

    static class CustomerType {

        private final static String CUSTOMER = "CUSTOMER";
        private final static String MERCHANT = "MERCHANT";

        public static String MERCHANT(){
            return MERCHANT;
        }

        public static String CUSTOMER(){
            return CUSTOMER;
        }

    }

    static class WalletType {

        private final static String DEFAULT = "DEFAULT";
        private final static String OTHER = "OTHER";

        public static String DEFAULT(){
            return DEFAULT;
        }

        public static String OTHER(){
            return OTHER;
        }
    }

    static class CardType {

        private final static String VISA = "VISA";
        private final static String MASTERCARD = "MASTERCARD";
        private final static String AMERICAN_EXPRESS = "AMERICAN_EXPRESS";
        private final static String DISCOVER = "DISCOVER";

        public static String VISA(){
            return VISA;
        }

        public static String MASTERCARD(){
            return MASTERCARD;
        }

        public static String AMERICAN_EXPRESS(){
            return AMERICAN_EXPRESS;
        }

        public static String DISCOVER(){
            return DISCOVER;
        }

    }

    public static class TransactStatus {

        private final static String APPROVED = "APPROVED";
        private final static String CANCELED = "CANCELED";
        private final static String INSUFFICIENT_BALANCE = "INSUFFICIENT_BALANCE";
        private final static String FAILED = "FAILED";

        public static String APPROVED(){
            return APPROVED;
        }

        public static String CANCELED(){
            return CANCELED;
        }

        public static String INSUFFICIENT_BALANCE(){
            return INSUFFICIENT_BALANCE;
        }

        public static String FAILED(){
            return FAILED;
        }

    }

    public static class ChargeType {

        private final static String CHARGE_ON_DEPOSIT = "CHARGE_ON_DEPOSIT";
        private final static String CHARGE_ON_WITHDRAWAL = "CHARGE_ON_WITHDRAWAL";
        private final static String CHARGE_ON_PAYMENT = "CHARGE_ON_PAYMENT";
        private final static String CHARGE_ON_EXCHANGE = "CHARGE_ON_EXCHANGE";

        public static String CHARGE_ON_DEPOSIT(){
            return CHARGE_ON_DEPOSIT;
        }

        public static String CHARGE_ON_WITHDRAWAL(){
            return CHARGE_ON_WITHDRAWAL;
        }

        public static String CHARGE_ON_PAYMENT(){
            return CHARGE_ON_PAYMENT;
        }

        public static String CHARGE_ON_EXCHANGE(){
            return CHARGE_ON_EXCHANGE;
        }


    }

}
