package com.example.ensai.starbusproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class itineraire_requete extends AppCompatActivity implements View.OnClickListener {

    Button bou_res = null;
    String depart = null;
    String arrivee = null;
    private EditText champ_depart;
    private EditText champ_arrivee;
    EditText itineraire_heure = null;
    ArrayList<String> listeRoute = new ArrayList<>();
    ArrayList<String> listeStop = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itineraire_requete);
        bou_res = (Button) findViewById(R.id.bou_itineraire_chercher);
        listeRoute = getIntent().getStringArrayListExtra("route");
        listeStop = getIntent().getStringArrayListExtra("stop");
        champ_depart = (EditText) findViewById(R.id.itineraire_depart);
        champ_arrivee = (EditText) findViewById(R.id.itineraire_destination);
        itineraire_heure = (EditText) findViewById(R.id.itineraire_heure);

        final AutoCompleteTextView itineraire_depart = (AutoCompleteTextView) findViewById(R.id.itineraire_depart);
        ArrayAdapter<String> itineraire_depart_bdd = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listeStop);
        itineraire_depart.setAdapter(itineraire_depart_bdd);

        final AutoCompleteTextView itineraire_destination = (AutoCompleteTextView) findViewById(R.id.itineraire_destination);
        ArrayAdapter<String> itineraire_destination_bdd = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, listeStop);
        itineraire_destination.setAdapter(itineraire_destination_bdd);
        bou_res.setOnClickListener(this);
    }




    public void onClick(View v) {
        if (v.getId() == R.id.bou_itineraire_chercher) {
            depart = champ_depart.getText().toString();
            arrivee = champ_arrivee.getText().toString();
            Intent intent = new Intent(this, itineraire_resultat.class);
            intent.putExtra("depart", depart);
            intent.putExtra("arrivee", arrivee);
            startActivity(intent);
        }
    }

    /*public void choisirHeure(View v) {
        final TimePicker picker = new TimePicker(this);
        picker.setHour(11);
        picker.setIs24HourView(true);
        new AlertDialog.Builder(this).setView(picker).setNegativeButton("Annuler",null).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                itineraire_heure.setText(picker.getHour() + ":" + picker.getMinute());
            }
        }).show();
    }*/
}
