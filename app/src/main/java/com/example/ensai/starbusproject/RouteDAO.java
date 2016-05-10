package com.example.ensai.starbusproject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import android.content.Context;


public class RouteDAO {

    public static List<Route> getRoutes(Context context) {
        List<Route> routes = new ArrayList<Route>();
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
    }
}

