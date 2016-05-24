package com.example.ensai.starbusproject;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class activity_maps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    MyLocationListener myLocationListener  = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        myLocationListener = new MyLocationListener();
        mapFragment.getMapAsync(this);

    }

    public void updatePosition( double latitude, double longitude){
        mMap.addMarker(new MarkerOptions().position(new LatLng(latitude, longitude)).title("Vous êtes ici"));
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        myLocationListener.update(this, this);
        LatLng here = new LatLng(48.01,-1.7);
        Log.i("location", String.valueOf(myLocationListener.getLatitude()));
        mMap.addMarker(new MarkerOptions().position(here).title("Vous êtes là"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        afficherStationsProches(mMap, here);
    }

    private void afficherStationsProches(GoogleMap googleMap, LatLng here) {
        mMap = googleMap;

        StopDAO stopBDD = new StopDAO();
        ArrayList<Stop> stops = stopBDD.getStops(this);
        Location locationUser = new Location("Test");
        locationUser.setLatitude(here.latitude);
        locationUser.setLongitude(here.longitude);


        LatLng latlngProche = new LatLng(5.01,-17);
        Location locationProche = new Location("Test");
        locationProche.setLatitude(latlngProche.latitude);
        locationProche.setLongitude(latlngProche.longitude);
        for (int i = 0; i < stops.size(); i++) {

                Stop stopactuel = stops.get(i);
                String latitude = stopactuel.getStopLat();
                String longitude = stopactuel.getStopLon();
                LatLng locationStation1 = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
            Location locationStation = new Location("Test");
            locationStation.setLatitude(locationStation1.latitude);
            locationStation.setLongitude(locationStation1.longitude);
            Object distance = null;
            Object distanceProche = null;
            if(
                    locationUser.distanceTo(locationProche) >locationUser.distanceTo(locationStation)){
                locationProche = locationStation;
            }
        }
        LatLng latlngPlusProche = new LatLng(locationProche.getLatitude(), locationProche.getLongitude());
        mMap.addMarker(new MarkerOptions().position(latlngPlusProche).title("Station proche"));
    }

    private String readStream(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String reponse = "";
        String ligne = null;
        while ((ligne = reader.readLine()) != null) {
            reponse += ligne;
        }
        return reponse;
    }

    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) { //PAS AU BON ENDROIT
        switch (requestCode) {
            case MyLocationListener.MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    myLocationListener.accessFineLocationPermissionGot();
                    this.updatePosition(myLocationListener.getLatitude(),myLocationListener.getLongitude());
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {
                    myLocationListener.checkPermission();
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}