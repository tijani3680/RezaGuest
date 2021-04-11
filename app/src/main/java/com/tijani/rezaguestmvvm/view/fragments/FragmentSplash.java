package com.tijani.rezaguestmvvm.view.fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tijani.rezaguestmvvm.R;
import com.tijani.rezaguestmvvm.databinding.FragmentSplashBinding;

public class FragmentSplash extends Fragment {
    FragmentSplashBinding binding;
    private RelativeLayout relError;
    private LinearLayout lilTry;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_splash,container,false);
        relError = binding.fragmentSplashRelError;
        lilTry = binding.lilTry;
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (isOnline()){
            NavDirections navDirections = FragmentSplashDirections.actionFragmentSplashToFragmentPrimitiveLogin();
            Navigation.findNavController(relError).navigate(navDirections);
        }else {
            relError.setVisibility(View.VISIBLE);
        }

        lilTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline()){
                    NavDirections navDirections = FragmentSplashDirections.actionFragmentSplashToFragmentPrimitiveLogin();
                    Navigation.findNavController(lilTry).navigate(navDirections);
                }else {
                    Toast.makeText(getContext(), "اینترنت خود را روشن کنید!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private boolean isOnline(){
        ConnectivityManager connMgr = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            connMgr = (ConnectivityManager) getActivity()
                    .getSystemService(getContext().CONNECTIVITY_SERVICE);
        }

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}