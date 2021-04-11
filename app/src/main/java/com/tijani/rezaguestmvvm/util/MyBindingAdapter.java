package com.tijani.rezaguestmvvm.util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class MyBindingAdapter {

    @BindingAdapter("image")
    public static void setImage(ImageView imageView,int img){
        imageView.setImageResource(img);
    }
}
