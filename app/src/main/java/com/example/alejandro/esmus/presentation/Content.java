package com.example.alejandro.esmus.presentation;

import android.os.Bundle;
import android.util.Log;

import com.example.alejandro.esmus.connection.RestClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Miren on 04/01/2016.
 */
public  class Content {

    private final Bundle bundle;

    private final static String EXTRA_CONTENIDO = "com.example.alejandro.esmus.extra_contenido";
    private final static String EXTRA_INDICE_TEMATICA="com.example.alejandro.esmus.indicetematica";
    private final static String EXTRA_INDICE_REGISTRO="com.example.alejandro.esmus.indiRegistro";
    private final static String EXTRA_INDICE_FRASE="com.example.alejandro.esmus.indicefrase";
    private final static String EXTRA_LAST="com.example.alejandro.esmus.last";
    private final static String EXTRA_CONSEJOS="com.example.alejandro.esmus.consejos";
    private final static String EXTRA_CONSEJOS_POSICION="com.example.alejandro.esmus.consejo_posicion";


    public void putConsejoPost(int i){
        bundle.putInt(EXTRA_CONSEJOS_POSICION, i);

    }


    //guarda la lista de consejos
    public void putExtraConsejos(String consejos){

        bundle.putString(EXTRA_CONSEJOS,consejos);

    }

    public ArrayList<String> getListaSubBotones() throws JSONException {

        ArrayList<String> salida=new ArrayList<>();
        JSONArray jsonArray= new JSONArray(bundle.getString(EXTRA_CONSEJOS));

        JSONObject jsonObject= jsonArray.getJSONObject(bundle.getInt(EXTRA_CONSEJOS_POSICION));
        JSONArray subBotones= jsonObject.getJSONArray("subBotones");

        for(int i=0; i<subBotones.length();i++){
            salida.add(subBotones.getJSONObject(i).getString("nombre"));
        }

        return salida;

    }

    public String getURL(int position) throws JSONException {

        String salida=null;
        JSONArray jsonArray= new JSONArray(bundle.getString(EXTRA_CONSEJOS));
        JSONObject jsonObject= jsonArray.getJSONObject(bundle.getInt(EXTRA_CONSEJOS_POSICION));
        JSONArray subBotones= jsonObject.getJSONArray("subBotones");

        salida=subBotones.getJSONObject(position).getString("url");


        return salida;

    }

