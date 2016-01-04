package com.example.alejandro.esmus;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.alejandro.esmus.presentation.Preferences;

public abstract class ModelActivity extends AppCompatActivity {

    protected Preferences pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref= new Preferences(this);
    }

    public void changeView(){}//Funcion para cambiar la vista de cada actividad Necesita ser sobreescrita

    //public void saveLogin(View view){}
}
