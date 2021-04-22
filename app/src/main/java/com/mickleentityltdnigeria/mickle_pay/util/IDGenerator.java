package com.mickleentityltdnigeria.mickle_pay.util;

import java.security.SecureRandom;
import java.util.UUID;

public class IDGenerator {

        public static IDGenerator mInstance;

        public static IDGenerator getInstance(){
            if(mInstance==null){
                mInstance = new IDGenerator();
            }
            return mInstance;
        }

        public String getUUID(){
            return UUID.randomUUID().toString();
        }

    //A function for generating MICKLE-PAY WALLET ID.
    public String getWalletID(String currencyCode){
        String wID = currencyCode;
        SecureRandom sr = new java.security.SecureRandom();
        long i = -1;
        do{
            sr.setSeed(SecureRandom.getSeed(128));
            i = sr.nextLong();
        }while(i < 0);
        wID += String.valueOf(i);
        return wID.toUpperCase();
    }
}
