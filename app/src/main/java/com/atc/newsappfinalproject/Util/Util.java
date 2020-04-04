package com.atc.newsappfinalproject.Util;


import android.icu.text.SimpleDateFormat;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.text.ParseException;

public class Util {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String dateFormatted(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = null;

        try {
            date = format.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy");

        return dateFormat.format(date);

    }
}
