package com.mickleentityltdnigeria.mickle_pay.dalc;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeRateEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
import com.mickleentityltdnigeria.mickle_pay.data.model.Rate;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;
import com.mickleentityltdnigeria.mickle_pay.data.model.WalletTransactions;
import com.mickleentityltdnigeria.mickle_pay.util.CurrentUser;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;
import com.mickleentityltdnigeria.mickle_pay.util.Types;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExchangeRateDalc {

    private FirebaseDatabase database;
    private final DatabaseReference exchangeRateDB;

    private final ExchangeRateEvents exchangeRateEvents;

    public ExchangeRateDalc(ExchangeRateEvents exchangeRateEvents) {
        this.exchangeRateEvents = exchangeRateEvents;
        this.database = FirebaseDatabase.getInstance();
        this.exchangeRateDB = database.getReference(DBReferences.EXCHANGE_RATES());
    }

    public void updateCurrencies(){
        Map<String, ExchangeRate> result = new HashMap<>();
        String[] currencies = CountryDalc.getCurrencyCodes();
        for(String c: currencies){
            if(!result.containsKey(c)){
                Map<String, Rate> rates = new HashMap<>();
                for(String s: currencies){
                    if(!s.equals(c)){
                        rates.put(s, new Rate(s, getExchangeRate(c,s)));
                    }
                }
                Timestamp ts = new Timestamp(new Date().getTime());
                ExchangeRate exchangeRate = new ExchangeRate(ts, CurrentUser.userID, c,rates);
                exchangeRateDB.child(c).setValue(exchangeRate).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        result.put(c, exchangeRate);
                    }
                });
            }
            exchangeRateEvents.onExchangeRatesUpdated(result);
        }
    }

    private double getExchangeRate(String fromCurrency, String toCurrency){
        double result = 0.0;

        return  result;
    }

    public void getExchangeRates(){
        Map<String, ExchangeRate> result = new HashMap<>();
        ValueEventListener onDataChangedListener1 = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    if (snapshot.exists()) {
                        //
                        if (snapshot.hasChildren()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                ExchangeRate exchangeRate = userSnapshot.getValue(ExchangeRate.class);
                                assert exchangeRate != null;
                                result.put(exchangeRate.getCurrencyCode(), exchangeRate);
                            }
                            exchangeRateEvents.onExchangeRatesFetched(result);
                        }
                    }
                } else {
                    exchangeRateEvents.onExchangeRatesNotFound(new Exception("No rates available."));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                exchangeRateEvents.onExchangeRatesNotFound(new Exception(error.getMessage()));
            }
        };
        Query query1 = FirebaseDatabase.getInstance().getReference(DBReferences.EXCHANGE_RATES())
                .orderByChild("currencyCode");
        query1.addListenerForSingleValueEvent(onDataChangedListener1);

    }


}
