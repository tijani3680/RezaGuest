package com.tijani.rezaguestmvvm.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentSendInformationKhordadBinding;
import com.tijani.rezaguestmvvm.databinding.FragmentSendInformationTirBinding;
import com.tijani.rezaguestmvvm.model.PriceSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.viewmodel.CustomerVM;
import com.tijani.rezaguestmvvm.viewmodel.KhordadVM;
import com.tijani.rezaguestmvvm.viewmodel.SuiteVM;
import com.tijani.rezaguestmvvm.viewmodel.TirVM;

import java.util.ArrayList;
import java.util.List;

public class FragmentSendInformationTir extends Fragment {
    FragmentSendInformationTirBinding binding;
    private EditText edtFullName, edtPhone, edtShenaseh, edtPriceDaryafti, edtShomarehFishPardakhti;
    private ImageView imgAddNafaratAvaliyeh, imgAddNafaratMazad, imgMinesNafaratAvaliyeh, imgMinesNafaratMazad;
    private TextView txtNafaratAvaliyeh, txtNafaratMazad, txtFinalPrice;
    private AppCompatCheckBox chkSpacialCustomer;
    private ProgressBar prgCalculatePrice, prgSaveData;
    private Button btnCalculatePrice, btnSaveData;
    private ImageView imgBack;

    private LinearLayout linearDaysSelected;
    private LinearLayout linearLayoutDaysSelectedS1, linearLayoutDaysSelectedS2, linearLayoutDaysSelectedS3, linearLayoutDaysSelectedS4, linearLayoutDaysSelectedS5,
            linearLayoutDaysSelectedS6, linearLayoutDaysSelectedS7, linearLayoutDaysSelectedS8, linearLayoutDaysSelectedS9, linearLayoutDaysSelectedS10, linearLayoutDaysSelectedS11, linearLayoutDaysSelectedS12;

    private TextView txtDaysSelectedS1, txtDaysSelectedS2, txtDaysSelectedS3, txtDaysSelectedS4, txtDaysSelectedS5, txtDaysSelectedS6, txtDaysSelectedS7, txtDaysSelectedS8,
            txtDaysSelectedS9, txtDaysSelectedS10, txtDaysSelectedS11, txtDaysSelectedS12;

    private TextView txtPriceDaysSelectedS1, txtPriceDaysSelectedS2, txtPriceDaysSelectedS3, txtPriceDaysSelectedS4, txtPriceDaysSelectedS5, txtPriceDaysSelectedS6, txtPriceDaysSelectedS7, txtPriceDaysSelectedS8,
            txtPriceDaysSelectedS9, txtPriceDaysSelectedS10, txtPriceDaysSelectedS11, txtPriceDaysSelectedS12;


    private List<Integer> deyListS1, deyListS2, deyListS3, deyListS4, deyListS5, deyListS6, deyListS7, deyListS8, deyListS9, deyListS10, deyListS11, deyListS12;
    private int nafaratAvaliyeh = 0;
    private int nafaratMazad = 0;
    int finalTotalPrice = 0;


    private List<Integer> specialPriceList;
    private List<Integer> priceList;

    private List<Integer> extraSpecialPriceList;
    private List<Integer> extraPriceList;

