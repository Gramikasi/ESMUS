package com.example.alejandro.esmus.presentation;

import android.os.Bundle;
import android.util.Log;

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

        if(bundle ==null) {
            bundle = new Bundle();
            Log.i("esmus","Creando nuevo bundle");

        }
        this.bundle = bundle;
    }


    public static String getExtraContenido() {
        return EXTRA_CONTENIDO;
    }

    public void putContenido(JSONArray jsonArray){

        bundle.putString(EXTRA_CONTENIDO, jsonArray.toString());
        //Log.i("esmus",jsonArray.toString());

    }


    public Bundle getBundle() {
        return bundle;
    }

    public ArrayList<String> getTematicas(){
        ArrayList<String> tematicas=new ArrayList<>();


        try {
            JSONArray contenido = new JSONArray(bundle.getString(EXTRA_CONTENIDO,"hola"));
            for (int i=0;i<contenido.length();i++)
            {

                    tematicas.add(contenido.getJSONObject(i).getString("nombre"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


        Log.e("esmus","Salida de la funcion getTematicas"+tematicas.toString());
        return tematicas;
    }
}
