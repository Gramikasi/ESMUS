package com.example.alejandro.esmus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class LastPhrases extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_phrases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        JSONArray last= null;
        try {
            last = content.getLast();
        } catch (JSONException e) {
            Toast.makeText(this,"Seha producido un error al conseguir las ultimas consultas",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        ArrayList<String> lastPhrases=null;
        ListView listView=(ListView)findViewById(R.id.listViewLast);
        //final ArrayList mLista = new ArrayList();
        //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
        //list.setAdapter(mAdapter);

        ListAdapter adapter=new ListAdapter(this.getApplicationContext(),lastPhrases);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                content.putExtraIndiceRegistro(position);
                startModelActivity(PhrasesActivity.class);
            }
        });
    }

}
