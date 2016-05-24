package com.example.ensai.starbusproject;
import android.content.Context;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;


public class RouteDAO{

    public static ArrayList<Route> getRoutes(Context context) {
        ArrayList<Route> routes = new ArrayList<Route>();
        try {
            InputStream stream = context.getResources().openRawResource(R.raw.routes);
            Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
            CSVRecord enregistrementCourant = null;
            iterateur.next();
            while (iterateur.hasNext()) {
                Route route = new Route();
                enregistrementCourant = iterateur.next();
                route.setRouteId(enregistrementCourant.get(0));
                route.setRoute_short_name(enregistrementCourant.get(2));
                route.setRoute_long_name(enregistrementCourant.get(3));
                routes.add(route);
            }
            stream.close();
        }
        catch (Exception e) {

        }
        return routes;
    }


    public static ArrayList<String> getNomRoute(Context context){
        ArrayList<String> listeNomRoute = new ArrayList<String>();
        try {
            InputStream stream = context.getResources().openRawResource(R.raw.routes);
            Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
            CSVRecord enregistrementCourant = null;
            iterateur.next();
            while (iterateur.hasNext()) {
                String nomRoute = new String();
                enregistrementCourant = iterateur.next();
                nomRoute = enregistrementCourant.get(0);
                listeNomRoute.add(nomRoute);
            }
            stream.close();
        }
        catch (Exception e) {

        }
        return listeNomRoute;
    }
}