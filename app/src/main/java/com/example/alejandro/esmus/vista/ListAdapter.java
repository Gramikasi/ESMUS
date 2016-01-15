package com.example.alejandro.esmus.vista;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alejandro.esmus.R;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by alejandro on 15/01/16.
 */
public class ListAdapter extends ArrayAdapter<ArrayList<String>> {

    private ArrayList<String> data=null;

    public ListAdapter(Context context, ArrayList datos) {
        super(context, R.layout.list_item, datos);
        data=datos;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.textView2);
        lblTitulo.setText(data.get(position).toString());
        /*int id=
        ImageView image = (ImageView)item.findViewById(R.id.imageView2);
        image.setImageResource();*/

        return(item);
    }


}
