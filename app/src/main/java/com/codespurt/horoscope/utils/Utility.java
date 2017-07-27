package com.codespurt.horoscope.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.codespurt.horoscope.pojo.HoroscopeData;
import com.google.gson.Gson;

/**
 * Created by CodeSpurt on 7/23/2017.
 */

public class Utility {

    private Context context;
    private Gson gson;

    public Utility(Context context) {
        this.context = context;
    }

    public boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public HoroscopeData parseData(String data) {
        gson = new Gson();
        try {
            return gson.fromJson(data, HoroscopeData.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String filterData(String s) {
        String s2 = s.replace("\\n", "");
        s2 = s2.replace("\\r", "");
        s2 = s2.replace("%", "");
        s2 = s2.replace("^", "");
        s2 = s2.replace("#", "");
        s2 = s2.replace("[", "");
        s2 = s2.replace("]", "");
        s2 = s2.replace("'", "");
        return s2.trim();
    }
}
