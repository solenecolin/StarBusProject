package com.example.ensai.starbusproject;

import java.io.Serializable;

/**
 * Created by ensai on 10/05/16.
 */

public class Route implements Serializable {
    public String route_short_name;
    public String route_long_name;


    public Route(){
    }

    public String getRoute_short_name(String route_short_name){
        return route_short_name;
    }
    public String setRoute_short_name(String route_short_name){
        return route_short_name;
    }

    public String getRoute_long_name(String route_long_name) {
        return route_long_name;
    }
    public String setRoute_long_name(String route_long_name){
        return route_long_name;
    }

}