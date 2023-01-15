package com.example.sms;

import android.content.Context;
import android.widget.Toast;

import java.util.concurrent.ConcurrentHashMap;

public class Tools {
    public static Context context;
    public static void showMessage(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
