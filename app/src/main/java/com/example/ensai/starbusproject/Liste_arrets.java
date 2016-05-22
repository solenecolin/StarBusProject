package com.example.ensai.starbusproject;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.ensai.starbusproject.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Liste_arrets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_arrets);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        try {
            try {
                URL adresse = new URL("http://data.keolis-rennes.com/j" +
                        "son/?cmd=getbusnextdepartures&version=2.2&key=1RJLZ38TUFZSWTW&param[mode]=line&param[route]=0057&param[direction]=1");
                HttpURLConnection connection = (HttpURLConnection) adresse.openConnection();
                String contenu = readStream(connection.getInputStream());
                connection.disconnect();
            } catch (MalformedURLException e) {
                Log.e("tag", "URL invalide", e);
            }
        }
        catch(IOException e){
            Log.e("tag", "Pas de connexion réseau", e);
        }

    }
    public String readStream ( InputStream inputStream ) throws IOException {
        BufferedReader reader = new BufferedReader( new
                InputStreamReader( inputStream ) ) ;
        String ligne = null ;
        String contenu = "'";
        while ( ( ligne= reader.readLine() ) != null ) {
            contenu+= ligne;
        }
        return contenu ;
    }
}
