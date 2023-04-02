package com.example.blurdemoforoh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

public class RealTimeBackgroundBlur extends androidx.appcompat.widget.AppCompatImageView {
    private  float radius = 100f;

    public void setRadius(float radius) {
        this.radius = radius;
    }
    public RealTimeBackgroundBlur(Context context) {
        super(context);
    }

    public RealTimeBackgroundBlur(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RealTimeBackgroundBlur(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("BackGroundBlur", "onDraw");
        super.onDraw(canvas);
        setRenderEffect(RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.REPEAT));
        invalidate();
    }
}
