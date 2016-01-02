package com.example.alejandro.esmus.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alejandro.esmus.ModelActivity;

import java.util.ArrayList;

/**
 * Created by alejandro on 28/12/15.
 */
public class Preferences {

    public final static String SH_PREFF="eus.ehu.intel.tta.esmus";
    public final static String EXTRA_LOGIN_N="eus.ehu.intel.tta.esmus.login_name";
    public final static String EXTRA_LOGIN_S="eus.ehu.intel.tta.esmus.login_surname";
    public final static String EXTRA_COUNTRY="eus.ehu.intel.tta.esmus.country";
    SharedPreferences pref;

    public Preferences(Context context) {

        pref=context.getSharedPreferences(SH_PREFF,Context.MODE_PRIVATE);
    }

    public ArrayList<String> readPref()
    {
        ArrayList<String> login=null;
        login.add(pref.getString(EXTRA_LOGIN_N, null));
        login.add(pref.getString(EXTRA_LOGIN_S, null));
        login.add(pref.getString(EXTRA_COUNTRY, null));
        return login;
    }
    public void writePreff(ArrayList<String> login)
    {
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_LOGIN_N,login.get(0));
        editor.putString(EXTRA_LOGIN_S,login.get(1));
        editor.putString(EXTRA_LOGIN_S,login.get(2));
        editor.commit();
    }
}