    //devuelve los titulos de los botones de la pagina principal de consejos
    public ArrayList<String> getBotonesConsejo() throws JSONException {

        ArrayList<String> salida=new ArrayList<>();
        JSONArray jsonArray= new JSONArray(bundle.get(EXTRA_CONSEJOS).toString());
        Log.e("esmus",jsonArray.toString()+ "Estamos en getBotonesConsejo");


        for(int i=0; i<jsonArray.length();i++){

            String string= jsonArray.getJSONObject(i).getString("tituloBoton");
            salida.add(string );
            Log.e("esmus", string);

        }

        return salida;
    }


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
    public ArrayList<String> getRegistros()
    {
        ArrayList<String> registros=new ArrayList<>();
        String datos=bundle.getString(EXTRA_CONTENIDO);
        try {

            JSONArray contenido = new JSONArray(datos);
            JSONArray registro=contenido.getJSONObject(getExtraIndiceTematica()).getJSONArray("subtemas");

            for (int i=0;i<registro.length();i++)
            {
                registros.add(registro.getJSONObject(i).getString("nombre"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public String getContenido(){

        return bundle.getString(EXTRA_CONTENIDO);
    }
    public String getPrepTematica()
    {
        String prep=null;
        String datos=bundle.getString(EXTRA_CONTENIDO);
        try {
            JSONArray contenido = new JSONArray(datos);
            prep=contenido.getJSONObject(getExtraIndiceTematica()).getString("prep");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prep;
    }
    public ArrayList<String> getFrases()
    {
        ArrayList<String> frases=new ArrayList<String>();
        String datos=bundle.getString(EXTRA_CONTENIDO);

        try {

            JSONArray contenido = new JSONArray(datos);
            JSONArray jfrases=contenido.getJSONObject(getExtraIndiceTematica()).getJSONArray("subtemas").getJSONObject(getExtraIndiceRegistro()).getJSONArray("frases");
            //JSONArray jfrases=registros.getJSONObject(registro).getJSONArray("frases");
            Log.i("esmus","Tamaño de jfrases en conten getfrases"+jfrases.length());

            for (int i=0;i<jfrases.length(); i++) {

                String fraseOut=null;
                String frasei=jfrases.getJSONObject(i).getString("fr");
                if(frasei.length() > 35)
                    fraseOut= frasei.substring(0,35) + "...";
                else
                    fraseOut=frasei;
                frases.add(fraseOut);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return frases;
    }


    public String getFrase() throws JSONException {

        String frase=null;
        String datos=bundle.getString(EXTRA_CONTENIDO);
        JSONArray jsonArray=new JSONArray(datos.toString());
        JSONArray frases=null;

        frases= jsonArray.getJSONObject(getExtraIndiceTematica()).
                getJSONArray("subtemas").getJSONObject(getExtraIndiceRegistro()).getJSONArray("frases");

        frase=frases.getJSONObject(getExtraIndiceFrase()).getString("fr");


        return frase;

    }

    public String getTraduccion() throws JSONException {

        String traduccion=null;

        String datos=bundle.getString(EXTRA_CONTENIDO);
        JSONArray jsonArray=new JSONArray(datos.toString());
        JSONArray frases=null;

        frases= jsonArray.getJSONObject(getExtraIndiceTematica()).
                getJSONArray("subtemas").getJSONObject(getExtraIndiceRegistro()).getJSONArray("frases");
        traduccion=frases.getJSONObject(getExtraIndiceFrase()).getString("tr");

        return traduccion;


    }

    public String getPath() throws JSONException {
        String path=null;

        String datos=bundle.getString(EXTRA_CONTENIDO);
        JSONArray jsonArray=new JSONArray(datos);
        path= jsonArray.getJSONObject(getExtraIndiceTematica()).
                getJSONArray("subtemas").getJSONObject(getExtraIndiceRegistro()).
                getJSONArray("frases").getJSONObject(getExtraIndiceFrase()).getString("path");
        Log.i("esmus","recogendo path de descarga:"+path);

        return path;

    }


    public String getPathG() throws JSONException {


        String datos=bundle.getString(EXTRA_CONTENIDO);
        JSONArray jsonArray=new JSONArray(datos.toString());
        String pathg= jsonArray.getJSONObject(getExtraIndiceTematica()).
                getJSONArray("subtemas").getJSONObject(getExtraIndiceRegistro()).
                getJSONArray("frases").getJSONObject(getExtraIndiceFrase()).getString("pathG");
        return pathg;


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

    public void guardarPathDescarga(String path){

        try {
            Log.i("esmus","guardando path de descarga:"+path);
            JSONArray jsonArray= new JSONArray(bundle.getString(EXTRA_CONTENIDO));

            jsonArray.getJSONObject(
                    getExtraIndiceTematica()).getJSONArray("subtemas").
                    getJSONObject(getExtraIndiceRegistro()).getJSONArray("frases").getJSONObject(getExtraIndiceFrase()).put("path",path);
            putContenido(jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void guardarPathGrabado(String path){

        try {
            JSONArray jsonArray= new JSONArray(bundle.getString(EXTRA_CONTENIDO));

         jsonArray.getJSONObject(
                 getExtraIndiceTematica()).getJSONArray("subtemas").
                 getJSONObject(getExtraIndiceRegistro()).getJSONArray("frases").getJSONObject(getExtraIndiceFrase()).put("pathG", path);
            putContenido(jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void putLast()
    {
        String last=bundle.getString(EXTRA_LAST,"[]");
        Log.i("esmus","sacando bundle:"+last);
        try {
            Log.i("esmus","preparando el borrado");
            JSONObject newJ=new JSONObject();
            newJ.put("tematica",getExtraIndiceTematica());
            newJ.put("registro",getExtraIndiceRegistro());
            newJ.put("frase", getExtraIndiceFrase());
            Log.i("esmus", "Antes del borrado: " + last);

            JSONArray jsonLast=new JSONArray(last);
            Log.i("esmus", "Antes del borrado: " + jsonLast.length());
            if(jsonLast.length()>10)
            {
                //TODO: hacer shift de consultas
                    jsonLast.remove(0);
                Log.i("esmus", "Despues del borrado: " + jsonLast.toString());
                Log.i("esmus", "despues del borrado: " + jsonLast.length());
                    jsonLast.put(newJ);
                Log.i("esmus", "Despues de añadir: " + jsonLast.toString());
                Log.i("esmus", "despues de añadir: " + jsonLast.length());

                /*for(int i=1;i<10;i++)
                {

                }*/
            }
            else{

                jsonLast.put(newJ);
            }
            Log.i("esmus","Antes de hacer el put:"+jsonLast.toString() );
            bundle.putString(EXTRA_LAST,jsonLast.toString());



        } catch (JSONException e) {
            e.printStackTrace();
        }



    }
    public ArrayList<String> getLast() throws JSONException{
        String last=bundle.getString(EXTRA_LAST,"[]");
        String datos=bundle.getString(EXTRA_CONTENIDO);
        JSONArray jsonArray=new JSONArray(datos.toString());
        ArrayList<String> stringLast=new ArrayList<String>();
        JSONArray jsonLast=new JSONArray(last);
        for(int i=0;i<jsonLast.length();i++)
        {
            stringLast.add(jsonArray.getJSONObject(jsonLast.getJSONObject(i).getInt("tematica")).
                    getJSONArray("subtemas").getJSONObject(jsonLast.getJSONObject(i).getInt("registro")).
                    getJSONArray("frases").getJSONObject(jsonLast.getJSONObject(i).getInt("frase")).getString("fr"));
        }


        return stringLast;
    }


    public void putIndices(int position)
    {
        String last=bundle.getString(EXTRA_LAST,"[]");
        try {
            JSONArray jsonLast=new JSONArray(last);
            putExtraIndiceTematica(jsonLast.getJSONObject(position).getInt("tematica"));
            putExtraIndiceRegistro(jsonLast.getJSONObject(position).getInt("registro"));
            putExtraIndiceFrase(jsonLast.getJSONObject(position).getInt("frase"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private final static String EXTRA_PATH_FOTOS="com.example.alejandro.esmus.fotos";

    public void guardarPathFotos(String strings){


        bundle.putString(EXTRA_PATH_FOTOS,strings);
        Log.i("esmus","bundle fotos guardado"+bundle.getString(EXTRA_PATH_FOTOS));
    }

    public ArrayList <String> getPathFotos(){

        String fotos= bundle.getString(EXTRA_PATH_FOTOS);
        Log.e("esmus","printando el path de fotos del bundle"+bundle.getString(EXTRA_PATH_FOTOS));
        fotos=fotos.substring(1,fotos.length()-1);
        String [] array=  fotos.split(", ");
        ArrayList arrayList= new ArrayList();
        for (String item : array){
            arrayList.add(item);
            Log.e("esmus","Estamos printando los items de fotos en getPathFotos"+item);
        }

        return arrayList;


    }


}
