//Name: Brandon Fowler
//Assignment: Lab9
//Class: CSCD372
//Quarter: Fall 2015

package com.fowler.brandon.bafowlerlab9;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class baFowlerLab9 extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener {

    private GoogleMap mMap;
    LocationManager locationMgr;
    LatLng loc;
    Float zoom = new Float(15);
    int markCount = 0;
    ArrayList<MarkerOptions> markers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ba_fowler_lab9);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button plus = (Button) findViewById(R.id.zoomButton);
        Button minus = (Button) findViewById(R.id.unZoomButton);
        Button mapType = (Button) findViewById(R.id.changeButton);
        Button mark = (Button) findViewById(R.id.markButton);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        mapType.setOnClickListener(this);
        mark.setOnClickListener(this);
        if(savedInstanceState != null){
            zoom = savedInstanceState.getFloat("zoom");
            markCount = savedInstanceState.getInt("markCount");
            markers = (ArrayList<MarkerOptions>) savedInstanceState.getSerializable("markers");
        }
        else{
            markers = new ArrayList<MarkerOptions>();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMyLocationEnabled(true);
        getLocation();
        if(loc != null) {
            CameraUpdate CU = CameraUpdateFactory.newLatLngZoom(loc, zoom);
            mMap.moveCamera(CU);
        }
        for (int i = 0; i < markCount; i++) {
            MarkerOptions mk = markers.get(i);
            mMap.addMarker(mk);
        }
    }

    public void getLocation(){
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE) ;
        Location location = null;
        PackageManager pm = this.getPackageManager();
        if (pm.checkPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, this.getPackageName()) == PackageManager.PERMISSION_GRANTED){
            location = locationMgr.getLastKnownLocation(getProvider());
        }
        if(location == null){
            Toast.makeText(getApplicationContext(), "Last Location Retuned Null", Toast.LENGTH_SHORT).show();
            return;
        }
        loc = new LatLng(location.getLatitude(),location.getLongitude());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext(), "Android Lab 9, Brandon A. Fowler", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    public String getProvider(){
        Criteria criteria = new Criteria() ;
        criteria.setAccuracy(Criteria.ACCURACY_FINE) ;
        criteria.setSpeedRequired(true) ;
        String providerName =
                locationMgr.getBestProvider(criteria, true) ;
        if (providerName != null)
            return providerName ;
        else
            return LocationManager.GPS_PROVIDER ;
    }

    public void onPlus(View v) {
        mMap.moveCamera(CameraUpdateFactory.zoomIn()) ;
        zoom = mMap.getCameraPosition().zoom ;
    }
    public void onMinus(View v) {
        mMap.moveCamera(CameraUpdateFactory.zoomOut()) ;
        zoom = mMap.getCameraPosition().zoom ;
    }
    public void mapType(View v) {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else if(mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE){
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        }
        else if(mMap.getMapType() == GoogleMap.MAP_TYPE_TERRAIN){
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }
    public void mark(View v){
        getLocation();
        if(loc != null) {
            markCount++;
            Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.mapmarker);
            MarkerOptions mk = new MarkerOptions().position(loc).title("Mark" + markCount).icon(BitmapDescriptorFactory.fromBitmap(icon));
            mMap.addMarker(mk);
            markers.add(mk);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.zoomButton){
            onPlus(v);
        }
        if(v.getId() == R.id.unZoomButton){
            onMinus(v);
        }
        if(v.getId() == R.id.changeButton){
            mapType(v);
        }
        if(v.getId() == R.id.markButton){
            mark(v);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        if(markCount != 0){
            savedState.putSerializable("markers",markers);
            savedState.putInt("markCount", markCount);
            savedState.putFloat("zoom",zoom);
        }
    }
}
