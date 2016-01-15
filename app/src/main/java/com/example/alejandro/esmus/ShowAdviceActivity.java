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

import com.example.alejandro.esmus.vista.AudioPlayer;
import com.example.alejandro.esmus.vista.ProgressTask;

import java.io.IOException;

public class ShowAdviceActivity extends ModelActivity {


    private LinearLayout layout;
    private String path="hola";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_advice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        new ProgressTask<String>(this){


            @Override
            protected String  work() throws Exception {

                path= filesManage.writeAudio( server.getAudio("000.aac"),getApplicationContext(),"000.aac");
                Log.e("esmus", path);

                return path;

            }

            @Override
            protected void onFinish(String  result) {

                try {
                    showAudio(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.execute();
    }

    private void showAudio(String nombre) throws IOException {
        View view = new View(this);
        AudioPlayer audio = new AudioPlayer(view);
        audio.setAudioUri(Uri.parse(nombre));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        layout.addView(view);
        audio.start();
    }
}


