package com.example.alejandro.esmus;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alejandro.esmus.vista.AudioPlayer;

import java.io.IOException;
import java.util.ArrayList;


public class RegisterActivivty extends ModelActivity {

    private LinearLayout layout;
////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activivty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<String> login=new ArrayList<>();
        layout = (LinearLayout) findViewById(R.id.register_activity_linear);
        login=pref.readPref();
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
        //logica mostrar registros
        TextView textView=(TextView)findViewById(R.id.welcome_message_register);

        textView.setText("Hola " + login.get(0) + " " + login.get(1) + " has venido a " + login.get(2) + " de visita!Quizas podria ayudarte a comunicarte en alguno de estos sitios!");

        ArrayList<String> registros=content.getRegistros(content.getExtraIndiceTematica());

        //ListView listView=(ListView)findViewById(R.id.listView);
        //final ArrayList mLista = new ArrayList();
        //final ArrayAdapter mAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mLista);
        //list.setAdapter(mAdapter);

        final View.OnClickListener listener= new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int i= (int) v.getTag();
                content.putExtraIndiceRegistro(i);
                startModelActivity(RegisterActivivty.class);

            }
        };
        LinearLayout list=(LinearLayout)findViewById(R.id.linearListview);
        int i=0;
        for (String registro : registros)
        {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            Button button=new Button(this);

            button.setText(registro);
            button.setTag(i);
            button.setOnClickListener(listener);
            button.setLayoutParams(params);
            list.addView(button);

            //  mLista.add(button);

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
}
