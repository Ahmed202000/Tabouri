package com.example.ahmedhegazy.tabouri;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Branches extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btn_Transaction;
    Button maps;
    TextView txtaddress_branches;
    List<String>SppinarIdBranches=new ArrayList<>();
    List<String>SppinarNameBranches=new ArrayList<>();


    private ImageView imagbank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branches);

        txtaddress_branches=findViewById(R.id.txtaddress_branches);
        Spinner BranchesName=findViewById(R.id.BranchesName);
        btn_Transaction=(Button)findViewById(R.id.btn_Transaction) ;
        imagbank = findViewById(R.id.imagbank);

        //اتصال بالداتا بيز وازي يجب بيانات
        Connection conn;
        Database db=new Database();
        conn=db.ConnectDB();

        try {
            Statement stm=conn.createStatement();

            ResultSet r=stm.executeQuery("select * from Branches");
            r.next();
            while (r.next()){

                SppinarIdBranches.add(r.getString(1));
                SppinarNameBranches.add(r.getString(2));
                txtaddress_branches.setText(r.getString(3));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String>adapter=new ArrayAdapter<>(this,R.layout.sppinar_branches_name,SppinarNameBranches);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        BranchesName.setAdapter(adapter);


        btn_Transaction.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(Branches.this, Login.class);
               startActivity(i);


         /*      AlertDialog.Builder d = new AlertDialog.Builder(Branches.this)
                       .setTitle(" Wlecome")
                       .setMessage("ماي هيا الخدمات التي تريدها :) ")
                       .setPositiveButton("خدمات", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent i = new Intent(Branches.this, Transaction.class);


                           }
                       })
                       .setPositiveButton("استعلامات", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               Intent i = new Intent(Branches.this, Reservation.class);
                               startActivity(i);

                           }
                       });
               d.create();
               d.show();
*/
           }
       });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btn_map);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Branches.this,MapsActivity.class);
                startActivity(i);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        setrImage();

    }

    private void setrImage() {

        PicassoClinte.downloadImage(Branches.this,  BanksGridLogo.imgLogoBank, imagbank);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.branches, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
