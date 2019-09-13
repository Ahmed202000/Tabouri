package com.example.ahmedhegazy.tabouri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;


public class AdapterBanksLogo extends ArrayAdapter<TabouriBanLogo> {

    Context context;
    ArrayList <TabouriBanLogo> mCategoryList;

    public AdapterBanksLogo(Context context , ArrayList <TabouriBanLogo> mCategoryList){
        super(context,R.layout.banklayout,mCategoryList);
        this.context = context;
        this.mCategoryList =mCategoryList;
    }

    public class Holder {
        ImageView imglogo;

    }

    public View getView(int position, View convertView, ViewGroup parent){
        TabouriBanLogo data = getItem(position);

        Holder viewHolder;

        if (convertView == null){

            viewHolder = new Holder();


            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.banklayout,parent ,false);


            viewHolder.imglogo = (ImageView) convertView.findViewById(R.id.imgbanklogo);

            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (Holder) convertView.getTag();
        }

        PicassoClinte.downloadImage(context,data.getBankLogo(),viewHolder.imglogo);


      //  viewHolder.mImage

        return convertView;
    }




}
