package com.example.myapp.Order;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import java.io.FilePermission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.example.myapp.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class DatHang extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    RecyclerView foodRecycler;
    FoodOrderAdapter FoodOrderAdapter;
    private Location location;
    private GoogleApiClient gac;
    private TextView tvLocation;
    Geocoder geocoder;
    List<Address> addresses;
    ArrayList<FoodOrder> foodOrders;
    TextView tongtien;
    Button btnTT;
    public Integer finalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);
        tongtien = findViewById(R.id.tongtien);
        btnTT=  findViewById(R.id.btnTT);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        if (checkPlayServices()) {
            // Building the GoogleApi client
            buildGoogleApiClient();
        }
        ShowBill();
        tongtien.setText(finalPrice + " đ");
        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pay();
                Toast.makeText(DatHang.this, "Đặt hàng thành công!",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setFoodRecycler(List<FoodOrder> foodorder_spList)
    {
        foodRecycler = findViewById(R.id.bill_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        foodRecycler.setLayoutManager(layoutManager);
        FoodOrderAdapter = new FoodOrderAdapter(this,foodorder_spList);
        foodRecycler.setAdapter(FoodOrderAdapter);
    }


    void ShowBill()
    {
        foodOrders = new ArrayList<>();

        try {
            EasyDB easyDB = EasyDB.init(this,"ITEM_DB")
                    .setTableName("ITEMS_TABLE")
                    .addColumn(new Column("ID", new String[]{"text","unique"}))
                    .addColumn(new Column("itemName", new String[]{"text","not null"}))
                    .addColumn(new Column("itemPrice", new String[]{"text","not null"}))
                    .addColumn(new Column("itemFinal", new String[]{"text","not null"}))
                    .addColumn(new Column("itemNumber", new String[]{"text","not null"}))
                    .doneTableColumn();
            Cursor res = easyDB.getAllData();
            while (res.moveToNext())
            {
                String id =  res.getString(0);
                String name =  res.getString(1);
                String priceo = res.getString(2);
                String price =  res.getString(3);
                String cout =  res.getString(4);

                FoodOrder foodOrder = new FoodOrder(""+name,""+price+" đ",""+cout,""+id,""+priceo+" đ");

                foodOrders.add(foodOrder);
                finalPrice += Integer.parseInt(price);
            }
            setFoodRecycler(foodOrders);
        }catch (Exception ex)
        {
            Toast.makeText(this,"Không có sản phẩm",Toast.LENGTH_SHORT).show();
        }
    }

    void Pay()
    {
        EasyDB easyDB = EasyDB.init(this,"ITEM_DB")
                .setTableName("ITEMS_TABLE")
                .addColumn(new Column("ID", new String[]{"text","unique"}))
                .addColumn(new Column("itemName", new String[]{"text","not null"}))
                .addColumn(new Column("itemPrice", new String[]{"text","not null"}))
                .addColumn(new Column("itemFinal", new String[]{"text","not null"}))
                .addColumn(new Column("itemNumber", new String[]{"text","not null"}))
                .doneTableColumn();
        easyDB.deleteAllDataFromTable();


    }

    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }

    public void dispLocation(View view) {
        getLocation();
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Kiểm tra quyền hạn
            ActivityCompat.requestPermissions(this,
                                              new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 2);
        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(gac);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                geocoder = new Geocoder(this, Locale.getDefault());
                try{

                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
                    String address = addresses.get(0).getAddressLine(0);
                    String area = addresses.get(0).getLocality();
                    String city= addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();

                    String fulladress = address +", "+area +", "+city+", "+country+",";
                    tvLocation.setText(fulladress);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                tvLocation.setText("(Không thể hiển thị vị trí. " +"Bạn đã kích hoạt location trên thiết bị chưa?)");
            }
        }
    }

    protected synchronized void buildGoogleApiClient() {
        if (gac == null) {
            gac = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API).build();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this, 1000).show();
            } else {
                Toast.makeText(this, "Thiết bị này không hỗ trợ.", Toast.LENGTH_LONG).show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        getLocation();
    }
    @Override
    public void onConnectionSuspended(int i) {
        gac.connect();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Lỗi kết nối: " + connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }
    protected void onStart() {
        gac.connect();
        super.onStart();
    }
    protected void onStop() {
        gac.disconnect();
        super.onStop();
    }
}
