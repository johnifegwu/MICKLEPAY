package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import com.mickleentityltdnigeria.mickle_pay.data.model.Merchant;

import java.util.List;

public interface MerchantEvents {

    void onMerchantDataFetched(List<Merchant> merchants);

    void onMerchantAdded(List<Merchant> merchants);

    void onMerchantUpdated(List<Merchant> merchants);

    void onMerchantRecordNotFound(Exception e);

    void onError(Exception e);
    
}
