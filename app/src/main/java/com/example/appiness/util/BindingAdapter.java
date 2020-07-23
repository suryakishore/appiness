package com.example.appiness.util;

import android.view.View;


public class BindingAdapter {


    @androidx.databinding.BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean value) {
        if (view != null) {
            view.setVisibility(value ? View.VISIBLE : View.GONE);
        }
    }
}
