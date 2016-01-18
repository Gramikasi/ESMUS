package com.example.alejandro.esmus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alejandro.esmus.presentation.Content;
import com.example.alejandro.esmus.vista.AudioPlayer;
import com.example.alejandro.esmus.vista.ProgressTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import static android.widget.Toast.*;

public class ShowPhraseActivity extends ModelActivity {


    private LinearLayout layout;
    private String path;
    private static final int  AUDIO_REQUEST_CODE = 1;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_phrase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        new ProgressTask<String>(this){

            //TODO: comprobacion si es el path es diferente de null
            //TODO: comprobar si existe el fichero y sino existe descargarlo

//TODO: meter un acceso al home
// TODO: arreglar el fallo del reproductor


            @Override
            protected String  work() throws Exception {
                String nombreF=String.valueOf(content.getExtraIndiceTematica())
                        +String.valueOf(content.getExtraIndiceRegistro())
                        +String.valueOf(content.getExtraIndiceFrase())+".aac";
               path= filesManage.writeAudio(server.getAudio(nombreF),
                        Environment.getExternalStorageDirectory().getAbsolutePath(),nombreF);

                Log.i("esmus",nombreF);

                return path;

            }

            @Override
            protected void onFinish(String  result) {

                try {
                    showAudio(path);
                    content.guardarPathDescarga(path);
                    filesManage.writeJson(content.getContenido(),
                            context.getApplicationContext().openFileOutput("dataFile.json", Context.MODE_PRIVATE));

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

    public void recordAudio(View view) {

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            makeText(this, R.string.no_micro, LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if(intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, AUDIO_REQUEST_CODE);

            } else {
                makeText(this, "no tienes aplicacion disponible", LENGTH_SHORT).show();
            }
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case  AUDIO_REQUEST_CODE:

                            path=data.getDataString();
                    try {
                        showAudio(path);
                        content.guardarPathGrabado(path);
                        filesManage.writeJson(content.getContenido(),
                                this.getApplicationContext().openFileOutput("dataFile.json", Context.MODE_PRIVATE));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }
        }
    }



}


