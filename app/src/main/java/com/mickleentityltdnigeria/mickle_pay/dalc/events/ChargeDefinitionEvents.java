package com.mickleentityltdnigeria.mickle_pay.dalc.events;

import androidx.annotation.NonNull;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;

import java.util.List;

public interface ChargeDefinitionEvents {

    void onChargeDefinitionAdded(ChargeDefinition chargeDefinition);

    void onChargeDefinitionUpdated(ChargeDefinition chargeDefinition);

    void onChargeDefinitionFetched(List<ChargeDefinition> chargeDefinitions);

    void onChargeDefinitionNotFound(@NonNull Exception e);

}
