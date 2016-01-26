package com.example.alejandro.esmus.vista;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
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
    private ArrayList<String> fotos=null;
    boolean imagenes=false;

  public ListAdapter(Context context, ArrayList datos,ArrayList<String> strings) {
        super(context, R.layout.list_item, datos);
        this.data=datos;
        this.fotos=strings;
        this.imagenes=true;
      
    }



    public ListAdapter(Context context, ArrayList datos) {
        super(context, R.layout.list_item, datos);
        this.data=datos;
        this.imagenes=false;


    }


    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.list_item, null);

        TextView lblTitulo = (TextView)item.findViewById(R.id.textView2);
        lblTitulo.setText(data.get(position).toString());

      if(imagenes) {
         ImageView image = (ImageView) item.findViewById(R.id.imageView2);

          Uri uri = Uri.parse(fotos.get(position));
          image.setImageURI(uri);
       }

        return(item);
    }


}
