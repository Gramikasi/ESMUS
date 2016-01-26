package com.example.alejandro.esmus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.alejandro.esmus.connection.RestClient;
import com.example.alejandro.esmus.model.Bussines;
import com.example.alejandro.esmus.model.FilesManage;
import com.example.alejandro.esmus.presentation.Content;
import com.example.alejandro.esmus.presentation.Preferences;

import java.io.File;

public abstract class ModelActivity extends AppCompatActivity {

    protected String URL="http://u017633.ehu.eus:18080/EsmusServer/contenido";//Introducir la url
    protected Preferences pref;
    protected Bussines server;
    protected Content content;
    protected RestClient restClient;
    protected FilesManage filesManage;
    File dirLast ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref = new Preferences(this);
        restClient = new RestClient(URL);
        server = new Bussines(restClient);
        content = new Content(getIntent().getExtras());
        filesManage = new FilesManage();


        //directorio propia de la app en memoria externa

        File dir = new File(Environment.getExternalStorageDirectory() + "/ESMUS/");
        // si no existe se crea
        if (!dir.exists()) {
            Log.e("esmus", "directorio esmus creado para memoria externa");
            dir.mkdir();
        }
        dirLast= new File(Environment.getExternalStorageDirectory() + "/ESMUS/lastPhrases.json");
    }

    protected <T> void startModelActivity(Class<T> cls){
        Intent intent= newIntent(cls);
        startActivity(intent);
    }

    protected <T> Intent newIntent(Class<T> cls){

        Intent intent= new Intent(getApplicationContext(),cls);
        intent.putExtras(content.getBundle());// metemos los datos de la actividad a si la nueva clase
        //que se crea coge los datos de la actividad, propagamos los datos
        return intent;
    }
}
