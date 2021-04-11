package com.tijani.rezaguestmvvm.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentKhordadBinding;
import com.tijani.rezaguestmvvm.model.ActivateSuites;
import com.tijani.rezaguestmvvm.model.ReservSuites;
import com.tijani.rezaguestmvvm.viewmodel.FarvardinVM;
import com.tijani.rezaguestmvvm.viewmodel.KhordadVM;
import com.tijani.rezaguestmvvm.viewmodel.SuiteVM;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentKhordad extends Fragment {
    FragmentKhordadBinding binding;
    private GridLayout gridLayoutS1, gridLayoutS2, gridLayoutS3, gridLayoutS4, gridLayoutS5, gridLayoutS6, gridLayoutS7, gridLayoutS8, gridLayoutS9, gridLayoutS10, gridLayoutS11, gridLayoutS12;
    private ImageView imgBack;
    private RelativeLayout relBtnConfirmAndCancel;
    private Button btnConfirm, btnCancel;
    private ProgressBar prg;

    //// ArrayList S1 /////
    ArrayList<Integer> idGrid_selected_s1;
    private String activityStatusS1;
    private List<Integer> suite1Reservs;
    //////
    //// ArrayList S2 /////
    ArrayList<Integer> idGrid_selected_s2;
    private String activityStatusS2;
    private List<Integer> suite2Reservs;
    //////
    //// ArrayList S3 /////
    ArrayList<Integer> idGrid_selected_s3;
    private String activityStatusS3;
    private List<Integer> suite3Reservs;
    //////
    //// ArrayList S4 /////
    ArrayList<Integer> idGrid_selected_s4;
    private String activityStatusS4;
    private List<Integer> suite4Reservs;
    //////
    //// ArrayList S5 /////
    ArrayList<Integer> idGrid_selected_s5;
    private String activityStatusS5;
    private List<Integer> suite5Reservs;
    //////
    //// ArrayList S6 /////
    ArrayList<Integer> idGrid_selected_s6;
    private String activityStatusS6;
    private List<Integer> suite6Reservs;
    //////
    //// ArrayList S7 /////
    ArrayList<Integer> idGrid_selected_s7;
    private String activityStatusS7;
    private List<Integer> suite7Reservs;
    //////
    //// ArrayList S8 /////
    ArrayList<Integer> idGrid_selected_s8;
    private String activityStatusS8;
    private List<Integer> suite8Reservs;
    //////
    //// ArrayList S9 /////
    ArrayList<Integer> idGrid_selected_s9;
    private String activityStatusS9;
    private List<Integer> suite9Reservs;
    //////
    //// ArrayList S10 /////
    ArrayList<Integer> idGrid_selected_s10;
    private String activityStatusS10;
    private List<Integer> suite10Reservs;
    //////
    //// ArrayList S11 /////
    ArrayList<Integer> idGrid_selected_s11;
    private String activityStatusS11;
    private List<Integer> suite11Reservs;
    //////
    //// ArrayList S12 /////
    ArrayList<Integer> idGrid_selected_s12;
    private String activityStatusS12;
    private List<Integer> suite12Reservs;
    //////
    private Handler handler;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_khordad,container,false);

        imgBack = binding.imgFragmentFarvardinImgBack;
        relBtnConfirmAndCancel = binding.relativeFragmentMonthsConfirmOrCancel;
        btnConfirm = binding.btnFragmentMonthsConfirm;
        btnCancel = binding.btnFragmentMonthsCancel;
        gridLayoutS1 = binding.gridLayoutDaysS1;
        gridLayoutS2 = binding.gridLayoutDaysS2;
        gridLayoutS3 = binding.gridLayoutDaysS3;
        gridLayoutS4 = binding.gridLayoutDaysS4;
        gridLayoutS5 = binding.gridLayoutDaysS5;
        gridLayoutS6 = binding.gridLayoutDaysS6;
        gridLayoutS7 = binding.gridLayoutDaysS7;
        gridLayoutS8 = binding.gridLayoutDaysS8;
        gridLayoutS9 = binding.gridLayoutDaysS9;
        gridLayoutS10 = binding.gridLayoutDaysS10;
        gridLayoutS11 = binding.gridLayoutDaysS11;
        gridLayoutS12 = binding.gridLayoutDaysS12;
        prg=binding.progressbarFragmentMonths;
        buildObjectFromLists();

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

        initReservDays();

        setOnClickGridSuites(gridLayoutS1, idGrid_selected_s1);
        setOnClickGridSuites(gridLayoutS2, idGrid_selected_s2);
        setOnClickGridSuites(gridLayoutS3, idGrid_selected_s3);
        setOnClickGridSuites(gridLayoutS4, idGrid_selected_s4);
        setOnClickGridSuites(gridLayoutS5, idGrid_selected_s5);
        setOnClickGridSuites(gridLayoutS6, idGrid_selected_s6);
        setOnClickGridSuites(gridLayoutS7, idGrid_selected_s7);
        setOnClickGridSuites(gridLayoutS8, idGrid_selected_s8);
        setOnClickGridSuites(gridLayoutS9, idGrid_selected_s9);
        setOnClickGridSuites(gridLayoutS10, idGrid_selected_s10);
        setOnClickGridSuites(gridLayoutS11, idGrid_selected_s11);
        setOnClickGridSuites(gridLayoutS12, idGrid_selected_s12);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragmentSendInformation();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelSelectDays();
            }
        });


    }

    private void goToFragmentSendInformation() {
        if (!(idGrid_selected_s1.isEmpty()) || !(idGrid_selected_s2.isEmpty()) || !(idGrid_selected_s3.isEmpty()) || !(idGrid_selected_s4.isEmpty()) || !(idGrid_selected_s5.isEmpty()) || !(idGrid_selected_s6.isEmpty()) || !(idGrid_selected_s7.isEmpty()) || !(idGrid_selected_s8.isEmpty()) || !(idGrid_selected_s9.isEmpty()) || !(idGrid_selected_s10.isEmpty()) || !(idGrid_selected_s11.isEmpty()) || !(idGrid_selected_s12.isEmpty())) {
            Bundle bundle = new Bundle();
            if (!(idGrid_selected_s1.isEmpty())) {
                Collections.sort(idGrid_selected_s1);
                bundle.putIntegerArrayList("idGrid_farvardin_S1", idGrid_selected_s1);
            }
            if (!(idGrid_selected_s2.isEmpty())) {
                Collections.sort(idGrid_selected_s2);
                bundle.putIntegerArrayList("idGrid_farvardin_S2", idGrid_selected_s2);


            }
            if (!(idGrid_selected_s3.isEmpty())) {
                Collections.sort(idGrid_selected_s3);
                bundle.putIntegerArrayList("idGrid_farvardin_S3", idGrid_selected_s3);

            }
            if (!(idGrid_selected_s4.isEmpty())) {
                Collections.sort(idGrid_selected_s4);
                bundle.putIntegerArrayList("idGrid_farvardin_S4", idGrid_selected_s4);
            }
            if (!(idGrid_selected_s5.isEmpty())) {
                Collections.sort(idGrid_selected_s5);
                bundle.putIntegerArrayList("idGrid_farvardin_S5", idGrid_selected_s5);
            }
            if (!(idGrid_selected_s6.isEmpty())) {
                Collections.sort(idGrid_selected_s6);
                bundle.putIntegerArrayList("idGrid_farvardin_S6", idGrid_selected_s6);


            }
            if (!(idGrid_selected_s7.isEmpty())) {
                Collections.sort(idGrid_selected_s7);
                bundle.putIntegerArrayList("idGrid_farvardin_S7", idGrid_selected_s7);


            }
            if (!(idGrid_selected_s8.isEmpty())) {
                Collections.sort(idGrid_selected_s8);
                bundle.putIntegerArrayList("idGrid_farvardin_S8", idGrid_selected_s8);


            }
            if (!(idGrid_selected_s9.isEmpty())) {
                Collections.sort(idGrid_selected_s9);
                bundle.putIntegerArrayList("idGrid_farvardin_S9", idGrid_selected_s9);


            }
            if (!(idGrid_selected_s10.isEmpty())) {
                Collections.sort(idGrid_selected_s10);
                bundle.putIntegerArrayList("idGrid_farvardin_S10", idGrid_selected_s10);


            }
            if (!(idGrid_selected_s11.isEmpty())) {
                Collections.sort(idGrid_selected_s11);
                bundle.putIntegerArrayList("idGrid_farvardin_S11", idGrid_selected_s11);


            }
            if (!(idGrid_selected_s12.isEmpty())) {
                Collections.sort(idGrid_selected_s12);
                bundle.putIntegerArrayList("idGrid_farvardin_S12", idGrid_selected_s12);


            }


            Navigation.findNavController(btnConfirm).navigate(R.id.action_fragmentKhordad_to_fragmentSendInformationKhordad, bundle);

        }
    }
    private void initReservDays() {
        prg.setVisibility(View.VISIBLE);
        checkActivateSuites();

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        MutableLiveData<List<ReservSuites>> liveData = new ViewModelProvider(requireActivity()).get(KhordadVM.class).getReservSuiteLiveData();
                        liveData.observe(requireActivity(), new Observer<List<ReservSuites>>() {
                            @Override
                            public void onChanged(List<ReservSuites> reservSuites) {
                                prg.setVisibility(View.GONE);

                                for (ReservSuites reservSuites1 : reservSuites) {

                                    String s1 = reservSuites1.getSuite1();
                                    String s2 = reservSuites1.getSuite2();
                                    String s3 = reservSuites1.getSuite3();
                                    String s4 = reservSuites1.getSuite4();
                                    String s5 = reservSuites1.getSuite5();
                                    String s6 = reservSuites1.getSuite6();
                                    String s7 = reservSuites1.getSuite7();
                                    String s8 = reservSuites1.getSuite8();
                                    String s9 = reservSuites1.getSuite9();
                                    String s10 = reservSuites1.getSuite10();
                                    String s11 = reservSuites1.getSuite11();
                                    String s12 = reservSuites1.getSuite12();

                                    generateReservationDeysList(s1, suite1Reservs);
                                    generateReservationDeysList(s2, suite2Reservs);
                                    generateReservationDeysList(s3, suite3Reservs);
                                    generateReservationDeysList(s4, suite4Reservs);
                                    generateReservationDeysList(s5, suite5Reservs);
                                    generateReservationDeysList(s6, suite6Reservs);
                                    generateReservationDeysList(s7, suite7Reservs);
                                    generateReservationDeysList(s8, suite8Reservs);
                                    generateReservationDeysList(s9, suite9Reservs);
                                    generateReservationDeysList(s10, suite10Reservs);
                                    generateReservationDeysList(s11, suite11Reservs);
                                    generateReservationDeysList(s12, suite12Reservs);


                                }


                                highlightingReservDays(activityStatusS1, suite1Reservs, gridLayoutS1);
                        highlightingReservDays(activityStatusS2, suite2Reservs, gridLayoutS2);
                        highlightingReservDays(activityStatusS3, suite3Reservs, gridLayoutS3);
                        highlightingReservDays(activityStatusS4, suite4Reservs, gridLayoutS4);
                        highlightingReservDays(activityStatusS5, suite5Reservs, gridLayoutS5);
                        highlightingReservDays(activityStatusS6, suite6Reservs, gridLayoutS6);
                        highlightingReservDays(activityStatusS7, suite7Reservs, gridLayoutS7);
                        highlightingReservDays(activityStatusS8, suite8Reservs, gridLayoutS8);
                        highlightingReservDays(activityStatusS9, suite9Reservs, gridLayoutS9);
                        highlightingReservDays(activityStatusS10, suite10Reservs, gridLayoutS10);
                        highlightingReservDays(activityStatusS11, suite11Reservs, gridLayoutS11);
                        highlightingReservDays(activityStatusS12, suite12Reservs, gridLayoutS12);
                    }
                });


            }


        });
    }





    private void cancelSelectDays() {
        relBtnConfirmAndCancel.setVisibility(View.GONE);
        if (!(idGrid_selected_s1.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s1.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS1.getChildAt(idGrid_selected_s1.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s1.clear();
        }

        if (!(idGrid_selected_s2.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s2.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS2.getChildAt(idGrid_selected_s2.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s2.clear();
        }

        if (!(idGrid_selected_s3.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s3.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS3.getChildAt(idGrid_selected_s3.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s3.clear();
        }

        if (!(idGrid_selected_s4.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s4.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS4.getChildAt(idGrid_selected_s4.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s4.clear();
        }

        if (!(idGrid_selected_s5.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s5.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS5.getChildAt(idGrid_selected_s5.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s5.clear();
        }

        if (!(idGrid_selected_s6.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s6.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS6.getChildAt(idGrid_selected_s6.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s6.clear();
        }

        if (!(idGrid_selected_s7.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s7.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS7.getChildAt(idGrid_selected_s7.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s7.clear();
        }
        if (!(idGrid_selected_s8.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s8.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS8.getChildAt(idGrid_selected_s8.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s8.clear();
        }
        if (!(idGrid_selected_s9.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s9.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS9.getChildAt(idGrid_selected_s9.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s9.clear();
        }
        if (!(idGrid_selected_s10.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s10.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS10.getChildAt(idGrid_selected_s10.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s10.clear();
        }
        if (!(idGrid_selected_s11.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s11.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS11.getChildAt(idGrid_selected_s11.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s11.clear();
        }
        if (!(idGrid_selected_s12.isEmpty())) {
            for (int i = 0; i < idGrid_selected_s12.size(); i++) {
                final CardView cardView = (CardView) gridLayoutS12.getChildAt(idGrid_selected_s12.get(i));
                cardView.setCardBackgroundColor(Color.WHITE);
            }
            idGrid_selected_s12.clear();
        }
    }

    private void checkActivateSuites() {
        MutableLiveData<ActivateSuites> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).checkActivateSuite();
        liveData.observe(requireActivity(), new Observer<ActivateSuites>() {
            @Override
            public void onChanged(ActivateSuites activateSuites) {

                activityStatusS1 = activateSuites.getSuite1();
                activityStatusS2 = activateSuites.getSuite2();
                activityStatusS3 = activateSuites.getSuite3();
                activityStatusS4 = activateSuites.getSuite4();
                activityStatusS5 = activateSuites.getSuite5();
                activityStatusS6 = activateSuites.getSuite6();
                activityStatusS7 = activateSuites.getSuite7();
                activityStatusS8 = activateSuites.getSuite8();
                activityStatusS9 = activateSuites.getSuite9();
                activityStatusS10 = activateSuites.getSuite10();
                activityStatusS11 = activateSuites.getSuite11();
                activityStatusS12 = activateSuites.getSuite12();
            }
        });
    }

    private void buildObjectFromLists() {

        idGrid_selected_s1 = new ArrayList<>();
        idGrid_selected_s2 = new ArrayList<>();
        idGrid_selected_s3 = new ArrayList<>();
        idGrid_selected_s4 = new ArrayList<>();
        idGrid_selected_s5 = new ArrayList<>();
        idGrid_selected_s6 = new ArrayList<>();
        idGrid_selected_s7 = new ArrayList<>();
        idGrid_selected_s8 = new ArrayList<>();
        idGrid_selected_s9 = new ArrayList<>();
        idGrid_selected_s10 = new ArrayList<>();
        idGrid_selected_s11 = new ArrayList<>();
        idGrid_selected_s12 = new ArrayList<>();

        suite1Reservs = new ArrayList<>();
        suite2Reservs = new ArrayList<>();
        suite3Reservs = new ArrayList<>();
        suite4Reservs = new ArrayList<>();
        suite5Reservs = new ArrayList<>();
        suite6Reservs = new ArrayList<>();
        suite7Reservs = new ArrayList<>();
        suite8Reservs = new ArrayList<>();
        suite9Reservs = new ArrayList<>();
        suite10Reservs = new ArrayList<>();
        suite11Reservs = new ArrayList<>();
        suite12Reservs = new ArrayList<>();

        handler = new Handler();

    }
    private void generateReservationDeysList(String reservation, List<Integer> list) {
        if (reservation != null) {
            String[] deys = reservation.split(",");

            for (int i = 0; i < deys.length; i++) {
                list.add(Integer.valueOf(deys[i]));
            }
        }


    }

    private void highlightingReservDays(String activityStatus, List<Integer> suiteReserveList, GridLayout gridLayout) {

        if (activityStatus.equals("1")) {
            for (int i = 0; i < suiteReserveList.size(); i++) {
                final CardView cardView = (CardView) gridLayout.getChildAt(suiteReserveList.get(i));
                cardView.setCardBackgroundColor(Color.parseColor("#FF80AB"));
            }
        } else {
            for (int i = 0; i < gridLayout.getChildCount(); i++) {
                final CardView cardView = (CardView) gridLayout.getChildAt(i);
                cardView.setCardBackgroundColor(Color.parseColor("#FF80AB"));

            }
        }
    }

    private void setOnClickGridSuites(GridLayout gridLayout, ArrayList<Integer> idGridSelectedSuite) {
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            final int id = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (cardView.getCardBackgroundColor().getDefaultColor() == Color.parseColor("#FF80AB")) {
                        Toast.makeText(getContext(), "قبلا رزرو شده است", Toast.LENGTH_SHORT).show();
                    } else {

                        if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                            cardView.setCardBackgroundColor(Color.parseColor("#66BB6A"));
                            idGridSelectedSuite.add(id);

                            if (relBtnConfirmAndCancel.getVisibility() == View.GONE) {
                                relBtnConfirmAndCancel.setVisibility(View.VISIBLE);

                            }

                        } else {
                            cardView.setCardBackgroundColor(Color.WHITE);
                            idGridSelectedSuite.remove(Integer.valueOf(id));

                            if (idGrid_selected_s5.isEmpty() && idGrid_selected_s2.isEmpty() && idGrid_selected_s1.isEmpty() && idGrid_selected_s3.isEmpty() && idGrid_selected_s4.isEmpty() && idGrid_selected_s6.isEmpty() && idGrid_selected_s7.isEmpty() && idGrid_selected_s8.isEmpty() && idGrid_selected_s9.isEmpty() && idGrid_selected_s10.isEmpty() && idGrid_selected_s11.isEmpty() && idGrid_selected_s12.isEmpty()) {
                                relBtnConfirmAndCancel.setVisibility(View.GONE);
                            }
                        }

                    }

                }
            });
        }
    }



}