package com.tijani.rezaguestmvvm.view.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.daimajia.androidanimations.library.YoYo;
import com.tijani.rezaguestmvvm.R;

public class MainActivity extends AppCompatActivity {
    NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navController = Navigation.findNavController(this,R.id.fragmentHost1);

        NavigationUI.setupActionBarWithNavController(this,navController);
        getSupportActionBar().hide();



    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }





}