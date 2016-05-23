package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class itineraire_resultat extends AppCompatActivity {

    private TextView champ_depart = null;
    private TextView champ_arrivee = null;
    private TextView res_direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire_resultat);
        Intent intent = getIntent();
        champ_depart = (TextView) findViewById(R.id.champ_dir);
        champ_arrivee = (TextView) findViewById(R.id.champ_arr);
        champ_depart.setText(intent.getStringExtra("depart"));
        champ_arrivee.setText(intent.getStringExtra("arrivee"));
    }


}