package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class horaire_requete extends AppCompatActivity implements View.OnClickListener{

    private Button bou_res = null;
    private EditText champ_ligne;
    private EditText champ_arret;
    private EditText champ_direction;
    String ligne = null;
    String arret = null;
    String direction = null;
    ArrayList<String> listeRoute = new ArrayList<>();
    ArrayList<String> listeStop = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_requete);
        bou_res = (Button) findViewById(R.id.bou_cher_hor);
        bou_res.setOnClickListener(this);
        champ_ligne = (EditText) findViewById(R.id.horaire_ligne);
        champ_direction = (EditText) findViewById(R.id.champ_direction);
        listeRoute = getIntent().getStringArrayListExtra("route");
        listeStop = getIntent().getStringArrayListExtra("stop");


        final AutoCompleteTextView ligne_demandee = (AutoCompleteTextView) findViewById(R.id.horaire_ligne);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listeRoute);
        ligne_demandee.setAdapter(adapter);

        /*final AutoCompleteTextView arret_demande = (AutoCompleteTextView) findViewById(R.id.horaire_arret);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listeStop);
        arret_demande.setAdapter(adapter2);*/

    }


    public void onClick(View v) {
        try {
            if (v.getId() == R.id.bou_cher_hor) {
                ligne = champ_ligne.getText().toString();
                arret = champ_arret.getText().toString();
                direction = champ_direction.getText().toString();
                Intent intent = new Intent(this, horaire_resultat.class);
                intent.putExtra("ligne", ligne);
                intent.putExtra("arret", arret);
                intent.putExtra("direction", direction);
                startActivity(intent);
            }
        }
        catch (Exception e){
            Toast.makeText(this, "erreur", Toast.LENGTH_LONG).show();
        }
    }
}