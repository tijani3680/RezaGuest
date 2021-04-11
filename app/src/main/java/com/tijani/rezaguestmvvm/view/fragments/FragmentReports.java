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

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentReportsBinding;
import com.tijani.rezaguestmvvm.model.Reports;
import com.tijani.rezaguestmvvm.viewmodel.ReportsVM;

public class FragmentReports extends Fragment {
    FragmentReportsBinding binding;
   private ImageView imgBack;
   private RelativeLayout prg;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_reports,container,false);
        imgBack = binding.imgFragmentReportsImgBack;
        prg = binding.relFragmentReportsPrg;
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
        getReports();
    }

    private void getReports(){
        MutableLiveData<Reports> liveData = new ViewModelProvider(requireActivity()).get(ReportsVM.class).getReports();
        liveData.observe(requireActivity(), new Observer<Reports>() {
            @Override
            public void onChanged(Reports reports) {
                prg.setVisibility(View.GONE);
                binding.setReportsInformation(reports);
            }
        });

    }
}