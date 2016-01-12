package com.example.alejandro.esmus;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends ModelActivity
        implements NavigationView.OnNavigationItemSelectedListener {

        ArrayList<String> login=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=pref.readPref();
       // Toast.makeText(this,login.get(0)+login.get(1)+login.get(2), Toast.LENGTH_SHORT).show();

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);



           DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

        if (login.get(0)!=null)
        {
            if (pref.isDownload().compareToIgnoreCase("1")==0){
                Toast.makeText(this,"dfichero ya descargado",Toast.LENGTH_SHORT).show();
                //llamar a la clase que lee el fichero
                //y hacer un put en el content del fichero

                try {
                    content.putContenido(server.getJson("prueba"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

                //Descarga con progressTask
                new ProgressTask<JSONArray>(this){


                    @Override
                    protected JSONArray work() throws Exception {

                       Log.e("esmus","descargando el json");

                        return server.getJson("dataFile.json");


                    }

                    @Override
                    protected void onFinish(JSONArray result) {

                        Log.e("esmus", "añadiendo cotenido");
                        content.putContenido(result);

                        TextView textView=(TextView)findViewById(R.id.welcome_message_main);

                        textView.setText("Hola " + login.get(0) + " " + login.get(1) + " has venido a " + login.get(2) + " de visita!Quizas podria ayudarte a comunicarte en alguno de estos sitios!");

                        ArrayList<String> tematicas=content.getTematicas();
                        Toast.makeText(context.getApplicationContext(),tematicas.toString(),Toast.LENGTH_SHORT).show();

                        //ListView listView=(ListView)findViewById(R.id.listView);



                        //final ArrayList mLista = new ArrayList();
                        //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
                        //list.setAdapter(mAdapter);


                        LinearLayout list=(LinearLayout)findViewById(R.id.linearListview);

                        for (String tema : tematicas)
                        {

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT);

                            Button button=new Button(context.getApplicationContext());

                            button.setText(tema);

                            button.setLayoutParams(params);
                            list.addView(button);

                            //  mLista.add(button);

                        }















                    }
                }.execute();


               // Toast.makeText(this,content.getTematicas().toString(),Toast.LENGTH_SHORT).show();

               /* try {
                    content.putContenido(server.getJson("prueba"));
                    Log.i("esmus", content.getTematicas().toString());
                    Toast.makeText(this,content.getTematicas().toString(),Toast.LENGTH_SHORT).show();


                } catch (IOException e) {
                    e.printStackTrace();}*/


            }



        }else
        {
            startModelActivity(LoginActivity.class);

        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
