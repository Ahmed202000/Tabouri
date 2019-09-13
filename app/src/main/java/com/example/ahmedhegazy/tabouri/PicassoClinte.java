package com.example.ahmedhegazy.tabouri;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

//import com.squareup.picasso.Picasso;

public class PicassoClinte {

    public static void downloadImage (Context context, String URL , ImageView imageView){

        if (URL != null && URL.length() >0){
              Picasso.with(context ).load(URL).placeholder(R.drawable.logooo).into(imageView);
        }
        else{
            Picasso.with(context).load(R.drawable.logooo).into(imageView);
        }
    }
}
