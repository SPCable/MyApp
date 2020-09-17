package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.nfc.Tag;
import android.os.Bundle;

import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapp.Account.TaiKhoan;
import com.example.myapp.Order.DatHang;
import com.example.myapp.Search.pro_search;
import com.example.myapp.TTSP.ttsanphamtt;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements FoodAdapter.OnFoodListener, BSAdapter.OnBestListener, SaleAdapter.OnSaleListener {
    Button btnOrder_main, btnMenu_main;
    RecyclerView foodRecycler;
    RecyclerView bannerRecycler;
    RecyclerView saleRecycler;
    RecyclerView bsRecycler;
    FoodAdapter foodAdapter;
    BannerAdapter bannerAdapter;
    SaleAdapter saleAdapter;
    BSAdapter bsAdapter;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    private ArrayList<Food_sp> foodList;
    private ArrayList<sale> sales;
    private ArrayList<bestseller> bestsellerArrayList;
    private ArrayList<banner> bannerArrayList;

    private long backPressedTime;
    private Toast backToast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth = firebaseAuth.getInstance();

        LoadFoods();
        LoadSales();
        LoadBanner();
        LoadBS();

        //Location

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);


    }

    private void LoadFoods() {
        foodList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("productList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Food_sp food_sp = ds.getValue(Food_sp.class);
                    System.out.println(food_sp.getIdFood().toString());
                    foodList.add(food_sp);
                }
                setFoodRecycler(foodList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    private void LoadSales() {
        sales = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("saleList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sales.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    sale sale = ds.getValue(sale.class);
                    sales.add(sale);
                }
                setSaleRecycler(sales);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void LoadBS() {
        bestsellerArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("bestsalerList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bestsellerArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    bestseller bsl = ds.getValue(bestseller.class);
                    bestsellerArrayList.add(bsl);
                }
                setBsRecycler(bestsellerArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void LoadBanner() {
        bannerArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("BannerList");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bannerArrayList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    banner bn = ds.getValue(banner.class);
                    bannerArrayList.add(bn);
                }
                setBannerRecycler(bannerArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void setFoodRecycler(List<Food_sp> food_spList) {

        foodRecycler = findViewById(R.id.food_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        foodRecycler.setLayoutManager(layoutManager);
        foodAdapter = new FoodAdapter(this, food_spList, this);
        foodRecycler.setAdapter(foodAdapter);
    }

    private void setBannerRecycler(List<banner> bannerList) {
        bannerRecycler = findViewById(R.id.banner_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        bannerRecycler.setLayoutManager(layoutManager);
        bannerAdapter = new BannerAdapter(this, bannerList);
        bannerRecycler.setAdapter(bannerAdapter);
    }

    private void setSaleRecycler(List<sale> saleList) {
        saleRecycler = findViewById(R.id.sale_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        saleRecycler.setLayoutManager(layoutManager);
        saleAdapter = new SaleAdapter(this, saleList, this);
        saleRecycler.setAdapter(saleAdapter);
    }

    private void setBsRecycler(List<bestseller> bestsellerList) {
        bsRecycler = findViewById(R.id.bs_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        bsRecycler.setLayoutManager(layoutManager);
        bsAdapter = new BSAdapter(this, bestsellerList, this);
        bsRecycler.setAdapter(bsAdapter);
    }


    //////////////////////Click Event////////////////////////////////

    @Override
    public void onBackPressed(){


        if(backPressedTime + 2000 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
//            if(requestCode == 999 && resultCode == RESULT_OK && backPressedTime + 2000 >System.currentTimeMillis())
//            {
//                super.onBackPressed();
//            }
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Nhấn thêm lần nữa để thoát", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


    public void orderBtn_main(View view) {
        if (view == findViewById(R.id.btnOrder_main)) {
            startActivity(new Intent(this, DatHang.class));
        }
    }

    public void btnMenu(View view) {
        if (view == findViewById(R.id.btnMenu_main)) {
            startActivity(new Intent(this, TaiKhoan.class));
        }
    }

    @Override
    public void onFoodClick(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, ttsanphamtt.class);
            startActivity(intent);
        }
    }

    @Override
    public void onBestClick(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, Buy_Activity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onSaleClick(int position) {
        if (position == 0) {
            Intent intent = new Intent(this, Buy_Activity.class);
            startActivity(intent);
        }
    }

    public void main_Search(View view) {
        if (view == findViewById(R.id.edit_search)) {
            startActivity(new Intent(this, pro_search.class));
        }
    }
    /////////////////////////////////////////////////////////////////

    //Location permission
    int LOCATION_REQUEST_CODE = 10001;
    private static final String TAG = "MainActivity";
    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            //super.onLocationResult(locationResult);
            if (locationResult == null) {
                return;
            }
            for (Location location : locationResult.getLocations()) {
                Log.d(TAG, "onLocationResult: " + location.toString());
            }
        }
    };


    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //getLastLocation();
            checkSettingsAndStartLocationUpdates();
        } else {
            askLocationPermission();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();
    }


    private void checkSettingsAndStartLocationUpdates() {
        LocationSettingsRequest request = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build();
        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);
        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                startLocationUpdates();
            }
        });
        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof ResolvableApiException) {
                    ResolvableApiException apiException = (ResolvableApiException) e;
                    try {
                        apiException.startResolutionForResult(MainActivity.this, 1001);
                    } catch (IntentSender.SendIntentException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void getLastLocation() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
//        {
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location !=null)
                {
                    Log.d(TAG, "onSuccess: " + location.toString());
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());
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

    private void askLocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                Log.d(TAG, "askLocationPermission: show alert dialog");
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == LOCATION_REQUEST_CODE)
        {
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                //getLastLocation();
                checkSettingsAndStartLocationUpdates();
            }
        }

    }
}