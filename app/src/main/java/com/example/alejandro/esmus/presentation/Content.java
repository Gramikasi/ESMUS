package com.example.alejandro.esmus.presentation;

import android.os.Bundle;

import com.example.alejandro.esmus.connection.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Miren on 04/01/2016.
 */
public  class Content {

    private final Bundle bundle;

    private final static String EXTRA_CONTENIDO = "com.example.alejandro.esmus.extra_contenido";

    public Content(Bundle bundle) {

        if(bundle ==null)
            bundle = new Bundle();

        this.bundle = bundle;
    }


    public void putContenido(JSONArray jsonArray){

        bundle.putString(EXTRA_CONTENIDO, jsonArray.toString());

    }





    public void putContenido(){}

    public Bundle getBundle() {
        return bundle;
    }

    public ArrayList<String> getTematicas(){
        ArrayList<String> tematicas=new ArrayList<String>();


        JSONArray contenido= null;
        try {
            contenido = new JSONArray(bundle.getString(EXTRA_CONTENIDO,null));
            for (int i=0;i<contenido.length();i++)
            {

                    tematicas.add(contenido.getJSONObject(i).getString("nombre"));

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return tematicas;
    }
}
