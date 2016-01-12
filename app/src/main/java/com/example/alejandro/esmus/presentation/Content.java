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
    private final static String EXTRA_INDICE_TEMATICA="com.example.alejandro.esmus.indicetematica";
    private final static String EXTRA_INDICE_REGISTRO="com.example.alejandro.esmus.indiRegistro";



    public void putExtraIndiceFrase(int i){

        bundle.putInt(EXTRA_INDICE_FRASE,i);

    }

    public void putExtraIndiceTematica(int i){

        bundle.putInt(EXTRA_INDICE_TEMATICA,i);

    }


    public void putExtraIndiceRegistro(int i){

        bundle.putInt(EXTRA_INDICE_REGISTRO,i);

    }




    public int getExtraIndiceFrase(){

      return   bundle.getInt(EXTRA_INDICE_FRASE);

    }

    public int getExtraIndiceTematica(){

       return  bundle.getInt(EXTRA_INDICE_TEMATICA);

    }


    public int getExtraIndiceRegistro(){

        return bundle.getInt(EXTRA_INDICE_REGISTRO);

    }



    private final static String EXTRA_INDICE_FRASE="com.example.alejandro.esmus.indicefrase";

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


    }

    public String getContenido(){

        return bundle.getString(EXTRA_CONTENIDO);
    }


    public Bundle getBundle() {
        return bundle;
    }

    public ArrayList<String> getTematicas(){
        ArrayList<String> tematicas=new ArrayList<>();


        String datos=bundle.getString(EXTRA_CONTENIDO);



        try {

           JSONArray contenido = new JSONArray(datos);

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
