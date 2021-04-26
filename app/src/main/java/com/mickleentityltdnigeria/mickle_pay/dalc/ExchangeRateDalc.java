package com.mickleentityltdnigeria.mickle_pay.dalc;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeRateEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeResult;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
import com.mickleentityltdnigeria.mickle_pay.data.model.Rate;
import com.mickleentityltdnigeria.mickle_pay.util.CurrentUser;
import com.mickleentityltdnigeria.mickle_pay.util.DBReferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExchangeRateDalc {

    private FirebaseDatabase database;
    private final DatabaseReference exchangeRateDB;

    private final ExchangeRateEvents exchangeRateEvents;
    private ExchangeResult exchangeResut;
    private Context mContext;

    public ExchangeRateDalc(ExchangeRateEvents exchangeRateEvents, Context mContext) {
        this.exchangeRateEvents = exchangeRateEvents;
        this.database = FirebaseDatabase.getInstance();
        this.exchangeRateDB = database.getReference(DBReferences.EXCHANGE_RATES());
        this.mContext = mContext;
        AndroidNetworking.initialize(mContext);
    }

    public void updateCurrencies(){
        Map<String, ExchangeRate> result = new HashMap<>();
        String[] currencies = CountryDalc.getCurrencyCodes();
        int count = currencies.length;
        for(String baseCurrency: currencies){
            if(!result.containsKey(baseCurrency)){
                Map<String, Rate> rates = new HashMap<>();
                exchangeResut = new ExchangeResult() {
                    @Override
                    public void onResultArrived(double bidPrice, String baseCurrency, String quoteCurrency) {
                        rates.put(quoteCurrency, new Rate(quoteCurrency, bidPrice));
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                };
                String rateApiURL = "https://www1.oanda.com/rates/api/v2/rates/spot.json?api_key=pU57Zp1Ib6vRcUHIXd2denbU&base=" +
                        "" + baseCurrency;
                for(String quoteCurrency: currencies){
                    if(!quoteCurrency.equals(baseCurrency)){
                        rateApiURL += "&quote=" + quoteCurrency;
                    }
                }
                //get quotes
                try{
                    AndroidNetworking.get(rateApiURL)
                            .setPriority(Priority.HIGH)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        int finalCount = response.getJSONArray("quotes").length();
                                        for(int i = 0; i < finalCount; i++){
                                            String quoteCurrency = response.getJSONArray("quotes").getJSONObject(i).getString("quote_currency");
                                            double bidPrice = Double.parseDouble(response.getJSONArray("quotes").getJSONObject(i).getString("bid"));
                                            rates.put(quoteCurrency, new Rate(quoteCurrency, bidPrice));
                                        }
                                        Timestamp ts = new Timestamp(new Date().getTime());
                                        ExchangeRate exchangeRate = new ExchangeRate(ts, CurrentUser.userID, baseCurrency , rates);
                                        exchangeRateDB.child(baseCurrency).setValue(exchangeRate).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                result.put(baseCurrency, exchangeRate);
                                            }
                                        });
                                    } catch (JSONException e) {
                                        Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(ANError anError) {
                                    Toast.makeText(mContext, anError.getErrorDetail(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch (Exception e){
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            exchangeRateEvents.onExchangeRatesUpdated(result);
        }
    }

    public void getExchangeRate(String baseCurrency, String quoteCurrency, ExchangeResult mExchangeResult) {
        try {
            String rateApiURL = "https://www1.oanda.com/rates/api/v2/rates/spot.json?api_key=pU57Zp1Ib6vRcUHIXd2denbU&base=" +
                    "{baseCurrency}&quote={quoteCurrency}";
            AndroidNetworking.get(rateApiURL)
                    .addPathParameter("baseCurrency", baseCurrency)
                    .addPathParameter("quoteCurrency", quoteCurrency)
                    .setPriority(Priority.HIGH)
                    .build()
                    .getAsJSONObject(new JSONObjectRequestListener() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                double bidPrice = Double.parseDouble(response.getJSONArray("quotes").getJSONObject(0).getString("bid"));
                                mExchangeResult.onResultArrived(bidPrice, baseCurrency, quoteCurrency);
                            } catch (JSONException e) {
                                mExchangeResult.onError(e);
                            }
                        }

                        @Override
                        public void onError(ANError anError) {
                            mExchangeResult.onError(new Exception(anError.getErrorDetail()));
                        }
                    });
        }catch (Exception e){
            mExchangeResult.onError(e);
        }
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
