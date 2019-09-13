package com.example.ahmedhegazy.tabouri;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation extends AppCompatActivity {

    TextView txtdate_reservation;
    TextView txtreservation_time;
    EditText txtnotes;
    Button btn_send_the_request;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtdate_reservation=(TextView) findViewById(R.id.txtdate_reservation);
        txtreservation_time=(TextView) findViewById(R.id.txtreservation_time);
        txtnotes=(EditText)findViewById(R.id.txtnotes);
        btn_send_the_request=(Button)findViewById(R.id.btn_send_the_request);

        //كود الوقت والتاريخ
        Date date=Calendar.getInstance().getTime();

        SimpleDateFormat sdf=new SimpleDateFormat(" dd/MM/yyyy");
        String date_star=sdf.format(date);

        SimpleDateFormat sd=new SimpleDateFormat("a h:mm:ss");
        txtdate_reservation.setText(date_star);
        txtreservation_time.setText(sd.format(date));



    }

}
