package com.example.alejandro.esmus;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.ListAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class PhrasesActivity extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<String> login = new ArrayList<>();
        login = pref.readPref();




        TextView textView = (TextView) findViewById(R.id.welcome_message_phrases);

        textView.setText("Seleciona una frase:");

        ArrayList<String> frases = content.getFrases();

        ListView listView = (ListView) findViewById(R.id.listViewPhrases);
        //final ArrayList mLista = new ArrayList();
        //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
        //list.setAdapter(mAdapter);

        ListAdapter adapter = new ListAdapter(this.getApplicationContext(), frases);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                content.putExtraIndiceFrase(position);


                try {
                    content.putLast(dirLast);
                    pref.setLast();

                } catch (IOException e) {
                    e.printStackTrace();
                }
                startModelActivity(ShowPhraseActivity.class);
               // finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_activity_actions, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_home:
                startModelActivity(MainActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}