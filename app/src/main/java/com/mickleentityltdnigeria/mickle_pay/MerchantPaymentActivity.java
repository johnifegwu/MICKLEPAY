package com.mickleentityltdnigeria.mickle_pay;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mickleentityltdnigeria.mickle_pay.dalc.ChargeDefinitionDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.ExchangeRateDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.MerchantDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.MerchantPaymentDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.WalletDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ChargeDefinitionEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeRateEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.MerchantEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.MerchantPaymentEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.WalletEvents;
import com.mickleentityltdnigeria.mickle_pay.data.model.ChargeDefinition;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;
import com.mickleentityltdnigeria.mickle_pay.data.model.Merchant;
import com.mickleentityltdnigeria.mickle_pay.data.model.MerchantPayment;
import com.mickleentityltdnigeria.mickle_pay.data.model.Wallet;
import com.mickleentityltdnigeria.mickle_pay.util.AppGlobals;
import com.mickleentityltdnigeria.mickle_pay.util.IDGenerator;
import com.mickleentityltdnigeria.mickle_pay.util.Types;
import com.mickleentityltdnigeria.mickle_pay.util.module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MerchantPaymentActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_payment);
        AppGlobals.setAppContext(this);
        FirebaseApp.initializeApp(this);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        try {
            module.checkNetwork();
            final String email = "mickle.entity.ltd@gmail.com";
            final String pwd = "76689e854e944fcdFUYI746VNMCNIF";
            mAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnFailureListener(MerchantPaymentActivity.this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //
                            Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = getIntent();
                            intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                            Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            setResult(Activity.RESULT_CANCELED, intent);
                            finish();
                        }
                    })
                    .addOnCompleteListener(MerchantPaymentActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                /* How to set the intent that started this activity
                                Uri uri = Uri.parse("mickle-pay:{merchant_id}"); // the merchant id in your app.
                                Intent payMerchant = new Intent(Intent.Action_View, uri);
                                payMerchant.putExtra("api_key", api_key);
                                payMerchant.putExtra("c_wallet_id", c_wallet_id);
                                payMerchant.putExtra("tran_amount", tran_amount);
                                payMerchant.putExtra("tran_curr", tran_curr);
                                payMerchant.putExtra("tran_desc", tran_desc);
                                payMerchant.putExtra("m_wallet_id", m_wallet_id);
                                payMerchant.putExtra("m_amount", m_amount);
                                payMerchant.putExtra("s_wallet_id", s_wallet_id);
                                payMerchant.putExtra("s_amount", s_amount);
                                */
                                Intent intent = getIntent();
                                String transactCode = Types.TransactStatus.INSUFFICIENT_BALANCE();
                                // Handle the Intent
                                assert intent != null;
                                Uri data = intent.getData();
                                String mType = data.getScheme();
                                if (mType.equals("mickle-pay")) {

                                    String m_id = data.getSchemeSpecificPart();
                                    String api_key = intent.getStringExtra("api_key");
                                    String c_wallet_id = intent.getStringExtra("c_wallet_id");
                                    double tran_amount = Double.parseDouble(intent.getStringExtra("tran_amount"));
                                    String tran_curr = intent.getStringExtra("tran_curr");
                                    String tran_desc = intent.getStringExtra("tran_desc");
                                    String m_wallet_id = intent.getStringExtra("m_wallet_id");
                                    double merchant_amount = Double.parseDouble(intent.getStringExtra("m_amount"));
                                    String s_wallet_id = intent.getStringExtra("s_wallet_id");
                                    double subMerchant_amount = Double.parseDouble(intent.getStringExtra("s_amount"));

                                    final Wallet[] merchantWallet = new Wallet[1];
                                    final Wallet[] subMerchantWallet = new Wallet[1];
                                    final Wallet[] customerWallet = new Wallet[1];
                                    //Validate API-Key
                                    MerchantDalc merchantDalc = new MerchantDalc(new MerchantEvents() {
                                        @Override
                                        public void onMerchantDataFetched(List<Merchant> merchants) {
                                            MerchantPaymentDalc merchantPaymentDalc = new MerchantPaymentDalc(new MerchantPaymentEvents() {
                                                @Override
                                                public void onPaymentSuccessful(MerchantPayment merchantPayment) {
                                                    // if all goes well do the following.
                                                    intent.putExtra("TransactStatus", Types.TransactStatus.APPROVED());
                                                    setResult(Activity.RESULT_OK, intent);
                                                    FirebaseAuth.getInstance().signOut();
                                                    finish();
                                                }

                                                @Override
                                                public void onPaymentFailed(@NonNull Exception e) {
                                                    Intent intent = getIntent();
                                                    intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                                    Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    setResult(Activity.RESULT_CANCELED, intent);
                                                    FirebaseAuth.getInstance().signOut();
                                                    finish();
                                                }

                                                @Override
                                                public void onPaymentsFetched(List<MerchantPayment> merchantPayments) {

                                                }

                                                @Override
                                                public void onPaymentNotFound(@NonNull Exception e) {
                                                    Intent intent = getIntent();
                                                    intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                                    Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    setResult(Activity.RESULT_CANCELED, intent);
                                                    FirebaseAuth.getInstance().signOut();
                                                    finish();
                                                }

                                                @Override
                                                public void onWalletDataNotFound(@NonNull Exception e) {
                                                    Intent intent = getIntent();
                                                    intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                                    Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    setResult(Activity.RESULT_CANCELED, intent);
                                                    FirebaseAuth.getInstance().signOut();
                                                    finish();
                                                }
                                            });
                                            WalletDalc customerWalletDalc = new WalletDalc(new WalletEvents() {
                                                @Override
                                                public void onWalletAdded(Wallet wallet) {

                                                }

                                                @Override
                                                public void onWalletFetched(List<Wallet> wallets) {
                                                    customerWallet[0] = wallets.get(0);
                                                    //get exchange rates based on the transaction currency
                                                    ExchangeRateDalc exchangeRateDalc = new ExchangeRateDalc(new ExchangeRateEvents() {
                                                        @Override
                                                        public void onExchangeRatesFetched(Map<String, ExchangeRate> exchangeRates) {
                                                            //get charges
                                                            ChargeDefinitionDalc chargeDefinitionDalc = new ChargeDefinitionDalc(new ChargeDefinitionEvents() {
                                                                @Override
                                                                public void onChargeDefinitionAdded(ChargeDefinition chargeDefinition) {

                                                                }

                                                                @Override
                                                                public void onChargeDefinitionUpdated(ChargeDefinition chargeDefinition) {

                                                                }

                                                                @Override
                                                                public void onChargeDefinitionFetched(List<ChargeDefinition> chargeDefinitions) {
                                                                    try {
                                                                        //collect payment
                                                                        merchantPaymentDalc.collectPayment(customerWallet[0], merchantWallet[0], subMerchantWallet[0], tran_desc, tran_curr, tran_amount, merchant_amount, subMerchant_amount, chargeDefinitions, exchangeRates, m_id, "");
                                                                    } catch (Exception e) {
                                                                        Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }

                                                                @Override
                                                                public void onChargeDefinitionNotFound(@NonNull Exception e) {

                                                                }
                                                            });
                                                            chargeDefinitionDalc.getChargeDefinitions(Types.ChargeType.CHARGE_ON_PAYMENT());
                                                        }

                                                        @Override
                                                        public void onExchangeRatesUpdated(Map<String, ExchangeRate> exchangeRates) {

                                                        }

                                                        @Override
                                                        public void onExchangeRatesNotFound(@NonNull Exception e) {

                                                        }
                                                    }, MerchantPaymentActivity.this);
                                                    //
                                                    exchangeRateDalc.getExchangeRatesByBaseCurrency(tran_curr);
                                                }

                                                @Override
                                                public void onWalletNotFound(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onWalletFailed(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onDuplicateWallet(@NonNull Exception e) {

                                                }
                                            });
                                            WalletDalc subMerchantWalletDalc = new WalletDalc(new WalletEvents() {
                                                @Override
                                                public void onWalletAdded(Wallet wallet) {

                                                }

                                                @Override
                                                public void onWalletFetched(List<Wallet> wallets) {
                                                    subMerchantWallet[0] = wallets.get(0);
                                                    customerWalletDalc.getWalletsByWalletID(c_wallet_id);
                                                }

                                                @Override
                                                public void onWalletNotFound(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onWalletFailed(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onDuplicateWallet(@NonNull Exception e) {

                                                }
                                            });
                                            WalletDalc merchantWalletDalc = new WalletDalc(new WalletEvents() {
                                                @Override
                                                public void onWalletAdded(Wallet wallet) {

                                                }

                                                @Override
                                                public void onWalletFetched(List<Wallet> wallets) {
                                                    merchantWallet[0] = wallets.get(0);
                                                    subMerchantWalletDalc.getWalletsByWalletID(s_wallet_id);
                                                }

                                                @Override
                                                public void onWalletNotFound(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onWalletFailed(@NonNull Exception e) {

                                                }

                                                @Override
                                                public void onDuplicateWallet(@NonNull Exception e) {

                                                }
                                            });
                                            merchantWalletDalc.getWalletsByWalletID(m_wallet_id);

                                        }

                                        @Override
                                        public void onMerchantAdded(List<Merchant> merchants) {

                                        }

                                        @Override
                                        public void onMerchantUpdated(List<Merchant> merchants) {

                                        }

                                        @Override
                                        public void onMerchantRecordNotFound(Exception e) {
                                            Intent intent = getIntent();
                                            intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                            Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            setResult(Activity.RESULT_CANCELED, intent);
                                            FirebaseAuth.getInstance().signOut();
                                            finish();
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                            Intent intent = getIntent();
                                            intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                            Toast.makeText(MerchantPaymentActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                            setResult(Activity.RESULT_CANCELED, intent);
                                            FirebaseAuth.getInstance().signOut();
                                            finish();
                                        }
                                    });
                                } else {
                                    intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                    Toast.makeText(MerchantPaymentActivity.this, "Invalid request.", Toast.LENGTH_SHORT).show();
                                    setResult(Activity.RESULT_CANCELED, intent);
                                    FirebaseAuth.getInstance().signOut();
                                    finish();
                                }
                            } else {
                                Intent intent = getIntent();
                                intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
                                Toast.makeText(MerchantPaymentActivity.this, "Authentication Failed.", Toast.LENGTH_SHORT).show();
                                setResult(Activity.RESULT_CANCELED, intent);
                                finish();
                            }
                            // ...
                        }
                    });
            //
        } catch (Exception e) {
            Intent intent = getIntent();
            intent.putExtra("TransactStatus", Types.TransactStatus.FAILED());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_CANCELED, intent);
            finish();
        }
    }
}