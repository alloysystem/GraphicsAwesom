package com.example.blurdemoforoh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RealTimeBackgroundBlur image1;
    RealTimeBackgroundBlur image2;
    RealTimeBackgroundBlur image3;
    RealTimeBackgroundBlur image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image1 = findViewById(R.id.imageView);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView3);
        image4 = findViewById(R.id.imageView4);
        image1.setRadius(25f);
        image2.setRadius(25f);
        image3.setRadius(25f);
        image4.setRadius(25f);
    }
}