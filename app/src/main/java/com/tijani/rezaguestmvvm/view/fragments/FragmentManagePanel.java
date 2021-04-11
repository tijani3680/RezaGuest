package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentManagePanelBinding;

public class FragmentManagePanel extends Fragment {
    FragmentManagePanelBinding binding;
    private ImageView imgBack;
    private CardView cardAddOperator, cardOperators, cardActivateSuites, cardSuitesPrice, cardActivateMonths, cardReports,cardResetApp;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_manage_panel, container, false);
        imgBack = binding.imgFragmentManagePanelImgBack;
        cardAddOperator = binding.cardFragmentManagePanelAddOperator;
        cardOperators = binding.cardFragmentManagePanelExistOperators;
        cardActivateSuites = binding.cardFragmentManagePanelActivateSuites;
        cardSuitesPrice = binding.cardFragmentManagePanelPriceSuites;
        cardActivateMonths = binding.cardFragmentManagePanelActivateMonths;
        cardReports = binding.cardFragmentManagePanelReport;
        cardResetApp = binding.cardFragmentManagePanelResetApp;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imgBack).popBackStack();
            }
        });

        cardAddOperator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardAddOperator).navigate(R.id.action_fragmentManagePanel_to_fragmentCreateOperator);

            }
        });
        cardOperators.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardOperators).navigate(R.id.action_fragmentManagePanel_to_fragmentOperators);
            }
        });
        cardActivateSuites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardActivateSuites).navigate(R.id.action_fragmentManagePanel_to_fragmentActivateSuites);
            }
        });
        cardSuitesPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardSuitesPrice).navigate(R.id.action_fragmentManagePanel_to_fragmentSuitesPrice);
            }
        });
        cardActivateMonths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardActivateMonths).navigate(R.id.action_fragmentManagePanel_to_fragmentActivateMonths);
            }
        });

        cardReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardReports).navigate(R.id.action_fragmentManagePanel_to_fragmentReports);
            }
        });

        cardResetApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(cardResetApp).navigate(R.id.action_fragmentManagePanel_to_fragmentReset);
            }
        });
    }
}