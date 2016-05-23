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

        public static ArrayList<Stop> getStops(Context context) {
            ArrayList<Stop> stops = new ArrayList<Stop>();
            try {
                InputStream stream = context.getResources().openRawResource(R.raw.stops);
                Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
                CSVRecord enregistrementCourant = null;
                iterateur.next();
                while (iterateur.hasNext()) {
                    Stop stop = new Stop();
                    enregistrementCourant = iterateur.next();
                    stop.setStopId(enregistrementCourant.get(0));
                    stop.setStopCode(enregistrementCourant.get(1));
                    stop.setStopName(enregistrementCourant.get(2));
                    stop.setStopLat(enregistrementCourant.get(4));
                    stop.setStopLon(enregistrementCourant.get(5));
                    stops.add(stop);
                }
                stream.close();
            }
            catch (Exception e) {

            }
            return stops;
        }


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
