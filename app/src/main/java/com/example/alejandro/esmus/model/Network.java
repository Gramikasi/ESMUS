package com.example.alejandro.esmus.model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by alejandro on 13/01/16.
 */
public abstract class Network
{

    public static boolean isConnected(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] net = connec.getAllNetworkInfo();

        for (int i = 0; i < net.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (net[i].getState() == NetworkInfo.State.CONNECTED)
            {
                connected = true;
            }
        }
        return connected;
    }




}
