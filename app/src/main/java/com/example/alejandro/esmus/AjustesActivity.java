package com.example.alejandro.esmus;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.File;

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
        Toast.makeText(this,"Nombre cambiado a "+ name.getText(),Toast.LENGTH_LONG).show();

    }

    public void cambiarApellido(View view){

        EditText apellido=(EditText)findViewById(R.id.ajustes_surname);
        pref.cambiarApellido(apellido.getText().toString());
        Toast.makeText(this,"Apellido cambiado a "+ apellido.getText(),Toast.LENGTH_LONG).show();


    }
    public void cambiarCiudad(View view){

        EditText name=(EditText)findViewById(R.id.ajustes_country);
        pref.cambiarCiudad(name.getText().toString());
        Toast.makeText(this,"Ciudad cambiado a "+ name.getText(),Toast.LENGTH_LONG).show();


    }

    public void menu(View view){

        startModelActivity(MainActivity.class);
    }
    public void borrarDatos(View view){

        File dir = new File(Environment.getExternalStorageDirectory() + "/ESMUS/");
        BorradoRecursivo(dir);
        this.getApplicationContext().getFilesDir().delete();
              pref.offDowload();
             pref.offLast();

        Toast.makeText(this,"Introduce tus nuevos datos",Toast.LENGTH_LONG).show();
        startModelActivity(LoginActivity.class);

    }

    void BorradoRecursivo(File Directory) {
        if (Directory.isDirectory()) {
            for (File child : Directory.listFiles()) {
                BorradoRecursivo(child);
            }
        }

        Directory.delete();
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


