package com.example.alejandro.esmus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class AjustesActivity extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void cambiarNombre(View view){


        EditText name=(EditText)findViewById(R.id.ajustes_name);
        pref.cambiarNombre(name.getText().toString());

    }

    public void cambiarApellido(View view){

        EditText apellido=(EditText)findViewById(R.id.ajustes_surname);
        pref.cambiarApellido(apellido.getText().toString());


    }
    public void cambiarCiudad(View view){

        EditText name=(EditText)findViewById(R.id.ajustes_country);
        pref.cambiarCiudad(name.getText().toString());


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