    private CardView btnConfirm, btnCancel;
    private int coastReceived =0;
    private   String shomarehFish="";
    private Boolean checkPrice = false;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      binding   = DataBindingUtil.inflate(inflater,R.layout.fragment_send_information_tir,container,false);
      findViews(binding);
      return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        specialPriceList = new ArrayList<>();
        extraSpecialPriceList = new ArrayList<>();
        priceList = new ArrayList<>();
        extraPriceList = new ArrayList<>();
        getMyArgument();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imgBack).popBackStack();
            }
        });

        imgAddNafaratAvaliyeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setCustomAnimation(Techniques.Flash,300L,txtNafaratAvaliyeh);

                nafaratAvaliyeh++;
                txtNafaratAvaliyeh.setText(String.valueOf(nafaratAvaliyeh));
            }
        });

        imgMinesNafaratAvaliyeh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setCustomAnimation(Techniques.Flash,300L,txtNafaratAvaliyeh);

                if (nafaratAvaliyeh > 0) {
                    nafaratAvaliyeh--;
                    txtNafaratAvaliyeh.setText(String.valueOf(nafaratAvaliyeh));

                }
            }
        });

        imgAddNafaratMazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setCustomAnimation(Techniques.Flash,300L,txtNafaratMazad);

                nafaratMazad++;
                txtNafaratMazad.setText(String.valueOf(nafaratMazad));
                if (checkPrice){
                    checkPrice =false;
                }


            }
        });

        imgMinesNafaratMazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyUtils.setCustomAnimation(Techniques.Flash,300L,txtNafaratMazad);

                if (nafaratMazad > 0) {
                    nafaratMazad--;
                    txtNafaratMazad.setText(String.valueOf(nafaratMazad));
                }

                if (checkPrice){
                    checkPrice =false;
                }
            }
        });

        chkSpacialCustomer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyUtils.setCustomAnimation(Techniques.Flash,300L,chkSpacialCustomer);
                if (isChecked){
                    if (checkPrice){
                        checkPrice=false;

                    }
                }else {
                    if (checkPrice){
                        checkPrice = false;
                    }
                }

            }
        });




        btnCalculatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyUtils.setCustomAnimation(Techniques.Tada,300L,btnCalculatePrice);

                getSuitePrice();
                linearDaysSelected.setVisibility(View.VISIBLE);
            }
        });

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyUtils.setCustomAnimation(Techniques.Pulse,300L,btnSaveData);

                String fullName = edtFullName.getText().toString();
                String phone = edtPhone.getText().toString();
                String customerId = edtShenaseh.getText().toString();
                String coastDaryafty = edtPriceDaryafti.getText().toString();
                String fishNumber = edtShomarehFishPardakhti.getText().toString();


                String reservistPerson = MySharedPreferences.readShenaseh(getContext());

                String resSuite1 = buildString(deyListS1);
                String resSuite2 = buildString(deyListS2);
                String resSuite3 = buildString(deyListS3);
                String resSuite4 = buildString(deyListS4);
                String resSuite5 = buildString(deyListS5);
                String resSuite6 = buildString(deyListS6);
                String resSuite7 = buildString(deyListS7);
                String resSuite8 = buildString(deyListS8);
                String resSuite9 = buildString(deyListS9);
                String resSuite10 = buildString(deyListS10);
                String resSuite11 = buildString(deyListS11);
                String resSuite12 = buildString(deyListS12);

                if (!coastDaryafty.isEmpty()){
                    coastReceived = Integer.parseInt(coastDaryafty);
                }else {
                    coastReceived =0;
                }


                if (!fishNumber.isEmpty()){
                    shomarehFish=fishNumber;
                }else {
                    shomarehFish ="";
                }


                if (fullName.isEmpty() && phone.isEmpty() && customerId.isEmpty()) {
                    Toast.makeText(getContext(), "پر کردن تمامی فیلدها الزامی است!", Toast.LENGTH_SHORT).show();
                    edtFullName.setError("");
                    edtPhone.setError("");
                    edtShenaseh.setError("");
                } else if (fullName.isEmpty() && phone.isEmpty()) {
                    Toast.makeText(getContext(), "پر کردن تمامی فیلدها الزامی است!", Toast.LENGTH_SHORT).show();
                    edtFullName.setError("");
                    edtPhone.setError("");
                } else if (fullName.isEmpty() && customerId.isEmpty()) {
                    Toast.makeText(getContext(), "پر کردن تمامی فیلدها الزامی است!", Toast.LENGTH_SHORT).show();
                    edtFullName.setError("");
                    edtShenaseh.setError("");
                } else if (phone.isEmpty() && customerId.isEmpty()) {
                    Toast.makeText(getContext(), "پر کردن تمامی فیلدها الزامی است!", Toast.LENGTH_SHORT).show();
                    edtPhone.setError("");
                    edtShenaseh.setError("");
                } else if (fullName.isEmpty()) {
                    Toast.makeText(getContext(), "لطفا نام و نام خانوادگی را وارد کنید!", Toast.LENGTH_SHORT).show();
                    edtFullName.setError("");
                } else if (phone.isEmpty()) {
                    Toast.makeText(getContext(), "لطفا شماره همراه را وارد کنید!", Toast.LENGTH_SHORT).show();
                    edtPhone.setError("");
                } else if (customerId.isEmpty()) {
                    Toast.makeText(getContext(), "لطفا شناسه را وارد کنید!", Toast.LENGTH_SHORT).show();
                    edtShenaseh.setError("");
                } else if (nafaratAvaliyeh == 0) {
                    Toast.makeText(getContext(), "تعداد نفرات اولیه مشخص نشده است!", Toast.LENGTH_SHORT).show();
                } else {


                    if (checkPrice){
                        checkConditionCustomer(fullName, phone, customerId, nafaratAvaliyeh, nafaratMazad, finalTotalPrice, coastReceived, shomarehFish, reservistPerson, resSuite1, resSuite2, resSuite3, resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12);

                    }else {
                        Toast.makeText(getContext(), "کلید محاسبه هزینه را بفشارید تا هزینه اعمال شود!", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }

    private String parcReservDays(List<Integer> list) {
        StringBuilder builder = new StringBuilder();
        String deys = null;
        for (int i = 0; i < list.size(); i++) {
            String dey = MyUtils.number2persian(String.valueOf(list.get(i) + 1)) + " " + "تیر   ";
            deys = builder.append(dey).toString();
        }

        return deys;
    }


    private void findViews(FragmentSendInformationTirBinding binding) {
        imgBack = binding.imgFragmentSendInformationFarvardinImgBack;
        edtFullName = binding.edtFragmentSendInformationEdtFullName;
        edtPhone = binding.edtFragmentSendInformationEdtPhone;
        edtShenaseh = binding.edtFragmentSendInformationEdtIdPerson;
        edtPriceDaryafti = binding.edtFragmentSendInformationEdtPriceRecived;
        edtShomarehFishPardakhti = binding.edtFragmentSendInformationEdtShomarehFishPardakhti;

        imgAddNafaratAvaliyeh = binding.imgFragmentSendInformationImgPlusNafaratAvaliyeh;
        imgMinesNafaratAvaliyeh = binding.imgFragmentSendInformationImgMinesNafaratAvaliyeh;

        imgAddNafaratMazad = binding.imgFragmentSendInformationImgPlusNafaratMazad;
        imgMinesNafaratMazad = binding.imgFragmentSendInformationImgMinesNafaratMazad;

        txtNafaratAvaliyeh = binding.txtFragmentSendInformationValueTextNafaratAvaliyeh;
        txtNafaratMazad = binding.txtFragmentSendInformationValueTextNafaratMazad;

        chkSpacialCustomer = binding.chkFragmentSendInformationCheckBoxSpatialCustomer;

        prgCalculatePrice = binding.prgFragmentSendInformationProgressBarCalculatePrice;
        prgSaveData = binding.prgFragmentSendInformationProgressBarSaveData;

        btnCalculatePrice = binding.btnFragmentSendInformationBtnCalculatePrice;
        btnSaveData = binding.btnFragmentSendInformationBtnSaveReservation;

        linearDaysSelected = binding.lilFragmentSendInformationLinearDaysSelected;

        linearLayoutDaysSelectedS1 = binding.lilFragmentSendInformationLinearDaysSelectedSuite1;
        linearLayoutDaysSelectedS2 = binding.lilFragmentSendInformationLinearDaysSelectedSuite2;
        linearLayoutDaysSelectedS3 = binding.lilFragmentSendInformationLinearDaysSelectedSuite3;
        linearLayoutDaysSelectedS4 = binding.lilFragmentSendInformationLinearDaysSelectedSuite4;
        linearLayoutDaysSelectedS5 = binding.lilFragmentSendInformationLinearDaysSelectedSuite5;
        linearLayoutDaysSelectedS6 = binding.lilFragmentSendInformationLinearDaysSelectedSuite6;
        linearLayoutDaysSelectedS7 = binding.lilFragmentSendInformationLinearDaysSelectedSuite7;
        linearLayoutDaysSelectedS8 = binding.lilFragmentSendInformationLinearDaysSelectedSuite8;
        linearLayoutDaysSelectedS9 = binding.lilFragmentSendInformationLinearDaysSelectedSuite9;
        linearLayoutDaysSelectedS10 = binding.lilFragmentSendInformationLinearDaysSelectedSuite10;
        linearLayoutDaysSelectedS11 = binding.lilFragmentSendInformationLinearDaysSelectedSuite11;
        linearLayoutDaysSelectedS12 = binding.lilFragmentSendInformationLinearDaysSelectedSuite12;

        txtDaysSelectedS1 = binding.txtFragmentSendInformationTxtDaysSelectedSuite1;
        txtDaysSelectedS2 = binding.txtFragmentSendInformationTxtDaysSelectedSuite2;
        txtDaysSelectedS3 = binding.txtFragmentSendInformationTxtDaysSelectedSuite3;
        txtDaysSelectedS4 = binding.txtFragmentSendInformationTxtDaysSelectedSuite4;
        txtDaysSelectedS5 = binding.txtFragmentSendInformationTxtDaysSelectedSuite5;
        txtDaysSelectedS6 = binding.txtFragmentSendInformationTxtDaysSelectedSuite6;
        txtDaysSelectedS7 = binding.txtFragmentSendInformationTxtDaysSelectedSuite7;
        txtDaysSelectedS8 = binding.txtFragmentSendInformationTxtDaysSelectedSuite8;
        txtDaysSelectedS9 = binding.txtFragmentSendInformationTxtDaysSelectedSuite9;
        txtDaysSelectedS10 = binding.txtFragmentSendInformationTxtDaysSelectedSuite10;
        txtDaysSelectedS11 = binding.txtFragmentSendInformationTxtDaysSelectedSuite11;
        txtDaysSelectedS12 = binding.txtFragmentSendInformationTxtDaysSelectedSuite12;


        txtPriceDaysSelectedS1 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite1;
        txtPriceDaysSelectedS2 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite2;
        txtPriceDaysSelectedS3 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite3;
        txtPriceDaysSelectedS4 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite4;
        txtPriceDaysSelectedS5 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite5;
        txtPriceDaysSelectedS6 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite6;
        txtPriceDaysSelectedS7 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite7;
        txtPriceDaysSelectedS8 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite8;
        txtPriceDaysSelectedS9 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite9;
        txtPriceDaysSelectedS10 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite10;
        txtPriceDaysSelectedS11 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite11;
        txtPriceDaysSelectedS12 = binding.txtFragmentSendInformationTxtPriceDaysSelectedSuite12;

        txtFinalPrice = binding.txtFragmentSendInformationTxtFinalPrice;


    }

    private void sendData(String fullName, String phone, String customerId, int nafaratAvaliyeh, int nafaratMazad, int finalCoast, int coastReceived,
                          String numberFish, String reservistPerson, String resSuite1, String resSuite2, String resSuite3, String resSuite4, String resSuite5,
                          String resSuite6, String resSuite7, String resSuite8, String resSuite9, String resSuite10, String resSuite11, String resSuite12) {


        prgSaveData.setVisibility(View.VISIBLE);
        TirVM viewModel = new ViewModelProvider(requireActivity()).get(TirVM.class);
        viewModel.saveReserv(fullName, phone, customerId, nafaratAvaliyeh, nafaratMazad, finalTotalPrice, coastReceived, numberFish, reservistPerson, resSuite1, resSuite2, resSuite3, resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12);

        viewModel.saveReservLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                prgSaveData.setVisibility(View.GONE);
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "اطلاعات با موفقییت ذخیره شد", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(btnSaveData).popBackStack();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }






    private void getMyArgument() {
        assert getArguments() != null;
        deyListS1 = getArguments().getIntegerArrayList("idGrid_farvardin_S1");
        deyListS2 = getArguments().getIntegerArrayList("idGrid_farvardin_S2");
        deyListS3 = getArguments().getIntegerArrayList("idGrid_farvardin_S3");
        deyListS4 = getArguments().getIntegerArrayList("idGrid_farvardin_S4");
        deyListS5 = getArguments().getIntegerArrayList("idGrid_farvardin_S5");
        deyListS6 = getArguments().getIntegerArrayList("idGrid_farvardin_S6");
        deyListS7 = getArguments().getIntegerArrayList("idGrid_farvardin_S7");
        deyListS8 = getArguments().getIntegerArrayList("idGrid_farvardin_S8");
        deyListS9 = getArguments().getIntegerArrayList("idGrid_farvardin_S9");
        deyListS10 = getArguments().getIntegerArrayList("idGrid_farvardin_S10");
        deyListS11 = getArguments().getIntegerArrayList("idGrid_farvardin_S11");
        deyListS12 = getArguments().getIntegerArrayList("idGrid_farvardin_S12");

    }


    @SuppressLint("SetTextI18n")
    private void initTextViewDaysSelected() {
        int nafaratMazad = Integer.parseInt(txtNafaratMazad.getText().toString());
        int totalPriceSuite1 = 0;
        int totalPriceSuite2 = 0;
        int totalPriceSuite3 = 0;
        int totalPriceSuite4 = 0;
        int totalPriceSuite5 = 0;
        int totalPriceSuite6 = 0;
        int totalPriceSuite7 = 0;
        int totalPriceSuite8 = 0;
        int totalPriceSuite9 = 0;
        int totalPriceSuite10 = 0;
        int totalPriceSuite11 = 0;
        int totalPriceSuite12 = 0;


        if (deyListS1 != null && !deyListS1.isEmpty()) {
            String deysS1 = parcReservDays(deyListS1);
            linearLayoutDaysSelectedS1.setVisibility(View.VISIBLE);
            txtDaysSelectedS1.setText(deysS1);

            totalPriceSuite1 = calculatePrice(nafaratMazad, deyListS1, 0);

            txtPriceDaysSelectedS1.setText(MyUtils.splitPrice(totalPriceSuite1) + " هزار تومان ");

        }
        if (deyListS2 != null && !deyListS2.isEmpty()) {
            String deysS2 = parcReservDays(deyListS2);
            linearLayoutDaysSelectedS2.setVisibility(View.VISIBLE);
            txtDaysSelectedS2.setText(deysS2);
            totalPriceSuite2 = calculatePrice(nafaratMazad, deyListS2, 1);
            txtPriceDaysSelectedS2.setText(MyUtils.splitPrice(totalPriceSuite2) + " هزار تومان ");
        }
        if (deyListS3 != null && !deyListS3.isEmpty()) {
            String deysS3 = parcReservDays(deyListS3);
            linearLayoutDaysSelectedS3.setVisibility(View.VISIBLE);
            txtDaysSelectedS3.setText(deysS3);
            totalPriceSuite3 = calculatePrice(nafaratMazad, deyListS3, 2);
            txtPriceDaysSelectedS3.setText(MyUtils.splitPrice(totalPriceSuite3) + " هزار تومان ");

        }
        if (deyListS4 != null && !deyListS4.isEmpty()) {
            String deysS4 = parcReservDays(deyListS4);
            linearLayoutDaysSelectedS4.setVisibility(View.VISIBLE);
            txtDaysSelectedS4.setText(deysS4);
            totalPriceSuite4 = calculatePrice(nafaratMazad, deyListS4, 3);
            txtPriceDaysSelectedS4.setText(MyUtils.splitPrice(totalPriceSuite4) + " هزار تومان ");

        }
        if (deyListS5 != null && !deyListS5.isEmpty()) {
            String deysS5 = parcReservDays(deyListS5);
            linearLayoutDaysSelectedS5.setVisibility(View.VISIBLE);
            txtDaysSelectedS5.setText(deysS5);
            totalPriceSuite5 = calculatePrice(nafaratMazad, deyListS5, 4);
            txtPriceDaysSelectedS5.setText(MyUtils.splitPrice(totalPriceSuite5) + " هزار تومان ");

        }
        if (deyListS6 != null && !deyListS6.isEmpty()) {
            String deysS6 = parcReservDays(deyListS6);
            linearLayoutDaysSelectedS6.setVisibility(View.VISIBLE);
            txtDaysSelectedS6.setText(deysS6);
            totalPriceSuite6 = calculatePrice(nafaratMazad, deyListS6, 5);
            txtPriceDaysSelectedS6.setText(MyUtils.splitPrice(totalPriceSuite6) + " هزار تومان ");

        }
        if (deyListS7 != null && !deyListS7.isEmpty()) {
            String deysS7 = parcReservDays(deyListS7);
            linearLayoutDaysSelectedS7.setVisibility(View.VISIBLE);
            txtDaysSelectedS7.setText(deysS7);
            totalPriceSuite7 = calculatePrice(nafaratMazad, deyListS7, 6);
            txtPriceDaysSelectedS7.setText(MyUtils.splitPrice(totalPriceSuite7) + " هزار تومان ");

        }
        if (deyListS8 != null && !deyListS8.isEmpty()) {
            String deysS8 = parcReservDays(deyListS8);
            linearLayoutDaysSelectedS8.setVisibility(View.VISIBLE);
            txtDaysSelectedS8.setText(deysS8);
            totalPriceSuite8 = calculatePrice(nafaratMazad, deyListS8, 7);
            txtPriceDaysSelectedS8.setText(MyUtils.splitPrice(totalPriceSuite8) + " هزار تومان ");

        }
        if (deyListS9 != null && !deyListS9.isEmpty()) {
            String deysS9 = parcReservDays(deyListS9);
            linearLayoutDaysSelectedS9.setVisibility(View.VISIBLE);
            txtDaysSelectedS9.setText(deysS9);
            totalPriceSuite9 = calculatePrice(nafaratMazad, deyListS9, 8);
            txtPriceDaysSelectedS9.setText(MyUtils.splitPrice(totalPriceSuite9) + " هزار تومان ");

        }
        if (deyListS10 != null && !deyListS10.isEmpty()) {
            String deysS10 = parcReservDays(deyListS10);
            linearLayoutDaysSelectedS10.setVisibility(View.VISIBLE);
            txtDaysSelectedS10.setText(deysS10);
            totalPriceSuite10 = calculatePrice(nafaratMazad, deyListS10, 9);
            txtPriceDaysSelectedS10.setText(MyUtils.splitPrice(totalPriceSuite10) + " هزار تومان ");
        }
        if (deyListS11 != null && !deyListS11.isEmpty()) {
            String deysS11 = parcReservDays(deyListS11);
            linearLayoutDaysSelectedS11.setVisibility(View.VISIBLE);
            txtDaysSelectedS11.setText(deysS11);
            totalPriceSuite11 = calculatePrice(nafaratMazad, deyListS11, 10);
            txtPriceDaysSelectedS11.setText(MyUtils.splitPrice(totalPriceSuite11) + " هزار تومان ");
        }
        if (deyListS12 != null && !deyListS12.isEmpty()) {
            String deysS12 = parcReservDays(deyListS12);
            linearLayoutDaysSelectedS12.setVisibility(View.VISIBLE);
            txtDaysSelectedS12.setText(deysS12);
            totalPriceSuite12 = calculatePrice(nafaratMazad, deyListS12, 11);
            txtPriceDaysSelectedS12.setText(MyUtils.splitPrice(totalPriceSuite12) + " هزار تومان ");
        }

        finalTotalPrice = totalPriceSuite1 + totalPriceSuite2 + totalPriceSuite3 + totalPriceSuite4 + totalPriceSuite5 + totalPriceSuite6 + totalPriceSuite7 +
                totalPriceSuite8 + totalPriceSuite9 + totalPriceSuite10 + totalPriceSuite11 + totalPriceSuite12;

        txtFinalPrice.setText(MyUtils.splitPrice(finalTotalPrice));

    }



    private int calculatePrice(int nafaratMazad, List<Integer> deyList, int index) {
        int priceSuite = 0;
        int extraPriceSuite = 0;
        int totalPriceSuite = 0;

        if (chkSpacialCustomer.isChecked()) {
            priceSuite = specialPriceList.get(index);
            extraPriceSuite = extraSpecialPriceList.get(index);
        } else {
            priceSuite = priceList.get(index);
            extraPriceSuite = extraPriceList.get(index);
        }


        if (nafaratMazad > 0) {
            totalPriceSuite = ((extraPriceSuite * nafaratMazad) + priceSuite) * deyList.size();
        } else {
            totalPriceSuite = priceSuite * deyList.size();
        }

        return totalPriceSuite;

    }

    private String buildString(List<Integer> list) {
        StringBuilder builder = new StringBuilder();

        if (list != null && !list.isEmpty()) {

            for (int i = 0; i < list.size(); i++) {
                builder.append(list.get(i)).append(",");
            }
        } else {
            builder.append("");
        }

        return builder.toString();

    }


    private void checkConditionCustomer(String fullName, String phone, String customerId, int nafaratAvaliyeh, int nafaratMazad, int finalCoast, int coastReceived,
                                        String numberFish, String reservistPerson, String resSuite1, String resSuite2, String resSuite3, String resSuite4, String resSuite5,
                                        String resSuite6, String resSuite7, String resSuite8, String resSuite9, String resSuite10, String resSuite11, String resSuite12) {

        MutableLiveData<Status> statusLiveData = new ViewModelProvider(requireActivity()).get(CustomerVM.class).checkConditionCustomer( customerId);

        statusLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    showDialog(fullName, phone, customerId, nafaratAvaliyeh, nafaratMazad, finalCoast, coastReceived, numberFish, reservistPerson, resSuite1, resSuite2, resSuite3, resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12);

                } else {

                    sendData(fullName, phone, customerId, nafaratAvaliyeh, nafaratMazad, finalCoast, coastReceived, numberFish, reservistPerson, resSuite1, resSuite2, resSuite3, resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12);


                }
            }
        });

    }



    private void showDialog(String fullName, String phone, String customerId, int nafaratAvaliyeh, int nafaratMazad, int finalCoast, int coastReceived,
                            String numberFish, String reservistPerson, String resSuite1, String resSuite2, String resSuite3, String resSuite4, String resSuite5,
                            String resSuite6, String resSuite7, String resSuite8, String resSuite9, String resSuite10, String resSuite11, String resSuite12){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_check_condition);
        AlertDialog dialog = builder.create();
        dialog.show();
        btnConfirm = dialog.findViewById(R.id.cardDialog_customLayoutCheckCondition_btn_btn_confirm);
        btnCancel = dialog.findViewById(R.id.cardDialog_customLayoutCheckCondition_btn_cancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendData(fullName, phone, customerId, nafaratAvaliyeh, nafaratMazad, finalCoast, coastReceived, numberFish, reservistPerson, resSuite1, resSuite2, resSuite3, resSuite4, resSuite5, resSuite6, resSuite7, resSuite8, resSuite9, resSuite10, resSuite11, resSuite12);
                dialog.dismiss();

            }
        });
    }


    private void getSuitePrice(){
        prgCalculatePrice.setVisibility(View.VISIBLE);
        checkPrice =true;

        MutableLiveData<List<PriceSuites>> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).getSuitePrice();
        liveData.observe(requireActivity(), new Observer<List<PriceSuites>>() {
            @Override
            public void onChanged(List<PriceSuites> priceSuites) {
                prgCalculatePrice.setVisibility(View.GONE);

                for (PriceSuites prices : priceSuites) {

                    specialPriceList.add(Integer.parseInt(prices.getSpecialPrice()));
                    priceList.add(Integer.parseInt(prices.getPrice()));

                    extraSpecialPriceList.add(Integer.parseInt(prices.getExtraSpecialPrice()));
                    extraPriceList.add(Integer.parseInt(prices.getExtraPrice()));
                }
                initTextViewDaysSelected();
            }
        });
    }
}