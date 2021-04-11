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
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentLockAppBinding;
import com.tijani.rezaguestmvvm.util.MySharedPreferences;
import com.tijani.rezaguestmvvm.util.MyUtils;

public class FragmentLockApp extends Fragment {
    FragmentLockAppBinding binding;
    private ImageView imgBack;
    private EditText edtPass, edtRepass;
    private Button btnLock;
    private TextView txtDelete;
    private String lockPassword = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lock_app, container, false);
        imgBack = binding.imgFragmentLockAppImgBack;
        edtPass = binding.edtpassLockApp;
        edtRepass = binding.edtRePassLockApp;
        btnLock = binding.btnFragmentLockAppBtnLock;
        txtDelete = binding.txtdelete;
        lockPassword = MySharedPreferences.readLockApp(getContext());
        if (lockPassword == null) {
            txtDelete.setTextColor(Color.GRAY);
            txtDelete.setEnabled(false);
        }

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


        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = MyUtils.persianToEnglish(edtPass.getText().toString());
                String rePass = MyUtils.persianToEnglish(edtRepass.getText().toString());
                if (password.isEmpty() && rePass.isEmpty()) {
                    edtPass.setError("فیلدهای مربوطه خالی است!");
                    edtRepass.setError("");
                } else if (password.isEmpty()) {
                    edtPass.setError("فیلدهای مربوطه خالی است!");

                } else if (rePass.isEmpty()) {
                    edtRepass.setError("فیلدهای مربوطه خالی است!");

                } else {
                    if (!password.equals(rePass)) {
                        Toast.makeText(getContext(), "رمز ورود با تکرار آن مطابقت ندارد!", Toast.LENGTH_SHORT).show();
                    } else {
                        MySharedPreferences.savedLockApp(getContext(), password);
                        Navigation.findNavController(btnLock).popBackStack();
                    }
                }


            }
        });

        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setView(R.layout.custom_layout_dialog_delete_lock_app);
                builder.setCancelable(false);
                Dialog dialog = builder.create();

                dialog.show();
                EditText edtDpass = dialog.findViewById(R.id.edtCustomDialogDeleteLock);
                CardView btnCancel = dialog.findViewById(R.id.cardDialog_custom_dialog_deleteLock_btn_cancel);
                CardView btnConfirm = dialog.findViewById(R.id.cardDialog_custom_dialog_deleteLock_btn_confirm);

                btnCancel.setOnClickListener(v1 -> dialog.dismiss());
                btnConfirm.setOnClickListener(v12 -> {

                    String lockApp = MySharedPreferences.readLockApp(getContext());
                    String pass = MyUtils.persianToEnglish(edtDpass.getText().toString());

                    if (!pass.equals(lockApp)) {
                        Toast.makeText(getContext(), "رمز عبور اشتباه است!", Toast.LENGTH_SHORT).show();
                    } else {
                        dialog.dismiss();
                        MySharedPreferences.removeLockApp(getContext());
                        txtDelete.setTextColor(Color.GRAY);
                        txtDelete.setEnabled(false);
                        Toast.makeText(getContext(), "رمز عبور غیر فعال شد", Toast.LENGTH_SHORT).show();
                    }

                });


            }
        });
    }

}
