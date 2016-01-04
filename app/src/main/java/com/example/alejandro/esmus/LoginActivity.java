package com.example.alejandro.esmus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends ModelActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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

    }
    //@Override
    public void saveLogin(View view)
    {
        //Toast.makeText(this,"Entramos en savelogin",Toast.LENGTH_SHORT).show();
        ArrayList<String> login=new ArrayList<String>();
        EditText name=(EditText)findViewById(R.id.login_name);
        EditText surname=(EditText)findViewById(R.id.login_surname);
        EditText country=(EditText)findViewById(R.id.login_country);
        login.add(name.getText().toString());
        login.add(surname.getText().toString());
        login.add(country.getText().toString());
        if (login.get(0).toString().length()>0&&login.get(1).toString().length()>0&&login.get(2).toString().length()>0){

            pref.writePreff(login);
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

         }else
        {
            TextView error=(TextView)findViewById(R.id.error_login);
            error.setVisibility(View.VISIBLE);
            Toast.makeText(this,"FALTAN DATOS",Toast.LENGTH_SHORT).show();
        }


    }

}
