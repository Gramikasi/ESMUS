package com.example.alejandro.esmus;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.AudioPlayer;
import com.example.alejandro.esmus.vista.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class RegisterActivivty extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<String> login=new ArrayList<>();
        login=pref.readPref();
        ArrayList<String> tematicas=content.getTematicas();



        //logica mostrar registros
        TextView textView=(TextView)findViewById(R.id.welcome_message_register);

        textView.setText("Bien " + login.get(0)+ "! Te encuentras "+content.getPrepTematica()+" "+tematicas.get(content.getExtraIndiceTematica())+" ,¿Que quieres hacer?");

        ArrayList<String> registros=content.getRegistros();

        ListView listView=(ListView)findViewById(R.id.listViewRegister);
        //final ArrayList mLista = new ArrayList();
        //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
        //list.setAdapter(mAdapter);

        ListAdapter adapter=new ListAdapter(this.getApplicationContext(),registros);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                content.putExtraIndiceRegistro(position);
                startModelActivity(PhrasesActivity.class);
              //  finish();
            }
        });

    }




}
