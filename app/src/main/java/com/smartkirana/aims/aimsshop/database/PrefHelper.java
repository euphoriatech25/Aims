package com.smartkirana.aims.aimsshop.database;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.smartkirana.aims.aimsshop.MyApp;

public class PrefHelper {

    private static SharedPreferences pref;
    private static Context context;

    public static void setUserDetails(String prefName, String prefValue) {
        pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(encrypt(prefName), encrypt(prefValue));
        editor.apply();
    }

    public static String getUserDetails(String prefName) {
        pref = MyApp.getContext().getSharedPreferences(prefName, Context.MODE_PRIVATE);
        String encryptedId = pref.getString(encrypt(prefName), encrypt("0"));
        return decrypt(encryptedId);
    }


    private static String encrypt(String input) {
        return Base64.encodeToString(input.getBytes(), Base64.DEFAULT);
    }

    private static String decrypt(String input) {
        return new String(Base64.decode(input, Base64.DEFAULT));
    }

    public static void setDeviceID(String randomNumber) {
//        SharedPreferences pref = MyApp.getContext().getSharedPreferences(Constants.DEVICE_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("regId", randomNumber);
        editor.apply();
    }

    public static String getDeviceID() {
//        SharedPreferences pref = MyApp.getContext().getSharedPreferences(Constants.DEVICE_ID, Context.MODE_PRIVATE);
        return pref.getString("regId", null);
    }

    public static void clearPreference(String[] prefArray) {
        for (int i = 0; i < prefArray.length; i++) {
            SharedPreferences clearPrefs = MyApp.getContext().getSharedPreferences(prefArray[i], Context.MODE_PRIVATE);
            clearPrefs.edit().clear().commit();
        }
    }
}
