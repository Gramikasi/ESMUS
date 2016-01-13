package com.example.alejandro.esmus.connection;

/**
 * Created by alejandro on 28/12/15.
 */


import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.InflaterOutputStream;

public class RestClient {

    private final static String AUTH = "Authorization";
    private final String baseURL;//path al recurso
    private final Map<String, String> properties = new HashMap<>();

    public RestClient(String baseURL) {
        this.baseURL = baseURL;
    }

    public void setHttpBasicAuth(String user, String passwd) {

        String basicAuth = Base64.encodeToString(String.format("%s:%s", user, passwd).getBytes(), Base64.DEFAULT);
        // setProperty(getAuthorization(),String.format("Basic %s",basicAuth));
        properties.put(AUTH, String.format("Basic %s", basicAuth));

    }

    //pasamos entre actividades la autorizacion
    public String getAuthorization() {

        return properties.get(AUTH);
    }

    public void setAuthorization(String auth) {

        properties.put(AUTH, auth);
    }

    public void setProperty(String name, String value) {
        properties.put(name, value);
    }

    //funcion generica que devuleve una conexion
    private HttpURLConnection getConnection(String path) throws IOException {

        URL url = new URL(String.format("%s/%s", baseURL, path));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // for(Map.Entry<String,String> property : properties.entrySet())
        //      conn.setRequestProperty(property.getKey(),property.getValue());
        return conn;
    }

    public String getString(String path) throws IOException {


        HttpURLConnection conn = null;
        try {
            conn = getConnection(path);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                return br.readLine();
            }
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public JSONArray getJson(String path) throws IOException, JSONException {


        return new JSONArray(getString(path));
    }



    public byte[] getAudioBytes(String path){

        HttpURLConnection conn=null;
        byte[] data =null;
        try {
            conn= getConnection(path);
            Log.e("esmus","path de audios "+ path);

         int contentLength = conn.getContentLength();
            InputStream raw =conn.getInputStream();
            InputStream in = new BufferedInputStream(raw);
            data = new byte[contentLength];
            int bytesRead = 0;
            int offset = 0;
            while (offset < contentLength) {
                bytesRead = in.read(data, offset, data.length - offset);
                if (bytesRead == -1)
                    break;
                offset += bytesRead;
            }
            in.close();

            if (offset != contentLength) {
                throw new IOException("Solo lectura " + offset + " bytes; Experados " + contentLength + " bytes");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(conn!=null)
                conn.disconnect();
        }

        return data;


    }

    public byte[] getAudio(String path){

        return getAudioBytes(path);
    }



}

