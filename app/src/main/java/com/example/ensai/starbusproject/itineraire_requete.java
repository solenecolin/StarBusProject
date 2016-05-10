package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class itineraire_requete extends AppCompatActivity implements View.OnClickListener {

    Button bou_res = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire_requete);
        bou_res = (Button) findViewById(R.id.bou_itineraire_chercher);
        bou_res.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.bou_itineraire_chercher) {
            Intent i = new Intent(this, itineraire_resultat.class);
            startActivity(i);
        }
    }
}
