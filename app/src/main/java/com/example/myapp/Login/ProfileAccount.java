package com.example.myapp.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapp.Account.TaiKhoan;
import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileAccount extends AppCompatActivity {

    Button btnDangxuat;
    TextView txtaccountname, txtemail;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_account);

        btnDangxuat = (Button) findViewById(R.id.btnDangXuat);
        txtaccountname = (TextView) findViewById(R.id.txtAccountName);
        txtemail = (TextView) findViewById(R.id.txtEmail);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount != null){
            txtaccountname.setText(signInAccount.getDisplayName());
            txtemail.setText(signInAccount.getEmail());
        }

        btnDangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.btnDangXuat:
                        AlertDialog dialog = new AlertDialog.Builder(ProfileAccount.this)
                                .setTitle("Đăng xuất tài khoản")
                                .setMessage("Bạn muốn đăng xuất tài khoản?")
                                .setPositiveButton("Đồng ý", null)
                                .setNegativeButton("Hủy", null)
                                .show();

                        Button YesButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                        YesButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                FirebaseAuth.getInstance().signOut();
                                signOut();
//                                Toast.makeText(ProfileAccount.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
//                                finish();
//                                Intent intent = new Intent(getApplicationContext(), TaiKhoan.class);
//                                startActivity(intent);
                            }
                        });
                        break;
                }
            }
        });
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//

//
//        btnDangxuat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switch (view.getId()) {
//                    case R.id.btnDangXuat:
//                        AlertDialog dialog = new AlertDialog.Builder(ProfileAccount.this)
//                                .setTitle("Đăng xuất tài khoản")
//                                .setMessage("Bạn muốn đăng xuất tài khoản?")
//                                .setPositiveButton("Đồng ý", null)
//                                .setNegativeButton("Hủy", null)
//                                .show();
//
//                        Button YesButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
//                        YesButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                signOut();
//                            }
//                        });
//                        break;
//                }
//            }
//        });
//
//        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//        if (acct != null) {
//
//            String personName = acct.getDisplayName();
//            String personEmail = acct.getEmail();
//
//            String personGivenName = acct.getGivenName();
//            String personFamilyName = acct.getFamilyName();
//            String personId = acct.getId();
//            Uri personPhoto = acct.getPhotoUrl();
//
//            txtaccountname.setText(personName);
//            txtemail.setText(personEmail);
//            //txtlogin.setText(personName);
//        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(ProfileAccount.this, "Đăng xuất thành công", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

    public void onBackPressed(){

//        Intent i = new Intent();
//        i.putExtra("AccountName", txtaccountname.getText().toString());
//        setResult(RESULT_OK, i);
//        finish();
        super.onBackPressed();
        //dổi tên account

    }

    public void btnBacktoTaiKhoan(View view) {
        if(view == findViewById(R.id.btnBackProfile)){

//            Intent i = new Intent();
//            i.putExtra("AccountName", txtaccountname.getText().toString());
//            setResult(Activity.RESULT_OK, i);
//            Log.d("ABCC","ACNAME: "+txtaccountname.getText().toString());
//            finish();

            super.onBackPressed();

            //dổi tên account
        }
    }
}