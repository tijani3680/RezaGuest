package com.tijani.rezaguestmvvm.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MySharedPreferences {

    public static void savedUserInformation(Context context, String userName, String shenaseh){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInformation",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName",userName);
        editor.putString("shenaseh",shenaseh);
        editor.apply();
    }

    public static String readUserName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInformation",0);
      String userName =  sharedPreferences.getString("userName",null);
      return userName;

    }

    public static String readShenaseh(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInformation",0);
        String shenaseh =  sharedPreferences.getString("shenaseh",null);
        return shenaseh;

    }

    public static void removeUserName(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("UserInformation",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("userName");
        editor.apply();
    }

    public static void savedLockApp(Context context,String password){
        SharedPreferences  sharedPreferences = context.getSharedPreferences("LockApp",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password",password);

        editor.apply();

    }

    public static String readLockApp(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LockApp",0);
        String password = sharedPreferences.getString("password",null);

        return password;
    }

    public static void removeLockApp(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("LockApp",0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("password");
        editor.apply();
    }

}
