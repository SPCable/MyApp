package com.example.myapp.Order;


import androidx.appcompat.app.AppCompatActivity;

import androidx.core.content.ContextCompat;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.biometric.BiometricPrompt;
import androidx.biometric.BiometricManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
//import android.hardware.biometrics.BiometricManager;
//import android.hardware.biometrics.BiometricPrompt;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.text.Html;

import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.util.Locale;

import java.util.Properties;
import java.util.concurrent.Executor;


import com.example.myapp.Complete;
import com.example.myapp.Login.ProfileAccount;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import p32929.androideasysql_library.Column;
import p32929.androideasysql_library.EasyDB;

public class DatHang extends AppCompatActivity {


    String data;
    private static final String TAG = "DatHang";

    Date date;
    String sEmail, sPassword, email;

    RecyclerView foodRecycler;
    ImageView wn1 , wn2, wn3;
    FoodOrderAdapter FoodOrderAdapter;

    ArrayList<FoodOrder> foodOrders;

    TextView tongtien, tvLocation, txtlocation;
    Button btnTT, btnCFMLocation;

    EditText editGmail, name, phone;

    public Integer finalPrice = 0;

    public GoogleMap mMap;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        tongtien = findViewById(R.id.tongtien);
        btnTT=  findViewById(R.id.btnTT);
        Button btnCFMLocation=  (Button) findViewById(R.id.btnCfmLocation);
        this.btnCFMLocation = btnCFMLocation;
        tvLocation = (TextView) findViewById(R.id.tvLocation);

        txtlocation = (TextView) findViewById(R.id.txtLocation); //bên activity_maps

//        mMap = googleMap;
//        getTapLocation(googleMap);

