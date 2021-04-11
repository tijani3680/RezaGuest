package com.tijani.rezaguestmvvm.view.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentCreateOperatorBinding;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.viewmodel.CustomerVM;

import java.util.PrimitiveIterator;

public class FragmentCreateOperator extends Fragment {
    FragmentCreateOperatorBinding binding;
    private EditText edtFullName,edtCode,edtUserName,edtPass;
    private ProgressBar progressBar;
    private ImageView imgBack;
    private Button btnConfirm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_operator,container,false);
        imgBack = binding.imgFragmentCreateOperatorImgBack;
        edtFullName = binding.edtFragmentCreateOperatorEdtFullName;
        edtCode = binding.edtFragmentCreateOperatorIdOperator;
        edtUserName = binding.edtFragmentCreateOperatorUserName;
        edtPass = binding.edtFragmentCreateOperatorPassword;
        progressBar = binding.fragmentCreateOperatorProgressbar;
        btnConfirm  = binding.fragmentCreateOperatorBtnConfirm;

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

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = edtFullName.getText().toString();
                String code = edtCode.getText().toString();
                String userName = edtUserName.getText().toString();
                String password = edtPass.getText().toString();
                if (fullName.isEmpty() || code.isEmpty() || userName.isEmpty() || password.isEmpty()){
                    Toast.makeText(getContext(), "پر کردن تمامی فیلد ها الزامی است!", Toast.LENGTH_SHORT).show();
                }else {
                    createOperator(fullName,code,userName,password);
                }

            }
        });
    }

    private void createOperator(String fullName,String code,String userName,String password){
        progressBar.setVisibility(View.VISIBLE);
        CustomerVM viewModel = new ViewModelProvider(requireActivity()).get(CustomerVM.class);
        viewModel.createOperator(fullName,code,userName,password);
        viewModel.createOperatorLiveData.observe(requireActivity(), new Observer<Status>() {
            @Override
            public void onChanged(Status status) {
                progressBar.setVisibility(View.GONE);
                if (status.getStatus().equals("exist")){
                    Toast.makeText(getContext(), "کد مربوطه یا نام کاربری در دسترس نیست!", Toast.LENGTH_SHORT).show();
                    edtCode.setTextColor(Color.RED);
                    edtUserName.setTextColor(Color.RED);
                }
                if (status.getStatus().equals("success")){
                    Toast.makeText(getContext(), "اپراتور با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(btnConfirm).popBackStack();
                }

                if (status.getStatus().equals("faild")){
                    Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}