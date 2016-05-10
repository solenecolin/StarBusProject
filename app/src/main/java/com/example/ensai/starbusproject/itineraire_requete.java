package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AutoCompleteTextView;

import static com.example.ensai.starbusproject.R.id.itineraire_depart;

public class itineraire_requete extends AppCompatActivity implements View.OnClickListener {

    Button bou_res = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire_requete);
        bou_res = (Button) findViewById(R.id.bou_itineraire_chercher);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(itineraire_depart);
        textView.setAdapter(adapter);
        bou_res.setOnClickListener(this);
    }

    private static final String[] COUNTRIES = new String[] {
            "Belgium", "France", "Italy", "Germany", "Spain"
    };



    public void onClick(View v) {
        if (v.getId() == R.id.bou_itineraire_chercher) {
            Intent i = new Intent(this, itineraire_resultat.class);
            startActivity(i);
        }
    }
}
