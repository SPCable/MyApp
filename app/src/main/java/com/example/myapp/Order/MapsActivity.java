package com.example.myapp.Order;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapp.R;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback{

    static MapsActivity INSTANCE;

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private Geocoder geocoder;
    private int ACCESS_LOCATION_REQUEST_CODE = 10001;
    FusedLocationProviderClient fusedLocationProviderClient;
    ImageView imgLocate;
    TextView txtlocation, txtOrderLocation;


    LocationRequest locationRequest;
    double a;
    double b;

    View mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        INSTANCE=this;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        imgLocate = (ImageView)findViewById(R.id.imgLocation);
        txtlocation = (TextView)findViewById(R.id.txtLocation);
        txtOrderLocation = (TextView)findViewById(R.id.tvLocation);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        //btn_get_my_location
        mapView = mapFragment.getView();
        //Location

        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

//        locationRequest = LocationRequest.create();
//        locationRequest.setInterval(200);
//        locationRequest.setFastestInterval(100);
//        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

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

        mMap.setPadding(0,0,0,580);

        getTapLocation(googleMap);



        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                enableUserLocation();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(a,b), 18));

                zoomToUserLocation(googleMap);
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_LOCATION_REQUEST_CODE);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public String getAddress(double lat, double lon){

        String address = "";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try{

            addresses = geocoder.getFromLocation(lat,lon,1);
            if(addresses.size() > 0)
            {
                address = addresses.get(0).getAddressLine(0);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;
    }


    private void getTapLocation(final GoogleMap googleMap) {

        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                LatLng tapLocation = googleMap.getCameraPosition().target;
                txtlocation.setText(getAddress(tapLocation.latitude,tapLocation.longitude));
                a=tapLocation.latitude;
                b=tapLocation.longitude;
                Log.d(TAG, "a: " + a);
                Log.d(TAG, "b: " + b);
            }
        });
    }

    private void enableUserLocation() {
        mMap.setMyLocationEnabled(true);
//        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setScrollGesturesEnabledDuringRotateOrZoom(false);
//        mMap.setBuildingsEnabled(false);
        mMap.getUiSettings().setTiltGesturesEnabled(false);



        if(mapView != null && mapView.findViewById(Integer.parseInt("1")) != null)
        {
            View locationBtn = ((View) mapView.findViewById(Integer.parseInt("1")).getParent()).findViewById(Integer.parseInt("2"));
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) locationBtn.getLayoutParams();
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            layoutParams.setMargins(0, 0, 0, 30);

        }
    }


    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            if(locationResult == null)
            {
                return;
            }
            for(Location location: locationResult.getLocations())
            {
                Log.d(TAG, "onLocationResult: " + location.toString());
            }
        }
    };

    private void startLocationUpdates(){
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void zoomToUserLocation(final GoogleMap googleMap){

        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED)
            {
                Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                    }
                });
            }
            else
            {
                Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();
                final SettingsClient client = LocationServices.getSettingsClient(this);
                locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {

                    @Override
                    public void onSuccess(Location location) {

                        if(location !=null)
                        {
                            LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();

                            Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
                            locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
                                @Override
                                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                                    startLocationUpdates();
                                }
                            });

                            if(location.getLatitude() != a && location.getLongitude() != b)
                            {
                                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                            }
                            else
                            {

                                LatLng latLng = new LatLng(a, b);
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
                                Log.d(TAG, "A: " + a);
                                Log.d(TAG, "B: " + b);
//                                googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
//                                    @Override
//                                    public void onCameraIdle() {
//                                        LatLng tapLocation = googleMap.getCameraPosition().target;
//                                        LatLng latLng = new LatLng(a, b);
//                                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
//                                    }
//                                });
                            }
                        }
                        else
                        {
                            Log.d(TAG, "onSuccess: Location was null............");
                        }
                    }
                });

                locationTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "onFailure: " + e.getLocalizedMessage());
                    }
                });
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == ACCESS_LOCATION_REQUEST_CODE)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                enableUserLocation();
                //zoomToUserLocation(googleMap);
            }
            else
            {

            }
        }
    }

    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }

    public void btnconfirmLocation(View view) {
        Intent i = new Intent();
        i.putExtra("message", txtlocation.getText().toString());
        setResult(RESULT_OK, i);
        finish();
    }

    //bấm btnShop: lấy data location
    public static MapsActivity getActivityInstance()
    {
        return INSTANCE;
    }

    public String getData()
    {
        String Data = txtlocation.getText().toString();
        return Data;
    }
    ////////////////////////////////////
}
