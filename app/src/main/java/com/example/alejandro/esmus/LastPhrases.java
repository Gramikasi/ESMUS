package com.example.alejandro.esmus;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class LastPhrases extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_phrases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<String> lastPhrases;
        String lastIndices;

        if (pref.getLast()) {
            try {
                Log.i("esmus","Estamos en last frases!!!!!");

                lastPhrases = content.getLast(dirLast);
                lastIndices= content.getLastIndices(dirLast);
                final JSONArray jsonLastIndices = new JSONArray(lastIndices);
                Log.i("esmus","Estamos en last frases!!!!!jsonarrayLAST="+lastIndices);



                ListView listView = (ListView) findViewById(R.id.listViewLast);
                Log.i("esmus","Creamos listview");
                ListAdapter adapter = new ListAdapter(this.getApplicationContext(), lastPhrases);
                listView.setAdapter(adapter);
                Log.i("esmus", "Antes del try");
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                           // Log.i("esmus", "Lo que se pasa al put position"+jsonArray.getJSONObject(position).toString());
                            content.putIndices(jsonLastIndices.getJSONObject(position));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("esmus","Excepcion en lastfrases");
                        }
                        startModelActivity(ShowPhraseActivity.class);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }else
        {
            Toast.makeText(this,"No existe ninguna consulta",Toast.LENGTH_LONG).show();
        }

    }

}
