package com.example.ensai.starbusproject;


import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class StopDAO {

       /* public static ArrayList<Stop> getRoutes(Context context) {
            ArrayList<Route> routes = new ArrayList<Route>();
            try {
                InputStream stream = context.getResources().openRawResource(R.raw.routes);
                Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
                CSVRecord enregistrementCourant = null;
                iterateur.next();
                while (iterateur.hasNext()) {
                    Route route = new Route();
                    enregistrementCourant = iterateur.next();
                    route.setRoute_short_name(enregistrementCourant.get(2));
                    route.setRoute_long_name(enregistrementCourant.get(3));
                    routes.add(route);
                }
                stream.close();
            }
            catch (Exception e) {

            }
            return routes;
        }*/


        public static ArrayList<String> getNomStop(Context context){
            ArrayList<String> listeNomStop = new ArrayList<String>();
            try {
                InputStream stream = context.getResources().openRawResource(R.raw.stops);
                Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
                CSVRecord enregistrementCourant = null;
                iterateur.next();
                while (iterateur.hasNext()) {
                    String nomStop = new String();
                    enregistrementCourant = iterateur.next();
                    nomStop = enregistrementCourant.get(2);
                    listeNomStop.add(nomStop);
                }
                stream.close();
            }
            catch (Exception e) {

            }
            Set set = new HashSet() ;
            set.addAll(listeNomStop) ;
            listeNomStop = new ArrayList(set) ;
            return listeNomStop;
        }


}
