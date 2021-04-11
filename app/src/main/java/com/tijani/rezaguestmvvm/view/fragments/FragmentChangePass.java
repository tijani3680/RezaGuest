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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentChangePassBinding;
import com.tijani.rezaguestmvvm.model.Status;
import com.tijani.rezaguestmvvm.model.UserPass;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.util.MyUtils;
import com.tijani.rezaguestmvvm.viewmodel.OperatorVM;

public class FragmentChangePass extends Fragment {
    FragmentChangePassBinding binding;
    private ImageView imgBack;
    private EditText edtOldUserName, edtOldPassword, edtNewUserName, edtNewPassword, edtRepeatePassword;
    private Button btnConfirm;
    private RelativeLayout prg;
    private ProgressBar progressBar;
    private String userName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_change_pass, container, false);
        imgBack = binding.imgFragmentChangePassImgBack;
        edtOldUserName = binding.edtFragmentChangePassOldUserName;
        edtOldPassword = binding.edtFragmentChangePassOldPassword;
        edtNewUserName = binding.edtFragmentChangePassNewUserName;
        edtNewPassword = binding.edtFragmentChangePassNewPassword;
        edtRepeatePassword = binding.edtFragmentChangePassRepeateNewPassword;
        btnConfirm = binding.fragmentChangePassBtnConfirm;
        prg = binding.relFragmentChangePassPrg;
        progressBar = binding.fragmentChangePassProgressbar2;
        userName = MySharedPreferences.readUserName(getContext());

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

        getOldUserPass(userName);

        btnConfirm.setOnClickListener(v -> {
            String newUserName = edtNewUserName.getText().toString();
            String newPassword = edtNewPassword.getText().toString();
            String repeatePassword = edtRepeatePassword.getText().toString();
            updateUserPass(userName, newUserName, newPassword, repeatePassword);
        });
    }

    private void getOldUserPass(String userName) {
        MutableLiveData<UserPass> liveData = new ViewModelProvider(requireActivity()).get(OperatorVM.class).getUserPass(userName);
        liveData.observe(requireActivity(), userPass -> {
            prg.setVisibility(View.GONE);
            edtOldUserName.setText(userPass.getUserName());
            edtOldPassword.setText(userPass.getPassword());

        });
    }

    private void updateUserPass(String oldUserName, String newUserName, String newPassword, String repeatePassword) {
        if (newUserName.isEmpty() || newPassword.isEmpty() || repeatePassword.isEmpty()) {
            Toast.makeText(getContext(), "لطفا تمامی فیلدها را تکمیل کنید!", Toast.LENGTH_SHORT).show();
        } else {
            if (newPassword.equals(repeatePassword)) {
                progressBar.setVisibility(View.VISIBLE);
                MutableLiveData<Status> liveData = new ViewModelProvider(requireActivity()).get(OperatorVM.class).changeUserPass(oldUserName, newUserName, newPassword);
                liveData.observe(requireActivity(), new Observer<Status>() {
                    @Override
                    public void onChanged(Status status) {
                        progressBar.setVisibility(View.GONE);
                        if (status.getStatus().equals("success")) {
                            Toast.makeText(getContext(), "تغییرات با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
                            MySharedPreferences.removeUserName(getContext());
                            Navigation.findNavController(btnConfirm).popBackStack();
                        } else {
                            Toast.makeText(getContext(), "خطای ناشناخته!", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            } else {
                Toast.makeText(getContext(), "رمز ورود با تکرار آن مطابقت ندارد!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}