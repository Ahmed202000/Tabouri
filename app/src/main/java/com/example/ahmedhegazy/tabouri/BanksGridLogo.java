package com.example.ahmedhegazy.tabouri;

import android.content.Intent;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BanksGridLogo extends AppCompatActivity {

    GridView gvBank;

    public static String Banksno;

    GietBanLogo g = new GietBanLogo();
    TabouriBanLogo datamodel;
    AdapterBanksLogo adapterbanks;
    ArrayList<TabouriBanLogo> data;

    public static String imgLogoBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_grid);

        gvBank = (GridView) findViewById(R.id.gvbanks);

        //كود تحديث الشاشه
        final SwipeRefreshLayout swf = (SwipeRefreshLayout) findViewById(R.id.SWF);
        swf.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                data = new ArrayList<>(g.GetDate(BanksGridLogo.this));
                adapterbanks = new AdapterBanksLogo(BanksGridLogo.this, data);
                gvBank.setAdapter(adapterbanks);
                swf.setRefreshing(false);

            }
        });

        data = new ArrayList<>(g.GetDate(BanksGridLogo.this));
        adapterbanks = new AdapterBanksLogo(BanksGridLogo.this, data);
        gvBank.setAdapter(adapterbanks);

        //عند الضغط علي logo
        gvBank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                datamodel = data.get(i);


                // create bottom sheet dialog
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(BanksGridLogo.this);

                // create layout inflater
                LayoutInflater layoutInflater = LayoutInflater.from(BanksGridLogo.this);

                View view1 = layoutInflater.inflate(R.layout.layout_bank_data, null);


                bottomSheetDialog.setContentView(view1);


                ImageView img = view1.findViewById(R.id.imagbank);
                TextView txtnamebank = view1.findViewById(R.id.txtnamebank);
                TextView txtemail_bank = view1.findViewById(R.id.txtemail_bank);
                TextView txtphone_bank = view1.findViewById(R.id.txtphone_bank);
                TextView txtbank_datails = view1.findViewById(R.id.txtbank_datails);
                Button btnbranches = view1.findViewById(R.id.btnbranches);


                txtnamebank.setText(datamodel.getBankName());
                txtemail_bank.setText(datamodel.getBankEmail());
                txtphone_bank.setText(datamodel.getBankPhone());
                txtbank_datails.setText(datamodel.getBankDetails());

                imgLogoBank = datamodel.getBankLogo();

                if (datamodel.getBankLogo() != null && datamodel.getBankLogo().length() > 0) {
                    PicassoClinte.downloadImage(BanksGridLogo.this, datamodel.getBankLogo(), img);
                }

                btnbranches.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(BanksGridLogo.this, Branches.class));

                    }
                });

                bottomSheetDialog.show();
            }
        });
    }
}
