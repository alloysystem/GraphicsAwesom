package com.example.blurredsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    /**
     * basic btn
     */
    private Button mBasicBtn;

    /**
     * weather btn
     */
    private Button mWeatherBtn;

    private Button mRealtimeBtn;

    private Button mNewAPIRealtimeBtn;

    private Button mDemoPerformanceBtn;

    private Button mDemoRealTimeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBasicBtn = (Button) findViewById(R.id.basic_blur_btn);
        mWeatherBtn = (Button) findViewById(R.id.weather_blur_btn);
        mRealtimeBtn = (Button) findViewById(R.id.realtime_blur_btn);
        mNewAPIRealtimeBtn = (Button) findViewById(R.id.new_realtime_blur_btn);
        mDemoPerformanceBtn = (Button) findViewById(R.id.demo_blur_alpha_btn);
        mDemoRealTimeBtn = (Button) findViewById(R.id.demo_blur_realtime_btn);

        mBasicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, BlurredViewBasicActivity.class));
                startActivity(new Intent(MainActivity.this, BlurredViewBasicActivity.class));
            }
        });

        mWeatherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                //startActivity(new Intent(MainActivity.this, RealtimeBlurredViewBasicActivity.class));
                startActivity(new Intent(MainActivity.this, SequenceImgViewBasicActivity.class));
            }
        });

        mRealtimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                //startActivity(new Intent(MainActivity.this, RealtimeBlurredViewBasicActivity.class));
                startActivity(new Intent(MainActivity.this, RealtimeBlurredViewBasicActivity.class));
            }
        });

        mNewAPIRealtimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(MainActivity.this, WeatherActivity.class));
                //startActivity(new Intent(MainActivity.this, RealtimeBlurredViewBasicActivity.class));
                startActivity(new Intent(MainActivity.this, NewRealtimeBlurActivity.class));
            }
        });

        mDemoPerformanceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(MainActivity.this, DemoBlurredAlphaActivity.class));
                //startActivity(new Intent(MainActivity.this, DemoBlurredRealTimeActivity.class));
            }
        });

        mDemoRealTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(MainActivity.this, DemoBlurredAlphaActivity.class));
                startActivity(new Intent(MainActivity.this, DemoBlurredRealTimeActivity.class));
            }
        });
    }
}