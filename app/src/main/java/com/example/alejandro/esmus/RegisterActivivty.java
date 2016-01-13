package com.example.alejandro.esmus;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.AudioPlayer;

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

        Toast.makeText(this,content.getContenido().toString(),Toast.LENGTH_SHORT).show();
        Toast.makeText(this, content.getTematicas().toString(), Toast.LENGTH_SHORT).show();
        Toast.makeText(this, Integer.valueOf(content.getExtraIndiceTematica()).toString(), Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //probar descarga audio
                try {
                    String path;
                    path=filesManage.writeAudio( server.getAudio("000.aac"),getApplicationContext(),"000.aac");
                    showAudio(path);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();



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
