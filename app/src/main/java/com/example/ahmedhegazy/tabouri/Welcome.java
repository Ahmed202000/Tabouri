package com.example.ahmedhegazy.tabouri;

import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        // 3000
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent a=new Intent(Welcome.this,BanksGridLogo.class);
                startActivity(a);
                finish();
            }
        },2000);

        //كود الافيقت
        ImageView image = (ImageView)findViewById(R.id.images);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim);
        image.startAnimation(animation);
    }
}
