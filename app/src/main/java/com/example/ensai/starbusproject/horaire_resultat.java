package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class horaire_resultat extends AppCompatActivity {

    private TextView res_ligne = null;
    private TextView res_arret = null;
    private TextView res_direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_resultat);
        Intent intent = getIntent();
        res_ligne = (TextView) findViewById(R.id.res_ligne);
        res_arret = (TextView) findViewById(R.id.res_arret);
        res_direction = (TextView) findViewById(R.id.res_direction);
        res_ligne.setText(intent.getStringExtra("ligne"));
        res_arret.setText(intent.getStringExtra("arret"));
        res_direction.setText(intent.getStringExtra("direction"));
    }
}
