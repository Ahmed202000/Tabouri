package com.example.ahmedhegazy.tabouri;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends AppCompatActivity {

    Button btnregister, btnlogin, btnfacbook, btngoogle;
    EditText txtUsername, txtPassword;
    CheckBox ckloginme;
    TextView txtforgeftpassword;

    public static String lang = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //قراءه من  SharedPreferences
        SharedPreferences SH = getSharedPreferences("TabouriSuper", MODE_PRIVATE);
        String us = SH.getString("Username", null);
        String P = SH.getString("Password", null);

        if ((us != null) && (P != null)) {
            startActivity(new Intent(Login.this, BanksGridLogo.class));
        }


        txtUsername = (EditText) findViewById(R.id.txtusername);
        txtPassword = (EditText) findViewById(R.id.txtpassword);
        ckloginme = (CheckBox) findViewById(R.id.ckloginme);
        btnlogin = (Button) findViewById(R.id.btnlogin);
        btnregister = (Button) findViewById(R.id.btnregister);

        //كود تسجيل الدخول
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtUsername.getText().toString().isEmpty()) {
                    txtUsername.setError("Enter username");
                    txtUsername.requestFocus();
                } else {
                    if (txtPassword.getText().toString().isEmpty()) {
                        txtPassword.setError("Enter password");
                        txtPassword.requestFocus();
                    } else {
                        Database db = new Database();
                        Connection conn = db.ConnectDB();

                        if (conn == null)
                            Toast.makeText(Login.this, "Check internet access", Toast.LENGTH_SHORT).show();
                        else {
                            ResultSet rs = db.RunSearch("select * from Customer where UserName='" + txtUsername.getText() + "' and Password ='" + txtPassword.getText() + "'");
                            try {
                                if (rs.next()) {
                                    if (ckloginme.isChecked()) {
                                        getSharedPreferences("TabouriSuper", MODE_PRIVATE)
                                                .edit()
                                                .putString("Username", txtPassword.getText().toString())
                                                .putString("Password", txtPassword.getText().toString())
                                                .commit();

                                    }
                                    AlertDialog.Builder d = new AlertDialog.Builder(Login.this)
                                            .setTitle(" Wlecome")
                                            .setMessage("ماي هيا الخدمات التي تريدها :) ")
                                            .setPositiveButton("خدمات", new DialogInterface.OnClickListener() {
                                                @Override
                                               public void onClick(DialogInterface dialog, int which) {
                                                   Intent i = new Intent(Login.this, Transaction.class);


                                                }
                                            })
                                            .setPositiveButton("استعلامات", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent i = new Intent(Login.this, Reservation.class);
                                                    startActivity(i);

                                                }
                                            });
                                    d.create();
                                    d.show();

                                    //startActivity(new Intent(Login.this, Transaction.class));
                                } else
                                    Toast.makeText(Login.this, "Invaild username or password", Toast.LENGTH_SHORT).show();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }


                        }
                    }
                }
            }
        });
        btnregister=(Button)findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login.this,Register.class));

            }
        });

    }



        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            getSharedPreferences("TabouriSuper", MODE_PRIVATE)
                    .edit()
                    .clear()
                    .commit();
            finish();
            return true;
        }
        else  if (id == R.id.action_arabic) {
            lang="arabic";
            return true;

            }

        else  if (id == R.id.action_einglish) {
            lang="einglish";
            return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
