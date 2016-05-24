package com.example.ensai.starbusproject;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
        LatLng here = new LatLng(myLocationListener.getLatitude() ,  myLocationListener.getLongitude());
        Log.i("location", String.valueOf(myLocationListener.getLatitude()));
        mMap.addMarker(new MarkerOptions().position(here).title("Vous êtes là"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(here));
        afficherStationsProches(mMap, here);
    }

    private void afficherStationsProches(GoogleMap googleMap, LatLng here) {
        mMap=googleMap;
        try {
            Requete requete = new Requete("getstation", "1.0");
            //http://data.keolis-rennes.com/json/?cmd=getstation&version=1.0&key=1RJLZ38TUFZSWTW&param[request]=proximity&param[mode]=coord&param[lat]=0&param[long]=0
            requete.addParam("request", "proximity");
            requete.addParam("mode", "coord");
            requete.addParam("lat", String.valueOf(here.latitude));
            requete.addParam("long", String.valueOf(here.longitude));
            HttpURLConnection urlConnection = (HttpURLConnection) new URL(requete.getURL()).openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String reponse = readStream(in);
            urlConnection.disconnect();
            JSONObject object = new JSONObject(reponse);
            final JSONArray array = object.getJSONObject("opendata").getJSONObject("answer").getJSONObject("data").getJSONArray("stopline");
            final List<Arret> arrets = new ArrayList<Arret>();
            for (int i = 0; i < array.length(); i++) {
                try {
                    JSONObject arretJSON = array.getJSONObject(i);
                    Arret arret = new Arret(arretJSON);
                    arrets.add(arret);
                    String stop = arret.getStop();
                    String latitude = null;
                    String longitude = null;
                    LatLng location = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                    mMap.addMarker(new MarkerOptions().position(location).title("Station proche"));
                }

                catch (JSONException e) {
                    Log.e("SBP", "Erreur", e);
                }
            }
            runOnUiThread(new Runnable() {

                @Override
                public void run() {
                }
            });
        }
        catch (Exception e) {
            Log.e("SBP", "Erreur",e);
        }
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