package com.tijani.rezaguestmvvm.view.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentDetailsMordadBinding;
import com.tijani.rezaguestmvvm.model.DetailsReserv;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.viewmodel.MordadVM;
import com.tijani.rezaguestmvvm.viewmodel.OrdibeheshtVM;

public class FragmentDetailsMordad extends Fragment {
    FragmentDetailsMordadBinding binding;

    private TextView txtName, txtShenaseh, txtPhone, txtReservDate, txtResevistPerson, txtNafaratAvaliyeh, txtNafaratMazad, txtFinalPrice, txtCoastReceived, txtShomarehFish;
    private ImageView imgBack;
    private LinearLayout lilS1, lilS2, lilS3, lilS4, lilS5, lilS6, lilS7, lilS8, lilS9, lilS10, lilS11, lilS12;
    private TextView txtS1, txtS2, txtS3, txtS4, txtS5, txtS6, txtS7, txtS8, txtS9, txtS10, txtS11, txtS12;
    private RelativeLayout prg;
    private TextView txtSituationPrice;
    private FloatingActionButton fab;
    private RelativeLayout relAfterDeleteReserv;
    private ScrollView scrollView;

    private String id;
    private String name;
    private String shenaseh;
    private String phone;
    private String reservDate;
    private String reservistPerson;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_mordad, container, false);

        txtName = binding.txtDetailsReservationName;
        txtShenaseh = binding.txtDetailsReservationShenaseh;
        txtPhone = binding.txtDetailsReservationPhone;
        txtReservDate = binding.txtDetailsReservationReservDate;
        txtResevistPerson = binding.txtDetailsReservationReservistPerson;
        imgBack = binding.imgFragmentDetailsReservImgBack;
        txtNafaratAvaliyeh = binding.txtDetailsReservationReservNumberAvaliyeh;
        txtNafaratMazad = binding.txtDetailsReservationReservNumberMazad;
        txtFinalPrice = binding.txtDetailsReservationReservTotalPrice;
        txtCoastReceived = binding.txtDetailsReservationReservPriceDaryafti;
        txtShomarehFish = binding.txtDetailsReservationReservFishPardakhti;

        relAfterDeleteReserv = binding.relAfterDeleteReserv;
        scrollView = binding.scrollView;

        lilS1 = binding.lilFragmentSendInformationLinearDaysSelectedSuite1;
        lilS2 = binding.lilFragmentSendInformationLinearDaysSelectedSuite2;
        lilS3 = binding.lilFragmentSendInformationLinearDaysSelectedSuite3;
        lilS4 = binding.lilFragmentSendInformationLinearDaysSelectedSuite4;
        lilS5 = binding.lilFragmentSendInformationLinearDaysSelectedSuite5;
        lilS6 = binding.lilFragmentSendInformationLinearDaysSelectedSuite6;
        lilS7 = binding.lilFragmentSendInformationLinearDaysSelectedSuite7;
        lilS8 = binding.lilFragmentSendInformationLinearDaysSelectedSuite8;
        lilS9 = binding.lilFragmentSendInformationLinearDaysSelectedSuite9;
        lilS10 = binding.lilFragmentSendInformationLinearDaysSelectedSuite10;
        lilS11 = binding.lilFragmentSendInformationLinearDaysSelectedSuite11;
        lilS12 = binding.lilFragmentSendInformationLinearDaysSelectedSuite12;

        txtS1 = binding.txtFragmentSendInformationTxtDaysSelectedSuite1;
        txtS2 = binding.txtFragmentSendInformationTxtDaysSelectedSuite2;
        txtS3 = binding.txtFragmentSendInformationTxtDaysSelectedSuite3;
        txtS4 = binding.txtFragmentSendInformationTxtDaysSelectedSuite4;
        txtS5 = binding.txtFragmentSendInformationTxtDaysSelectedSuite5;
        txtS6 = binding.txtFragmentSendInformationTxtDaysSelectedSuite6;
        txtS7 = binding.txtFragmentSendInformationTxtDaysSelectedSuite7;
        txtS8 = binding.txtFragmentSendInformationTxtDaysSelectedSuite8;
        txtS9 = binding.txtFragmentSendInformationTxtDaysSelectedSuite9;
        txtS10 = binding.txtFragmentSendInformationTxtDaysSelectedSuite10;
        txtS11 = binding.txtFragmentSendInformationTxtDaysSelectedSuite11;
        txtS12 = binding.txtFragmentSendInformationTxtDaysSelectedSuite12;

        txtSituationPrice = binding.txtFragmentSendInformationTxtFinalPrice;

        prg = binding.relFragmentDetailsReservationPrg;
        fab = binding.fab;

        id = getArguments().getString("id");
        name = getArguments().getString("customerName");
        shenaseh = getArguments().getString("shenaseh");
        phone = getArguments().getString("phone");
        reservDate = getArguments().getString("reservDate");
        reservistPerson = getArguments().getString("reservistPerson");

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName.setText(name);
        txtShenaseh.setText(shenaseh);
        txtPhone.setText(phone);
        txtReservDate.setText(reservDate);
        txtResevistPerson.setText(reservistPerson);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imgBack).popBackStack();
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBottomSheet();
            }
        });

        getData();

    }

    private void getData() {
        MordadVM viewModel = new ViewModelProvider(requireActivity()).get(MordadVM.class);
        viewModel.getDetailsReserv(id);
        viewModel.detailsReservLiveData.observe(requireActivity(), new Observer<DetailsReserv>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(DetailsReserv detailsReserv) {
                prg.setVisibility(View.GONE);
                txtNafaratAvaliyeh.setText(detailsReserv.getNafaratAvaliyeh());
                txtNafaratMazad.setText(detailsReserv.getNafaratMazad());
                txtFinalPrice.setText(MyUtils.splitPrice(Integer.parseInt(detailsReserv.getFinalCoast())) + "  هزار تومان  ");
                txtCoastReceived.setText(MyUtils.splitPrice(Integer.parseInt(detailsReserv.getCoastReceived())) + "  هزار تومان  ");
                txtShomarehFish.setText(detailsReserv.getNumberFish());
                String suite1 = detailsReserv.getSuite1();
                String suite2 = detailsReserv.getSuite2();
                String suite3 = detailsReserv.getSuite3();
                String suite4 = detailsReserv.getSuite4();
                String suite5 = detailsReserv.getSuite5();
                String suite6 = detailsReserv.getSuite6();
                String suite7 = detailsReserv.getSuite7();
                String suite8 = detailsReserv.getSuite8();
                String suite9 = detailsReserv.getSuite9();
                String suite10 = detailsReserv.getSuite10();
                String suite11 = detailsReserv.getSuite11();
                String suite12 = detailsReserv.getSuite12();


                if (suite1 != null) {
                    initReservDays(suite1, lilS1, txtS1);
                }
                if (suite2 != null) {
                    initReservDays(suite2, lilS2, txtS2);
                }

                if (suite3 != null) {
                    initReservDays(suite3, lilS3, txtS3);
                }

                if (suite4 != null) {
                    initReservDays(suite4, lilS4, txtS4);
                }

                if (suite5 != null) {
                    initReservDays(suite5, lilS5, txtS5);
                }

                if (suite6 != null) {
                    initReservDays(suite6, lilS6, txtS6);
                }

                if (suite7 != null) {
                    initReservDays(suite7, lilS7, txtS7);
                }

                if (suite8 != null) {
                    initReservDays(suite8, lilS8, txtS8);
                }

                if (suite9 != null) {
                    initReservDays(suite9, lilS9, txtS9);
                }

                if (suite10 != null) {
                    initReservDays(suite10, lilS10, txtS10);
                }

                if (suite11 != null) {
                    initReservDays(suite11, lilS11, txtS11);
                }

                if (suite12 != null) {
                    initReservDays(suite12, lilS12, txtS12);
                }


                int situationPrice = Integer.parseInt(detailsReserv.getSituation());
                if (situationPrice == 0) {
                    txtSituationPrice.setTextColor(Color.BLACK);
                    txtSituationPrice.setText(situationPrice + "");
                } else {
                    if (situationPrice > 0) {
                        txtSituationPrice.setTextColor(Color.RED);
                        String price = MyUtils.splitPrice(situationPrice);
                        txtSituationPrice.setText(price + "  هزار تومان بدهکار  ");

                    } else {
                        txtSituationPrice.setTextColor(Color.BLUE);
                        String price = MyUtils.splitPrice(-situationPrice);
                        txtSituationPrice.setText(price + "  هزار تومان بستانکار  ");
                    }
                }
            }
        });
    }
    private void initReservDays(String suite, LinearLayout lilS, TextView txtS) {
        StringBuilder builder = new StringBuilder();
        String[] deys = suite.split(",");
        for (int i = 0; i < deys.length; i++) {
            int deyNumber = Integer.parseInt(deys[i]) + 1;
            String dey = MyUtils.number2persian(deyNumber + " مرداد ");
            builder.append(dey).append(" , ");
        }
        lilS.setVisibility(View.VISIBLE);
        txtS.setText(builder.toString());
    }
    private void updateMoney(String id, String money) {
        MordadVM viewModel = new ViewModelProvider(requireActivity()).get(MordadVM.class);
        viewModel.updateCoastReceived(id, money);
        viewModel.updateCoastReceivedLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                    getData();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
    private void deleteReservCustomer(String id) {
        MordadVM viewModel = new ViewModelProvider(requireActivity()).get(MordadVM.class);
        viewModel.deleteReservCustomer(id);
        viewModel.deleteReservCustomerLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {

                    scrollView.setVisibility(View.GONE);
                    fab.setVisibility(View.GONE);
                    relAfterDeleteReserv.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }



    private void openBottomSheet() {
        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);
        CardView cancelReservation = view.findViewById(R.id.cardView_Cancel_Reserv);
        CardView addMoney = view.findViewById(R.id.cardView_Add_Money);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.setCancelable(true);
        bottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomSheetDialog.show();

        addMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMoney();
                bottomSheetDialog.dismiss();
            }
        });

        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(R.layout.custom_layout_dialog_cancel_reserv);
                builder.setCancelable(false);
                Dialog dialog = builder.create();

                dialog.show();
                CardView btnConfirm = dialog.findViewById(R.id.cardDialog_customLayoutCancelReserv_btn_confirm);
                CardView btnCancel = dialog.findViewById(R.id.cardDialog_customLayoutCancelReserv_btn_cancel);
                btnConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        deleteReservCustomer(id);
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });


            }
        });
    }
    private void addMoney() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_add_money);
        builder.setCancelable(false);
        Dialog dialog = builder.create();

        dialog.show();
        EditText edtMoney = dialog.findViewById(R.id.edtCustomDialogAddMoney);
        CardView btnAddMoney = dialog.findViewById(R.id.cardDialog_custom_dialog_AddMoney_btn_confirm);
        CardView btnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_AddMoney_btn_cancel);
        btnAddMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String money = edtMoney.getText().toString();
                if (money.isEmpty()) {
                    Toast.makeText(getContext(), "مبلغ را وارد کنید!", Toast.LENGTH_SHORT).show();
                    edtMoney.setError("");
                } else {
                    updateMoney(id, money);
                    dialog.dismiss();

                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
}