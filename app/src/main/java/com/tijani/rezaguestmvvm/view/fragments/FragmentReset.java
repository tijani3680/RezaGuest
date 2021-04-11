package com.tijani.rezaguestmvvm.view.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.database.DatabaseUtils;
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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentResetBinding;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.viewmodel.MonthsVM;

public class FragmentReset extends Fragment {
    FragmentResetBinding binding;
    private ImageView imgBack;
    private CardView cardResFarvardin,cardResOrdibehesht,cardResKhordad,cardResTir,cardResMordad,cardResShahrivar,cardResMehr,cardResAban,cardResAzar,cardResDey,cardResBahman,cardResEsfand,cardResAllMonths;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_reset,container,false);
       imgBack = binding.imgFragmentResetImgBack;
       cardResFarvardin = binding.cardFragmentResetResFarvardin;
       cardResOrdibehesht = binding.cardFragmentResetResOrdibehesht;
       cardResKhordad = binding.cardFragmentResetResKhordad;
       cardResTir = binding.cardFragmentResetResTir;
       cardResMordad = binding.cardFragmentResetResMordad;
       cardResShahrivar = binding.cardFragmentResetResShahrivar;
       cardResMehr = binding.cardFragmentResetResMehr;
       cardResAban = binding.cardFragmentResetResAban;
       cardResAzar = binding.cardFragmentResetResAzar;
       cardResDey = binding.cardFragmentResetResDey;
       cardResBahman = binding.cardFragmentResetResBahman;
       cardResEsfand = binding.cardFragmentResetResEsfand;
       cardResAllMonths = binding.cardFragmentResetResAllMonths;
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgBack.setOnClickListener(v -> Navigation.findNavController(imgBack).popBackStack());
        cardResFarvardin.setOnClickListener(v -> showDialog("farvardin"));
        cardResOrdibehesht.setOnClickListener(v -> showDialog("ordibehesht"));
        cardResKhordad.setOnClickListener(v -> showDialog("khordad"));
        cardResTir.setOnClickListener(v -> showDialog("tir"));
        cardResMordad.setOnClickListener(v -> showDialog("mordad"));
        cardResShahrivar.setOnClickListener(v -> showDialog("shahrivar"));
        cardResMehr.setOnClickListener(v -> showDialog("mehr"));
        cardResAban.setOnClickListener(v -> showDialog("aban"));
        cardResAzar.setOnClickListener(v -> showDialog("azar"));
        cardResDey.setOnClickListener(v -> showDialog("dey"));
        cardResBahman.setOnClickListener(v -> showDialog("bahman"));
        cardResEsfand.setOnClickListener(v -> showDialog("esfand"));
        cardResAllMonths.setOnClickListener(v -> showDialog("allMonths"));
    }

    private void showDialog(String month){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.custom_layout_dialog_reset_month);
        builder.setCancelable(false);
        Dialog dialog = builder.create();

        dialog.show();
        ProgressBar prg = dialog.findViewById(R.id.customLayoutResetMonth_progressbar);
        CardView btnCancel = dialog.findViewById(R.id.cardDialog_customLayoutResetMonth_btn_cancel);
        CardView btnConfirm =dialog.findViewById(R.id.cardDialog_customLayoutResetMonth_btn_confirm);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prg.setVisibility(View.VISIBLE);
                resetMonth(month,prg,dialog);

            }
        });
    }
    private void resetMonth(String month,ProgressBar prg,Dialog dialog){
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(MonthsVM.class).reset(month);
        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")){
                    prg.setVisibility(View.GONE);
                    dialog.dismiss();
                    Toast.makeText(getContext(), "عملیات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}