package com.tijani.rezaguestmvvm.util;

import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class MyUtils {

    public static void setCustomAnimation(Techniques techniques, Long duration, View view){
        YoYo.with(techniques)
                .duration(duration)
                .repeat(1)
                .playOn(view);
    }


    public static String persianToEnglish(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            chars[i] = ch;
        }
        return new String(chars);
    }


    public static String number2persian(String text) {
        text = text.replaceAll("0", "۰");
        text = text.replaceAll("1", "۱");
        text = text.replaceAll("2", "۲");
        text = text.replaceAll("3", "۳");
        text = text.replaceAll("4", "۴");
        text = text.replaceAll("5", "۵");
        text = text.replaceAll("6", "۶");
        text = text.replaceAll("7", "۷");
        text = text.replaceAll("8", "۸");
        text = text.replaceAll("9", "۹");
        return text;
    }

    public static String splitPrice(int number) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat();
            DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols();
            decimalFormatSymbol.setGroupingSeparator(',');
            decimalFormat.setDecimalFormatSymbols(decimalFormatSymbol);
            return decimalFormat.format(number);
        } catch (Exception ex) {
            return String.valueOf(number);
        }
    }



}
