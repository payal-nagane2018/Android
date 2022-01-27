package com.payal.faculty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgFlash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFlash=(ImageView)findViewById(R.id.imageView);
        Animation myanim= AnimationUtils.loadAnimation(MainActivity.this,R.anim.home_animation);
        imgFlash.startAnimation(myanim);
        final Thread thread=new Thread(new Runnable()
        {
            @Override
            public void run() {
                try
                {
                    Thread.sleep(3000);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent in=new Intent(MainActivity.this,Home.class);
                    startActivity(in);
                    finish();
                }
            }
        });
        thread.start();

    }

}




