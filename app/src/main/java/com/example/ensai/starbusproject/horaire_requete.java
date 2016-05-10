package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class horaire_requete extends AppCompatActivity implements View.OnClickListener{

    private Button bou_res = null;
    private EditText champ_ligne;
    private EditText champ_arret;
    private EditText champ_direction;
    String ligne = null;
    String arret = null;
    String direction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_requete);
        bou_res = (Button) findViewById(R.id.bou_cher_hor);
        bou_res.setOnClickListener(this);
        champ_ligne = (EditText) findViewById(R.id.champ_ligne);
        champ_arret = (EditText) findViewById(R.id.champ_arret);
        champ_direction = (EditText) findViewById(R.id.champ_direction);
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
