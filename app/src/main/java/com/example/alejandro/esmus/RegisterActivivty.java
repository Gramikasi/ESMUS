package com.example.alejandro.esmus;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.AudioPlayer;
import com.example.alejandro.esmus.vista.ProgressTask;

import java.io.IOException;


public class RegisterActivivty extends ModelActivity {


    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layout = (LinearLayout) findViewById(R.id.register_activity_linear);

        Toast.makeText(this, content.getContenido().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, content.getTematicas().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.valueOf(content.getExtraIndiceTematica()).toString(), Toast.LENGTH_SHORT).show();


    }


}
