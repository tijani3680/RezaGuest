package com.tijani.rezaguestmvvm.view.fragments;

import android.graphics.Color;
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

import com.github.angads25.toggle.interfaces.OnToggledListener;
import com.github.angads25.toggle.model.ToggleableView;
import com.github.angads25.toggle.widget.LabeledSwitch;
import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentActivateSuitesBinding;
import com.tijani.rezaguestmvvm.model.ActivateSuites;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.viewmodel.SuiteVM;

public class FragmentActivateSuites extends Fragment {
    FragmentActivateSuitesBinding binding;
    private ImageView imgBack;
    private LabeledSwitch switchS1, switchS2, switchS3, switchS4, switchS5, switchS6, switchS7, switchS8, switchS9, switchS10, switchS11, switchS12;
    private RelativeLayout relPrg;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activate_suites, container, false);
        imgBack = binding.imgFragmentActivateSuiteImgBack;
        switchS1 = binding.swichS1;
        switchS2 = binding.swichS2;
        switchS3 = binding.swichS3;
        switchS4 = binding.swichS4;
        switchS5 = binding.swichS5;
        switchS6 = binding.swichS6;
        switchS7 = binding.swichS7;
        switchS8 = binding.swichS8;
        switchS9 = binding.swichS9;
        switchS10 = binding.swichS10;
        switchS11 = binding.swichS11;
        switchS12 = binding.swichS12;
        relPrg = binding.relFragmentActivateSuitePrg;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        checkActivateSuites();
        imgBack.setOnClickListener(v -> Navigation.findNavController(imgBack).popBackStack());
        switchS1.setOnToggledListener((toggleableView, isOn) -> {
            if (isOn) {
                activateSuite("s1");
            } else {
                inActiveSuite("s1");

            }

        });
        switchS2.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s2");
            } else {
                inActiveSuite("s2");
            }

        });
        switchS3.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s3");
            } else {
                inActiveSuite("s3");
            }

        });
        switchS4.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s4");
            } else {
                inActiveSuite("s4");
            }

        });
        switchS5.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s5");
            } else {
                inActiveSuite("s5");
            }

        });
        switchS6.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s6");
            } else {
                inActiveSuite("s6");
            }

        });
        switchS7.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s7");
            } else {
                inActiveSuite("s7");
            }

        });
        switchS8.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s8");
            } else {
                inActiveSuite("s8");
            }

        });
        switchS9.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s9");
            } else {
                inActiveSuite("s9");
            }

        });
        switchS10.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s10");
            } else {
                inActiveSuite("s10");
            }

        });
        switchS11.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s11");
            } else {
                inActiveSuite("s11");
            }

        });
        switchS12.setOnToggledListener((toggleableView, isOn) -> {

            if (isOn) {
                activateSuite("s12");
            } else {
                inActiveSuite("s12");
            }

        });

    }

    private void checkActivateSuites() {
        MutableLiveData<ActivateSuites> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).checkActivateSuite();
        liveData.observe(requireActivity(), new Observer<ActivateSuites>() {
            @Override
            public void onChanged(ActivateSuites activateSuites) {
                relPrg.setVisibility(View.GONE);

                if (activateSuites.getSuite1().equals("1")) {
                    switchS1.setOn(true);
                }

                if (activateSuites.getSuite2().equals("1")) {
                    switchS2.setOn(true);
                }

                if (activateSuites.getSuite3().equals("1")) {
                    switchS3.setOn(true);
                }

                if (activateSuites.getSuite4().equals("1")) {
                    switchS4.setOn(true);
                }

                if (activateSuites.getSuite5().equals("1")) {
                    switchS5.setOn(true);
                }

                if (activateSuites.getSuite6().equals("1")) {
                    switchS6.setOn(true);
                }

                if (activateSuites.getSuite7().equals("1")) {
                    switchS7.setOn(true);
                }

                if (activateSuites.getSuite8().equals("1")) {
                    switchS8.setOn(true);
                }

                if (activateSuites.getSuite9().equals("1")) {
                    switchS9.setOn(true);
                }

                if (activateSuites.getSuite10().equals("1")) {
                    switchS10.setOn(true);
                }

                if (activateSuites.getSuite11().equals("1")) {
                    switchS11.setOn(true);
                }

                if (activateSuites.getSuite12().equals("1")) {
                    switchS12.setOn(true);
                }


            }
        });

    }

    private void activateSuite(String suite) {
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).activateSuite(suite);

        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "سوئیت فعال شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void inActiveSuite(String suite) {
        MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(SuiteVM.class).inActiveSuite(suite);
        liveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                if (status.getStatus().equals("success")) {
                    Toast.makeText(getContext(), "سوئیت غیرفعال شد", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}