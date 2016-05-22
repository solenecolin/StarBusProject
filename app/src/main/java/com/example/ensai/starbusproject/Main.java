package com.example.ensai.starbusproject;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*Bon j'ai envie de dire, le design est temporaire, mais comme on a que
        deux boutons pour l'instant on n'a pas trop de possibilités...*/
public class Main extends AppCompatActivity implements View.OnClickListener {

    Button bou_hor = null;
    Button bou_iti = null;
    Button bou_geo = null;
    private ArrayList<Route> listeRoute = new ArrayList<Route>();

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
        listeRoute = RouteDAO.getRoutes(this);
    }

    public void onClick(View v){
        if (v.getId()==R.id.bou_iti){
            Intent i = new Intent(this, itineraire_requete.class);
            Bundle b = new Bundle();
            b.putSerializable("key", listeRoute);
            i.putExtra("listeRoute", listeRoute);
            startActivity(i);
        }
        if (v.getId()==R.id.bou_hor){
            Intent i = new Intent(this, horaire_requete.class);
            Bundle b = new Bundle();
            b.putSerializable("key", listeRoute);
            i.putExtra("listeRoute", listeRoute);
            startActivity(i);
        }
      //  if (v.getId()==R.id.bou_geo){
        //    Intent i = new Intent(this, carte_localisation.class);
       //     startActivity(i);
      //  }

    }
}
