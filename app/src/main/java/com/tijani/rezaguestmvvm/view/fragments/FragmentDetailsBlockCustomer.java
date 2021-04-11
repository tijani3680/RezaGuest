package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentDetailsBlockCustomerBinding;


public class FragmentDetailsBlockCustomer extends Fragment {
    FragmentDetailsBlockCustomerBinding binding;
    TextView txtNameToolbar,txtDescription;
    ImageView imgBack;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_details_block_customer,container,false);
        txtNameToolbar = binding.txtFragmentDetailsBlockCustomerCustomerName;
        txtDescription = binding.txtFragmentDetailsBlockCustomerDescription;
        imgBack = binding.imgFragmentDetailsBlockCustomerImgBack;

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       String customerName = getArguments().getString("customerName");
       String description = getArguments().getString("description");

       txtNameToolbar.setText(customerName);
       txtDescription.setText(description);

       imgBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Navigation.findNavController(imgBack).popBackStack();
           }
       });


    }
}