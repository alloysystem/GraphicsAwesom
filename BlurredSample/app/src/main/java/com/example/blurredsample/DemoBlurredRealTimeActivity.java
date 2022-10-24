package com.example.blurredsample;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DemoBlurredRealTimeActivity extends AppCompatActivity {

    /**
     * blurredview
     */
    private RealTimeBlurredView mBlurredView;

    /**
     * RecyclerView
     */
    private RecyclerView mRecyclerView;

    private int mScrollerY;

    private int mAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blurred_realtime_demo_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mBlurredView = (RealTimeBlurredView) findViewById(R.id.yahooweather_blurredview);
        // 可以在代码中使用setBlurredImg()方法指定需要模糊的图片
        mBlurredView.setBlurredImg(getResources().getDrawable(R.drawable.zhuomian));

        mRecyclerView = (RecyclerView) findViewById(R.id.yahooweather_recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerViewAdapter(this));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                if (Math.abs(mScrollerY) > 1000) {
                    mBlurredView.setBlurredTop(100);
                    mAlpha = 100;
                } else {
                    mBlurredView.setBlurredTop(mScrollerY / 10);
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                mBlurredView.setBlurredLevel(mAlpha);
            }
        });
    }
}
