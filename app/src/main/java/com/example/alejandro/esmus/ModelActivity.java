package com.example.alejandro.esmus;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.alejandro.esmus.connection.RestClient;
import com.example.alejandro.esmus.model.Bussines;
import com.example.alejandro.esmus.model.FilesManage;
import com.example.alejandro.esmus.presentation.Content;
import com.example.alejandro.esmus.presentation.Preferences;

public abstract class ModelActivity extends AppCompatActivity {

    protected String URL="http://158.227.56.114:8080/GramikasiServer/docs";//Introducir la url
    protected Preferences pref;
    protected Bussines server;
    protected Content content;
    protected RestClient restClient;
    protected FilesManage filesManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pref= new Preferences(this);
        restClient=new RestClient(URL);
        server= new Bussines(restClient);
        content= new Content(getIntent().getExtras());
        filesManage=new FilesManage();
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
