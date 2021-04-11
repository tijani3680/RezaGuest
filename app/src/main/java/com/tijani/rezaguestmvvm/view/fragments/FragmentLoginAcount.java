package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentLoginAcountBinding;
import com.tijani.rezaguestmvvm.model.CheckLogin;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.viewmodel.CustomerVM;

public class FragmentLoginAcount extends Fragment {
    FragmentLoginAcountBinding binding;
    private ImageView imageBack;
    private Button btnLogin;
    private EditText edtUserName, edtPassword;
    private ProgressBar progressBar;
    private AppCompatCheckBox checkBox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_acount, container, false);
        imageBack = binding.imgFragmentLoginAcountImgBack;
        btnLogin = binding.btnActivityUserSigninBtnLogin;
        edtUserName = binding.edtUserNameSignIn;
        edtPassword = binding.edtpassSignIn;
        progressBar = binding.fragmentLoginAcountProgressbar;
        checkBox = binding.chkActivityUserSigninChkShowPass;
        return binding.getRoot();


    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(imageBack).popBackStack();

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkLogin();


            }
        });
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    edtPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {

                    edtPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);

                }
            }
        });


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void checkLogin() {

        String userName = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        if (userName.isEmpty()) {
            Toast.makeText(getContext(), "لطفا نام کاربری را وارد کنید!", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getContext(), "لطفا رمز عبور را وارد کنید!", Toast.LENGTH_SHORT).show();

        } else {
            progressBar.setVisibility(View.VISIBLE);

            CustomerVM customerVM = new ViewModelProvider(requireActivity()).get(CustomerVM.class);
            customerVM.checkLogin(userName, password);

            MutableLiveData<CheckLogin> loginLiveData = customerVM.checkLoginLiveData;

            loginLiveData.observe(requireActivity(), new Observer<CheckLogin>() {
                @Override
                public void onChanged(CheckLogin checkLogin) {
                    progressBar.setVisibility(View.GONE);
                    String status = checkLogin.getStatus();
                    if (status.equals("success")) {
                        MySharedPreferences.savedUserInformation(getContext(),checkLogin.getUserName(),checkLogin.getShenaseh());
                        /*NavDirections navDirections = FragmentLoginAcountDirections.actionFragmentLoginAcountToFragmentHome();*/
                        Navigation.findNavController(btnLogin).popBackStack();
                    } else {
                        Toast.makeText(getContext(), "نام کاربری یا کلمه عبور اشتباه است!", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }

}