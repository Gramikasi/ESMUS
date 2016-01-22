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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandro.esmus.presentation.Content;
import com.example.alejandro.esmus.vista.AudioPlayer;
import com.example.alejandro.esmus.vista.ListAdapter;
import com.example.alejandro.esmus.vista.ProgressTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static android.widget.Toast.*;

public class ShowPhraseActivity extends ModelActivity {


    //TODO: meter un acceso al home
    private LinearLayout layout;
    private String path;
    private static final int  AUDIO_REQUEST_CODE = 1;
    private Uri uri;
    String nombreF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_phrase);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final String nombreF=String.valueOf(content.getExtraIndiceTematica())
                +String.valueOf(content.getExtraIndiceRegistro())
                +String.valueOf(content.getExtraIndiceFrase())+".aac";


        TextView textView=(TextView)findViewById(R.id.phrase_texto);
        textView.setText("Frase y Traducción");



        ListView listView=(ListView)findViewById(R.id.listViewShowPhrases);
        ArrayList<String> frases=new ArrayList<>();
        try {
            frases.add(0,content.getFrase());
            frases.add(1,content.getTraduccion());
        } catch (JSONException e) {
            e.printStackTrace();
        }


        ListAdapter adapter=new ListAdapter(this.getApplicationContext(),frases);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        layout = (LinearLayout) findViewById(R.id.showPharase_activity_linear);

        final File dir = new File(Environment.getExternalStorageDirectory() + "/ESMUS/");
        final File aux= new File(Environment.getExternalStorageDirectory() + "/ESMUS/nombreF");


        try {
            if(content.getPath().length()<1  ||  !aux.exists() ){



                new ProgressTask<String>(this){



                    @Override
                    protected String  work() throws Exception {

                        Log.e("esmus","descargando audio");

                        path= filesManage.writeAudio(server.getAudio(nombreF),
                                dir.getAbsolutePath(),nombreF);

                        Log.i("esmus",nombreF);

                        return path;

                    }

                    @Override
                    protected void onFinish(String  result) {

                        try {

                            content.guardarPathDescarga(path);
                            filesManage.writeJson(content.getContenido(),
                                    context.getApplicationContext().openFileOutput("dataFile.json", Context.MODE_PRIVATE));
                            
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.execute();


            }else{
                path=content.getPath();
                Log.e("esmus","audio descargado de antes");

            }



        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void escucharAudio( View view){

        try {
            showAudio(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void escucharGrabacion(View view)
    {

        try {
            showAudio(content.getPathG());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
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
                Button button= (Button) findViewById(R.id.button3_showPhare);
                button.setVisibility(View.VISIBLE);
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


