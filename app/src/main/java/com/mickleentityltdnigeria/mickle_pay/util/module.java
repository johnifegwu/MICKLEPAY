package com.mickleentityltdnigeria.mickle_pay.util;

import com.mickleentityltdnigeria.mickle_pay.exceptions.NetworkException;

public class module {

    public static String Wifi_ENABLED = "Wifi enabled";
    public static String Mobile_Data_ENABLED = "Mobile data enabled";
    public static String No_Network = "No internet enabled";

    public static void checkNetwork() throws NetworkException {
        String status = NetworkHelper.getNetworkStatus(AppGlobals.getAppContext());
        if(status == null || status == module.No_Network){
            throw new NetworkException();
        }
    }

}
