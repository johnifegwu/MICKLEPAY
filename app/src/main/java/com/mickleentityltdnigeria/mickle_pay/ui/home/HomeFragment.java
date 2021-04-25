package com.mickleentityltdnigeria.mickle_pay.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mickleentityltdnigeria.mickle_pay.R;
import com.mickleentityltdnigeria.mickle_pay.dalc.ExchangeRateDalc;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeRateEvents;
import com.mickleentityltdnigeria.mickle_pay.dalc.events.ExchangeResult;
import com.mickleentityltdnigeria.mickle_pay.data.model.ExchangeRate;

import java.util.Map;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }
}