package com.example.alejandro.esmus.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by alejandro on 28/12/15.
 */
public class FilesManage
{
        private String path=null;

        public FilesManage(String url)
        {
            path=url;
        }

        public String getPath() {
                return path;
        }

        public void setPath(String path) {
                this.path = path;
        }

        public FilesManage(){}


        public boolean writeJson(String jsonArray,Context context)
        {
                Boolean result=false;
                try
                {
                        OutputStreamWriter fout= new OutputStreamWriter(context.getApplicationContext().openFileOutput("dataFile.json", Context.MODE_PRIVATE));
                        fout.write(jsonArray);
                        fout.close();
                        result=true;
                }
                 catch (Exception e)
                 {
                         Toast.makeText(context,"Error al escribir fichero desde memoria interna",Toast.LENGTH_SHORT).show();
                         Log.e("esmus", "Error al escribir fichero a memoria interna");
                }

                return result;
        }

        public String readJson(Context context)
        {
                String jsonArray=null;

                try
                {
                        BufferedReader fin = new BufferedReader(new InputStreamReader(context.getApplicationContext().openFileInput("dataFile.json")));
                        jsonArray = fin.readLine();
                        fin.close();
                }
                catch (Exception ex)
                {
                        Toast.makeText(context,"Error al leer fichero desde memoria interna",Toast.LENGTH_SHORT).show();
                        Log.e("esmus", "Error al leer fichero desde memoria interna");
                }

                return jsonArray;

        }

        //guardar ficheros de audio
        public String writeAudio( byte[] audio, Context context, String nombreAudio) throws IOException {
                String pathFichero=null;
                FileOutputStream fos=context.getApplicationContext().openFileOutput(nombreAudio, Context.MODE_PRIVATE);
                Log.e("esmus","writeAudio "+ pathFichero);
                pathFichero=context.getFilesDir().getAbsolutePath();
                pathFichero.concat(nombreAudio);
                Log.e("esmus","writeAudio "+ pathFichero.concat(nombreAudio));
                DataOutputStream dos=new DataOutputStream(fos);
                dos.write(audio);
                dos.close();
                fos.close();

                return pathFichero.concat(nombreAudio);

        }




}