        editGmail=findViewById(R.id.editMail);
        name = findViewById(R.id.editName);
        phone = findViewById(R.id.editPhone);
        wn1 = findViewById(R.id.wn1);
        wn2 = findViewById(R.id.wn2);
        wn3 = findViewById(R.id.wn3);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            name.setText(signInAccount.getDisplayName());
            editGmail.setText(signInAccount.getEmail());
        }
        else {
            name.setText(null);
            editGmail.setText(null);
        }

        try{

                data=MapsActivity.getActivityInstance().getData();
                tvLocation.setText(data);
                Log.d(TAG, "đã Bấm!!!!!!!!");

        }catch (Exception e) {
            e.printStackTrace();
        }


        ShowBill(); // hien thi thong tin hoa don
        tongtien.setText(finalPrice + " đ");

        // tai khoan gmail smtp:
        sEmail = "hjhj2305@gmail.com";
        sPassword = "rlxpglgnjbqjyteo";


        BiometricManager biometricManager = BiometricManager.from(this);
        switch (biometricManager.canAuthenticate()){
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d("Biometric:", "Có Biometric");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Toast.makeText(this,"Vui lòng sử dụng/cài đặt bảo mật sinh trắc học để thanh toán",Toast.LENGTH_LONG).show();
                btnTT.setVisibility(View.GONE);
                break;
        }

        Executor executor = ContextCompat.getMainExecutor(this);
        final BiometricPrompt biometricPrompt = new BiometricPrompt(DatHang.this, executor, new BiometricPrompt.AuthenticationCallback()
        {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                Toast.makeText(getApplicationContext(),"Xác thực bảo mật thành công",Toast.LENGTH_SHORT).show();

                new Sendmail().execute();
//                Intent intent = new Intent(DatHang.this,Complete.class);
//                startActivity(intent);
//                finish();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
            }
        });

        final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Thanh Toán")
                .setDescription("Dùng bảo mật sinh trắc học để thanh toán")
                .setNegativeButtonText("Hủy")
                .build();


        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(FirebaseAuth.getInstance().getCurrentUser()!=null){
                    biometricPrompt.authenticate(promptInfo);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Vui lòng đăng nhập để thanh toán", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class Sendmail extends AsyncTask<Message,String,String> {             //Gửi mail cho khách hàng
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(DatHang.this,"Vui lòng đợi","Đang đặt hàng...",true,false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "465");
            Integer c = 0;

            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(sEmail,sPassword);
                }
            });

            try{
                Message message = new MimeMessage(session); // tạo 1 mail gửi cho người dùng


                message.setFrom(new InternetAddress(sEmail));

                message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(editGmail.getText().toString().trim()));

                message.setSubject("Taiducfood - Kính chào quý khách");

                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                Multipart multipart  = new MimeMultipart();
                String htmnlHeader = "<table style=font-family:-apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;\">\n" +
                        "        <td>\n" +
                        "           <img src='https://firebasestorage.googleapis.com/v0/b/taiducfood.appspot.com/o/119126284_350226082826029_2841802760364491756_n.png?alt=media&token=c63a4718-d56e-4efe-af39-f8b2a0f40b98' alt='asdasd' width=\"250px\" style=\"border-radius: 16px;\">\n" +
                        "        </td>\n" +
                        "        <td style=\"padding-left: 16px;\">\n" +
                        "            <p style=\"font-size: 28px;\">Công ty TNHH Thực Phẩm Tài Đức</p>\n" +
                        "            <p>Website: <a href='taiducfood.com'> Taiducfood.com</a></p>" +
                        "            <p>Địa chỉ: <a> 46/13-46/15 Đường Tân Cảng, Phường 25, Quận Bình Thạnh, TP.HCM, Việt Nam</a></p>\n" +
                        "            <p>Email: <a> info.taiducfood@gmail.com</a></p>\n" +
                        "            <p>Phone: <a href=\"tel:0913098639\">091 309 8639</a> - <a href=\"tel:0918698639\">091 869 8639</a> - <a href=\"tel:0935588910\">093 558 8910</a></p>\n" +
                        "        </td>\n" +
                        "    </table>";
                mimeBodyPart.setContent(htmnlHeader,"text/html;charset=utf-8");
                multipart.addBodyPart(mimeBodyPart);

                mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setText("Kính chào " + name.getText().toString().trim() + "!\n");
                multipart.addBodyPart(mimeBodyPart);

                mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setText("Mã đơn hàng: " + "#123456789"+ "\n");
                multipart.addBodyPart(mimeBodyPart);
                ;

                mimeBodyPart = new MimeBodyPart();
                String htmltag = "<h3>Thông tin đơn hàng:</h3>" +
                        "<table border=\"1\" width=\"820px\">\n" +
                        "        <tr>\n" +
                        "            <th>STT</th>\n" +
                        "            <th>Tên sản phẩm</th>\n" +
                        "            <th>Giá</th>\n" +
                        "            <th>Số lượng</th>\n" +
                        "            <th>Khuyến mãi</th>\n" +
                        "            <th>Tổng</th>\n" +
                        "        </tr>";
                mimeBodyPart.setContent(htmltag,"text/html;charset=utf-8");
                multipart.addBodyPart(mimeBodyPart);

                EasyDB easyDB = EasyDB.init(DatHang.this,"ITEM_DB")
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
                    c++;
                    String id =  res.getString(0);
                    String name =  res.getString(1);
                    String priceo = res.getString(2);
                    String price =  res.getString(3);
                    String cout =  res.getString(4);

                    mimeBodyPart = new MimeBodyPart();
                    String htmlcode ="<tr>\n" +
                            "            <th>"+c.toString().trim()+"</th>\n" +
                            "            <th>"+name+"</th>\n" +
                            "            <th>"+priceo+" vnd"+"</th>\n" +
                            "            <th>"+cout+"</th>\n" +
                            "            <th></th>\n" +
                            "            <th>"+price+" vnd"+"</th>\n" +
                            "        </tr>";
                    mimeBodyPart.setContent(htmlcode,"text/html;charset=utf-8");
                    multipart.addBodyPart(mimeBodyPart);
                }

                mimeBodyPart = new MimeBodyPart();
                String htmlfooter = " <tr>\n" +
                        "        <th></th>\n" +
                        "        <th></th>\n" +
                        "        <th></th>\n" +
                        "        <th></th>\n" +
                        "        <th>Tổng tiền</th>\n" +
                        "        <th>"+finalPrice+" vnd"+"</th>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</body>";

                mimeBodyPart.setContent(htmlfooter,"text/html;charset=utf-8");
                multipart.addBodyPart(mimeBodyPart);

                mimeBodyPart = new MimeBodyPart();
                String htmlInfoUser = "<h3 style=\"text-align: start;\">Thông tin khách hàng: </h3>\n" +
                        "    <span style=\"text-align: left;\">\n" +
                        "        <p>\n" +
                        "            <span>Tên khách hàng: </span> \n" +
                        "            <span>"+name.getText().toString().trim()+"</span>\n" +
                        "        </p>\n" +
                        "        <p>\n" +
                        "            <span>Địa chỉ giao hàng: </span> \n" +
                        "            <span>"+tvLocation.getText().toString().trim()+"</span>\n" +
                        "        </p>\n" +
                        "        <p>\n" +
                        "            <span>Số điện thoại: </span> \n" +
                        "            <span>"+phone.getText().toString().trim()+"</span>\n" +
                        "        </p>\n" +
                        "        <p>\n" +
                        "            <span>Email: </span> \n" +
                        "            <span>"+editGmail.getText().toString().trim()+"</span>\n" +
                        "        </p>\n" +
                        "        <p style=\"font-weight: bold; font-size: 18px;\">\n" +
                        "            <span>Hình thức thanh toán:</span>\n" +
                        "            <span>Thanh toán khi nhận hàng</span>\n" +
                        "        </p>\n" +
                        "    </span>";

                mimeBodyPart.setContent(htmlInfoUser,"text/html;charset=utf-8");
                multipart.addBodyPart(mimeBodyPart);

                mimeBodyPart = new MimeBodyPart();
                String htmlfooter1 = "<footer style=\"text-align: center;\" >\n" +
                        "    <p>\n" +
                        "        Cảm ơn quý khách đã sử dụng dịch vụ của chúng tôi. Nhân viên của chúng tôi sẽ liên lạc cho quý khách trong thời gian sớm nhất.\n" +
                        "    </p>\n" +
                        "    <p>Bản quyền của Taiducfood ® 2020. Bảo lưu mọi quyền.</p>\n" +
                        "</footer>";

                mimeBodyPart.setContent(htmlfooter1,"text/html;charset=utf-8");
                multipart.addBodyPart(mimeBodyPart);

                message.setContent(multipart);

                Transport.send(message);

                return "Success";

            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                Pay();
                Intent intent = new Intent(DatHang.this,Complete.class);
                startActivity(intent);
//                finish();
            }
            else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(DatHang.this);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Thông báo</font>"));
                builder.setMessage("Đặt hàng không thành công! Xin vui lòng thử lại");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            }
        }
    } //gửi mail cho khách hàng


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

    public void LocationBtnOnClick(View view) {
        startActivityForResult(new Intent(getApplicationContext(), MapsActivity.class),999);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //final Intent data
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 999 && resultCode == RESULT_OK) {

            tvLocation.setText(data.getStringExtra("message"));
        }
    }

//
//    public String getAddress(double lat, double lon){
//
//        String address = "";
//
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//
//        List<Address> addresses;
//
//        try{
//
//            addresses = geocoder.getFromLocation(lat,lon,1);
//            if(addresses.size() > 0)
//            {
//                address = addresses.get(0).getAddressLine(0);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return address;
//    }
//
//    private void getTapLocation(final GoogleMap googleMap) {
//
//        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
//            @Override
//            public void onCameraIdle() {
//                LatLng tapLocation = googleMap.getCameraPosition().target;
//                tvLocation.setText(getAddress(tapLocation.latitude,tapLocation.longitude));
//            }
//        });
//    }
//    btnCFMLocation.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//
//        }
//    });
}
