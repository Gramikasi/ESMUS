package com.example.alejandro.esmus;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import java.util.ArrayList;

import com.example.alejandro.esmus.model.Network;
import com.example.alejandro.esmus.vista.ProgressTask;


public class MainActivity extends ModelActivity
        implements NavigationView.OnNavigationItemSelectedListener {
        ArrayList<String> login=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login=pref.readPref();
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

           DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            final Toast aviso=Toast.makeText(this,"No tienes conexion a internet!",Toast.LENGTH_LONG);

        if (login.get(0)!=null)
        {
                //Descarga con progressTask
                new ProgressTask<JSONArray>(this){

                    @Override
                    protected JSONArray work() throws Exception {

                        if (pref.isDownload()) {

                            Log.e("esmus","leyendo fichero json");
                          JSONArray jsonArray=  new JSONArray(filesManage.readJson(context));

                            return jsonArray;
                        }else{
                            if(Network.isConnected(context)) {
                                Log.e("esmus", "descargando el json");
                                JSONArray jsonArray = server.getJson("dataFile.json");
                                filesManage.writeJson(jsonArray.toString(), context);
                                Log.i("esmus", "Antes de hacer el set" + pref.isDownload().toString());
                                pref.setDownload();

                                return jsonArray;
                                }
                                else{
                                aviso.show();
                                return null;
                            }
                        }

                    }

                    @Override
                    protected void onFinish(JSONArray result) {

                        if (result!=null)
                        {
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

                            final View.OnClickListener listener= new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {
                                    int i= (int) v.getTag();
                                    content.putExtraIndiceTematica(i);
                                    startModelActivity(RegisterActivivty.class);

                                }
                            };
                            LinearLayout list=(LinearLayout)findViewById(R.id.linearListview);
                            int i=0;
                            for (String tema : tematicas)
                            {

                                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);

                                Button button=new Button(context.getApplicationContext());

                                button.setText(tema);
                                button.setTag(i);
                                button.setOnClickListener(listener);
                                button.setLayoutParams(params);
                                list.addView(button);

                                //  mLista.add(button);

                            }

                            Log.i("esmus", "Despues de hacer el set" + pref.isDownload().toString());
                        }else
                        {
                            TextView textView=(TextView)findViewById(R.id.welcome_message_main);

                            textView.setText("Hola " + login.get(0) + " " + login.get(1) + " lo sentimos. No se ha podido desacargar la informacion necesaria, por favor conectate a internet y reinicia la aplicación.La descarga ocupa muy poco y solo se realiza 1 vez.Gracias!");

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
