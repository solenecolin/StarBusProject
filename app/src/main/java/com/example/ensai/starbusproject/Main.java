package com.example.ensai.starbusproject;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    Button bou_hor = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bou_hor = (Button) findViewById(R.id.bou_hor);
        bou_hor.setOnClickListener(new View.OnClickListener());
    }

    public void onClick(View v){
        if (v.getId()==R.id.bou_iti){
            Intent i = new Intent(this, Itineraire_requete.class);
            startActivity(i);
        }
        if (v.getId()==R.id.bou_hor){
            Intent i = new Intent(this, Horaire_requete.class);
            startActivity(i);
        }
    }
}
