package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.angads25.toggle.widget.LabeledSwitch;
import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentActivateMonthsBinding;
import com.tijani.rezaguestmvvm.model.ActivateMonths;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.viewmodel.MonthsVM;
import com.tijani.rezaguestmvvm.viewmodel.SuiteVM;

public class FragmentActivateMonths extends Fragment {
    FragmentActivateMonthsBinding binding;
    private ImageView imgBack;
    private LabeledSwitch switchM1, switchM2, switchM3, switchM4, switchM5, switchM6, switchM7, switchM8, switchM9, switchM10, switchM11, switchM12;
    private RelativeLayout relPrg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activate_months, container, false);
        imgBack = binding.imgFragmentActivateMonthsImgBack;
        switchM1 = binding.swichM1;
        switchM2 = binding.swichM2;
        switchM3 = binding.swichM3;
        switchM4 = binding.swichM4;
        switchM5 = binding.swichM5;
        switchM6 = binding.swichM6;
        switchM7 = binding.swichM7;
        switchM8 = binding.swichM8;
        switchM9 = binding.swichM9;
        switchM10 = binding.swichM10;
        switchM11 = binding.swichM11;
        switchM12 = binding.swichM12;
        relPrg = binding.relFragmentActivateMonthsPrg;
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkActivateMonths();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imgBack).popBackStack();
            }
        });

        switchM1.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m1");
            } else {
                inActiveMonth("m1");

            }

        });
        switchM2.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m2");
            } else {
                inActiveMonth("m2");

            }

        });
        switchM3.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m3");
            } else {
                inActiveMonth("m3");

            }

        });
        switchM4.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m4");
            } else {
                inActiveMonth("m4");

            }

        });
        switchM5.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m5");
            } else {
                inActiveMonth("m5");

            }

        });
        switchM6.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m6");
            } else {
                inActiveMonth("m6");

            }

        });
        switchM7.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m7");
            } else {
                inActiveMonth("m7");

            }

        });
        switchM8.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m8");
            } else {
                inActiveMonth("m8");

            }

        });
        switchM9.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m9");
            } else {
                inActiveMonth("m9");

            }

        });
        switchM10.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m10");
            } else {
                inActiveMonth("m10");

            }

        });
        switchM11.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m11");
            } else {
                inActiveMonth("m11");

            }

        });
        switchM12.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateMonth("m12");
            } else {
                inActiveMonth("m12");

            }

        });


    }

    private void checkActivateMonths() {
        MutableLiveData<ActivateMonths> liveData = new ViewModelProvider(requireActivity()).get(MonthsVM.class).checkActivateMonths();
        liveData.observe(requireActivity(), new Observer<ActivateMonths>() {
            @Override
            public void onChanged(ActivateMonths activateMonths) {
                relPrg.setVisibility(View.GONE);
                if (activateMonths.getFarvardin().equals("1")) {
                    switchM1.setOn(true);
                }
                if (activateMonths.getOrdibehesht().equals("1")) {
                    switchM2.setOn(true);
                }
                if (activateMonths.getKhordad().equals("1")) {
                    switchM3.setOn(true);
                }
                if (activateMonths.getTir().equals("1")) {
                    switchM4.setOn(true);
                }
                if (activateMonths.getMordad().equals("1")) {
                    switchM5.setOn(true);
                }
                if (activateMonths.getShahrivar().equals("1")) {
                    switchM6.setOn(true);
                }
                if (activateMonths.getMehr().equals("1")) {
                    switchM7.setOn(true);
                }
                if (activateMonths.getAban().equals("1")) {
                    switchM8.setOn(true);
                }
                if (activateMonths.getAzar().equals("1")) {
                    switchM9.setOn(true);
                }
                if (activateMonths.getDey().equals("1")) {
                    switchM10.setOn(true);
                }
                if (activateMonths.getBahman().equals("1")) {
                    switchM11.setOn(true);
                }
                if (activateMonths.getEsfand().equals("1")) {
                    switchM12.setOn(true);
                }

            }
        });
    }


    private void activateMonth(String month) {
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(MonthsVM.class).activateMonth(month);

        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "ماه مربوطه فعال شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void inActiveMonth(String month) {
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(MonthsVM.class).inActiveMonth(month);
        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "ماه مربوطه غیرفعال شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}