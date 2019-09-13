package com.example.ahmedhegazy.tabouri;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Transaction extends AppCompatActivity {

    Button transaction;

    ImageView imagbank;
    List<String>SpinnertransactionName=new ArrayList<>();
   // List<String>SpinnertransactionID=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        imagbank = findViewById(R.id.imagbanks);

        Spinner Spinnertransaction=findViewById(R.id.TransactionName);
        //اتصال بالداتا بيز وازي يجب بيانات
        Connection conn;
        Database db=new Database();
        conn=db.ConnectDB();

        try {
            Statement stm=conn.createStatement();

            ResultSet r=stm.executeQuery("select * from  Transactions");
            r.next();
            while (r.next()){

                SpinnertransactionName.add(r.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.spinner_transaction_name,SpinnertransactionName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinnertransaction.setAdapter(adapter);



                transaction = (Button) findViewById(R.id.btnreservation);
                transaction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(Transaction.this, Reservation.class);
                        startActivity(i);
                    }
                });
            }
}