package com.mickleentityltdnigeria.mickle_pay.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkHelper {

    public static String getNetworkStatus(Context context){
        String status = null;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if(activeNetwork != null){
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                status = module.Wifi_ENABLED;
                return status;
            }else if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                status = module.Mobile_Data_ENABLED;
                return status;
            } else{
                status = module.No_Network;
                return status;
            }
        }
        return status;
    }
}