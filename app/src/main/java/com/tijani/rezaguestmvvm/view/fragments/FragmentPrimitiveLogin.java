package com.tijani.rezaguestmvvm.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentPrimitiveLoginBinding;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.util.MyUtils;

public class FragmentPrimitiveLogin extends Fragment {
    FragmentPrimitiveLoginBinding binding;

    ///Views
    private Button btnSignIn;
    private EditText edtPass;

    /// Variable
    private int conter = 3;
    private  String passwordPrimitiveLogin;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_primitive_login, container, false);
        btnSignIn = binding.btnActivityPrimitiveLoginBtnLogin;
        edtPass = binding.edtActivityPrimitiveLoginEdtPassword;
        return binding.getRoot();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     passwordPrimitiveLogin =  MySharedPreferences.readLockApp(getContext());


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = MyUtils.persianToEnglish(edtPass.getText().toString());
                checkPass(pass);

            }
        });

        if (passwordPrimitiveLogin ==null){
            NavDirections navDirections = FragmentPrimitiveLoginDirections.actionFragmentPrimitiveLoginToFragmentHome();
            Navigation.findNavController(btnSignIn).navigate(navDirections);

        }

    }

    private void checkPass(String password) {

        if (password.isEmpty()) {

            edtPass.setError("رمز ورود را وارد کنید!");
        } else {


            if (!password.equals(passwordPrimitiveLogin)) {

                Toast.makeText(getContext(), "رمز ورود اشتباه وارد شده است!", Toast.LENGTH_SHORT).show();
                conter--;
                if (conter == 0) {
                    btnSignIn.setClickable(false);
                    Toast.makeText(getContext(), "رمز بیش از حد مجاز اشتباه وارد شده است!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Navigation.findNavController(btnSignIn).navigate(R.id.action_fragmentPrimitiveLogin_to_fragmentHome);


            }

        }
    }

}