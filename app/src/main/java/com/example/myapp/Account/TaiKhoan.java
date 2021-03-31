package com.example.myapp.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.myapp.History.BuyHistory;
import com.example.myapp.Login.Login;
import com.example.myapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class TaiKhoan extends AppCompatActivity {

    TextView txtlogin;

    public static final int RESULT_CODE = 1014;
    private FirebaseAuth mAuth;


    @Override
    protected void onStart() {
        super.onStart();

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            txtlogin.setText(signInAccount.getDisplayName());
        }
        else {
            txtlogin.setText("Login");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tai_khoan);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        txtlogin = (TextView) findViewById(R.id.txtLogin);

//        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
//        if(signInAccount != null){
//            txtlogin.setText(signInAccount.getDisplayName());
//        }


//        Intent intent = getIntent();
//        String text = intent.getStringExtra("AccountName");
//        txtlogin.setText(text);

    }





    public void onBackPressed(){
        super.onBackPressed();
    }
    public void btnBack(View view) {
        super.onBackPressed();
    }

    public void btnLichSuMua(View view) {
        if(view == findViewById(R.id.btnLichsumua)){
            startActivity(new Intent(this, BuyHistory.class));
        }
    }
    public void btnQuanLyThanhToan(View view) {
        if(view == findViewById(R.id.btnQuanlythanhtoan)){
            startActivity(new Intent(this, Payments.class));
        }
    }

    public void btnLogin(View view) {
        if (view == findViewById(R.id.txtLogin)) {
            Intent intent = new Intent(this, Login.class);
//            startActivityForResult(intent, RESULT_CODE);
            startActivity(intent);
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        Log.d("TK","TK: ");
//        switch (requestCode)
//        {
//            case RESULT_CODE:
//                if(resultCode == Activity.RESULT_OK){
//                    String mess = data.getStringExtra("AccountName");
//                    Log.d("TK","TK: "+ mess);
//                    txtlogin.setText(mess);
//                }
//        }
//    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
//        //super.onActivityResult(requestCode, resultCode, data);
//        Log.d("ABCC","ACNAME: "+ data.getStringExtra("message"));
//        Log.d("ABCC","requestCode: "+ requestCode);
//        Log.d("ABCC","resultCode: "+ resultCode);
//        Log.d("ABCC","RESULT_OK: "+ RESULT_OK);
//        if (requestCode == 999 && resultCode == RESULT_OK) {
//            txtlogin.setText(data.getStringExtra("message"));
//            Log.d("ABCC","ACNAME: "+ data.getStringExtra("message"));
//        }
//    }

//    public void btnXemThongTinTaiKhoan(View view) {
//    }
}