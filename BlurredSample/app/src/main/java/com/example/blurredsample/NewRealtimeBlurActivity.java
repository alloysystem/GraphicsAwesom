package com.example.blurredsample;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewRealtimeBlurActivity extends AppCompatActivity {

    /**
     * 进度条SeekBar
     */
    private SeekBar mSeekBar;

    /**
     * 显示进度的文字
     */
    private TextView mProgressTv;

    /**
     * RTBlurreView
     */
    private RealTimeBlurredView mBlurredView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_realtime_blurredview);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 初始化视图
        initViews();

        // 处理seekbar滑动事件
        setSeekBar();
    }

    /**
     * 初始化视图
     */
    private void initViews() {
        mSeekBar = (SeekBar) findViewById(R.id.activity_new_realtime_main_seekbar);
        mProgressTv = (TextView) findViewById(R.id.activity_new_realtime_main_progress_tv);
        mBlurredView = (RealTimeBlurredView) findViewById(R.id.activity_main_new_realtime_blurredview);

        // 可以在代码中使用setBlurredImg()方法指定需要模糊的图片
        mBlurredView.setBlurredImg(getResources().getDrawable(R.drawable.dayu));
    }

    /**
     * 处理seekbar滑动事件
     */
    private void setSeekBar() {
        mSeekBar.setMax(100);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mSeekBar.setMin(0);
        }
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mBlurredView.setBlurredLevel(progress);
                mProgressTv.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}