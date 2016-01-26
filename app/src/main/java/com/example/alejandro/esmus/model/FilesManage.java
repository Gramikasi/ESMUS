package com.example.alejandro.esmus.model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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
        //Eliminar dependencias con vista!!! Quitar todos los context

        public boolean writeJson(String jsonArray,OutputStream outputStream)
        {
                Boolean result=false;
                try
                {
                        OutputStreamWriter fout= new OutputStreamWriter(outputStream);
                        Log.i("esmus","Last json en writeJson:"+jsonArray.toString());
                        fout.write(jsonArray);
                        fout.close();
                        result=true;
                }
                 catch (Exception e)
                 {
                         Log.e("esmus", "Error al escribir fichero a memoria interna");
                }

                return result;
        }

        public String readJson(InputStream inputStream)
        {
                String jsonArray=null;

                try
                {
                        BufferedReader fin = new BufferedReader(new InputStreamReader(inputStream));
                        jsonArray = fin.readLine();
                        fin.close();
                }
                catch (Exception ex)
                {
                        Log.e("esmus", "Error al leer fichero desde memoria interna");
                }

                return jsonArray;

        }

        //guardar ficheros de audio
        public String writeAudio( byte[] audio, String pathFichero, String nombreAudio) throws IOException {

                pathFichero= String.format("%s/%s", pathFichero,nombreAudio);
                FileOutputStream fos=new FileOutputStream(pathFichero);
                Log.e("esmus", "writeAudio " + pathFichero);
                DataOutputStream dos=new DataOutputStream(fos);
                dos.write(audio);
                dos.close();
                fos.close();
                return pathFichero;

        }





}
