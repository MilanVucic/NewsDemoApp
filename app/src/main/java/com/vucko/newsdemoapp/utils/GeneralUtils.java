package com.vucko.newsdemoapp.utils;

import android.content.Context;
import android.widget.Toast;

public class GeneralUtils {
    public static void displayError(String errorMessage, Context context){
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
