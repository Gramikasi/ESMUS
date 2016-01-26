package com.example.alejandro.esmus;

import android.os.Bundle;
import android.os.Environment;
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

        ArrayList<String> last=null;

        if (pref.getLast()) {
            try {

                last = content.getLast(dirLast);
                final JSONArray jsonArray = new JSONArray(last);

                ListView listView = (ListView) findViewById(R.id.listViewLast);
                //final ArrayList mLista = new ArrayList();
                //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
                //list.setAdapter(mAdapter);

                ListAdapter adapter = new ListAdapter(this.getApplicationContext(), last);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            content.putIndices(jsonArray.getJSONObject(position).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
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
