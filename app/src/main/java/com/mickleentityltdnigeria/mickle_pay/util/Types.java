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

    public static class MerchantType {

        private final static String MAIN_MERCHANT = "MAIN_MERCHANT";
        private final static String SUB_MERCHANT = "SUB_MERCHANT";

        public static String MAIN_MERCHANT(){
            return MAIN_MERCHANT;
        }

        public static String SUB_MERCHANT(){
            return SUB_MERCHANT;
        }

    }

    public static class CustomerType {

        private final static String PERSONAL = "PERSONAL";
        private final static String JOINT = "JOINT";

        public static String PERSONAL(){
            return PERSONAL;
        }

        public static String JOINT(){
            return JOINT;
        }

    }

    public static class WalletType {

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

    public static class userType{

        private final static String ADMIN = "ADMIN";
        private final static String CUSTOMER = "CUSTOMER";
        private final static String MERCHANT = "MERCHANT";
        private final static String SUB_MERCHANT = "SUB_MERCHANT";

        public static String ADMIN(){
            return ADMIN;
        }

        public static String CUSTOMER(){
            return CUSTOMER;
        }

        public static String MERCHANT(){
            return MERCHANT;
        }

        public static String SUB_MERCHANT(){
            return SUB_MERCHANT;
        }

    }

    public static class ApprovalStatus{

        private final static String PENDING = "PENDING";
        private final static String NOT_APPROVED = "NOT_APPROVED";
        private final static String APPROVED = "APPROVED";

        public static String PENDING(){
            return PENDING;
        }
        public static String NOT_APPROVED(){
            return NOT_APPROVED;
        }
        public static String APPROVED(){
            return APPROVED;
        }

    }

}
