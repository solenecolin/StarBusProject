package com.example.ensai.starbusproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class Main extends AppCompatActivity implements View.OnClickListener {

    Button bou_hor = null;
    Button bou_iti = null;
    Button bou_geo = null;
    ArrayList<String> listeNomRoute = new ArrayList<>();
    ArrayList<String> listeNomArret = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bou_hor = (Button) findViewById(R.id.bou_hor);
        bou_hor.setOnClickListener(this);
        bou_iti = (Button) findViewById(R.id.bou_iti);
        bou_iti.setOnClickListener(this);
        bou_geo = (Button) findViewById(R.id.bou_geo);
        bou_geo.setOnClickListener(this);
        ArrayList<Route> listeRoute = (RouteDAO.getRoutes(this));
        listeNomRoute = (RouteDAO.getNomRoute(this));
        listeNomArret = (StopDAO.getNomStop(this));
    }

    public void onClick(View v){
        if (v.getId()==R.id.bou_iti){
            Intent i = new Intent(this, itineraire_requete.class);
            i.putStringArrayListExtra("route", listeNomRoute);
            i.putStringArrayListExtra("stop", listeNomArret);
            startActivity(i);
        }
        if (v.getId()==R.id.bou_hor){
            Intent i = new Intent(this, horaire_requete.class);
            i.putStringArrayListExtra("route", listeNomRoute);
            i.putStringArrayListExtra("stop", listeNomArret);
            startActivity(i);
        }
        if (v.getId()==R.id.bou_geo){
           Intent i = new Intent(this, activity_maps.class);
            i.putStringArrayListExtra("route", listeNomRoute);
            i.putStringArrayListExtra("stop", listeNomArret);
           startActivity(i);
       }

    }
}
