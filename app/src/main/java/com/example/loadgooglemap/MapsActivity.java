package com.example.loadgooglemap;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.loadgooglemap.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng khair = new LatLng(27.939196, 77.842445);
        mMap.addMarker(new MarkerOptions().position(khair).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(khair));

// making circle on location
   //     mMap.addCircle(new CircleOptions()
     //           .center(khair)
       //         .radius(1000)
         //       .fillColor(Color.GREEN)
           //     .strokeColor(Color.RED));

        // over lay or set image
      //  mMap.addGroundOverlay(new GroundOverlayOptions()
         //       .position(khair,100f,500f)
           //     .image(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background)));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));

                Geocoder geocoder = new Geocoder(MapsActivity.this);
                try {
                    ArrayList<Address> arradr = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude,0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}