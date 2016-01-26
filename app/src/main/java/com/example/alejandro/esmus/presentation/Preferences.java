package com.example.alejandro.esmus.presentation;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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
    public final static String EXTRA_FILEDOWNLOAD="eus.ehu.intel.tta.esmus.isDownload";
    public final static String EXTRA_LAST="eus.ehu.intel.tta.esmus.last";

    SharedPreferences pref;

    public Preferences(Context context) {

        pref=context.getSharedPreferences(SH_PREFF,Context.MODE_PRIVATE);
    }

    public ArrayList<String> readPref()
    {
        ArrayList<String> login=new ArrayList<String>();
        login.add(pref.getString(EXTRA_LOGIN_N, null));
        login.add(pref.getString(EXTRA_LOGIN_S, null));
        login.add(pref.getString(EXTRA_COUNTRY, null));
        return login;
    }
    public Boolean isDownload()
    {
        Boolean i=pref.getBoolean(EXTRA_FILEDOWNLOAD,false);
        Log.d("mensaje", "isdownload");
        return i;
    }
    public void writePreff(ArrayList<String> login)
    {
        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_LOGIN_N,login.get(0));
        editor.putString(EXTRA_LOGIN_S,login.get(1));
        editor.putString(EXTRA_COUNTRY,login.get(2));
        editor.commit();
    }
    public void setDownload()
    {

        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean(EXTRA_FILEDOWNLOAD, true);
        editor.commit();
        Log.i("esmus", "estamos en el set");
    }

  public void offDowload(){

      SharedPreferences.Editor editor=pref.edit();
      editor.putBoolean(EXTRA_FILEDOWNLOAD, false);
      editor.commit();
  }

    public void cambiarNombre(String login){

        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_LOGIN_N, login);
        editor.commit();

    }

    public void setLast()
    {
        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean(EXTRA_LAST, true);
        editor.commit();
    }

    public void offLast(){

        SharedPreferences.Editor editor=pref.edit();
        editor.putBoolean(EXTRA_LAST, false);
        editor.commit();
    }
    public Boolean getLast()
    {
        return pref.getBoolean(EXTRA_LAST,false);
    }

    public void cambiarApellido(String apellido){

        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_LOGIN_S,apellido);
        editor.commit();

    }

    public void cambiarCiudad(String ciudad){

        SharedPreferences.Editor editor=pref.edit();
        editor.putString(EXTRA_COUNTRY,ciudad);
        editor.commit();

    }
}
