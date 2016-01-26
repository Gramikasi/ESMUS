package com.example.alejandro.esmus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandro.esmus.model.Network;
import com.example.alejandro.esmus.vista.ListAdapter;
import com.example.alejandro.esmus.vista.ProgressTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class AdvisesActivity extends ModelActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advises);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        new ProgressTask<JSONArray>(this){

            protected JSONArray work() throws  IOException{

                if(Network.isConnected(context)) {
                    return server.getJson("consejos.json");
                }
                else {

                    Toast.makeText(context.getApplicationContext(),"No tienes conexion a internet!",Toast.LENGTH_LONG).show();
                    return null;
                }

            }


            protected void onFinish(JSONArray result){

                if(result!=null){

                    Log.e("esmus","Descargado consejos"+ result.toString());
                    content.putExtraConsejos(result.toString());

                    TextView textView=(TextView)findViewById(R.id.phrase_consejo);
                    textView.setText("Lista De Consejos");


                    ListView listView=(ListView)findViewById(R.id.listViewAdvise);
                    ArrayList<String> consejos= null;
                    try {
                        consejos = content.getBotonesConsejo();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    ListAdapter adapter=new ListAdapter(context.getApplicationContext(),consejos);
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            content.putConsejoPost(position);
                            startModelActivity(ShowAdviceActivity.class);
                        }
                    });






                }else{
                    startModelActivity(MainActivity.class);
                }


            }

        }.execute();


    }



}
