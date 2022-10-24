package com.example.blurredsample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class RealTimeBlurredView extends RelativeLayout {
    /**
     * Context
     */
    private Context mContext;

    /**
     * 模糊后的ImageView
     */
    private ImageView mBlurredImg;

    /**
     * 原图Bitmap
     */
    private Bitmap mOriginBitmap;

    /**
     * 是否移动背景图片
     */
    private boolean isMove;

    /**
     * 是否禁用模糊效果
     */
    private boolean isDisableBlurred;

    /**
     * 模糊后的Bitmap
     */
    private Bitmap mBlurredBitmap;

    private int currentBlurLevel = 0;

    public RealTimeBlurredView(Context context) {
        super(context);
        init(context);
    }

    public RealTimeBlurredView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        initAttr(context, attrs);
    }

    public RealTimeBlurredView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initAttr(context, attrs);
    }

    private void init(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.realtime_blurredview, this);
        mBlurredImg = (ImageView) findViewById(R.id.realtime_blurredview_blurred_img);
    }

    private void initAttr(Context context, AttributeSet attrs) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurredView);
        Drawable drawable = typedArray.getDrawable(R.styleable.BlurredView_src);
        isMove = typedArray.getBoolean(R.styleable.BlurredView_move, false);
        isDisableBlurred = typedArray.getBoolean(R.styleable.BlurredView_disableBlurred, false);

        typedArray.recycle();

        // blur image
        if (null != drawable) {
            mOriginBitmap = BlurredUtil.drawableToBitmap(drawable);
        }

        // setVisibility
        if (!isDisableBlurred) {
            mBlurredImg.setVisibility(VISIBLE);
        }

        // setMove
        if (null != drawable) {
            setMove(context, isMove);
        }
    }


    /**
     * 设置背景图片移动效果
     * @param context   上下文对象
     * @param isMove    是否移动
     */
    private void setMove(Context context, boolean isMove) {
        if (isMove) {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int height = point.y;
            setBlurredHeight(height, mBlurredImg);
        }
    }

    /**
     * 改变图片的高度
     *
     * @param height        图片的高度
     * @param imageView     imageview对象
     */
    private void setBlurredHeight(int height, ImageView imageView) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = height + 100;
        imageView.requestLayout();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setImageView();
    }

    /**
     * 填充ImageView.
     */
    private void setImageView() {
        mBlurredImg.setImageBitmap(mBlurredBitmap);
    }

    /**
     * 以代码的方式添加待模糊的图片
     *
     * @param blurredBitmap 待模糊的图片
     */
    public void setBlurredImg(Bitmap blurredBitmap) {
        if (null != blurredBitmap) {
            mOriginBitmap = blurredBitmap;
            mBlurredBitmap = BlurBitmap.blur(mContext, blurredBitmap);
            setImageView();
            setMove(mContext, isMove);
        }
    }

    /**
     * 以代码的方式添加待模糊的图片
     *
     * @param blurDrawable 待模糊的图片
     */
    public void setBlurredImg(Drawable blurDrawable) {
        if (null != blurDrawable) {
            mOriginBitmap = BlurredUtil.drawableToBitmap(blurDrawable);
            //mBlurredBitmap = BlurBitmap.blur(mContext, mOriginBitmap);
            //setImageView();
            mBlurredImg.setImageBitmap(mOriginBitmap);
            setMove(mContext, isMove);
        }
    }

    /**
     * 设置模糊程度
     *
     * @param level 模糊程度, 数值在 0~100 之间.
     */
    public void setBlurredLevel(int level) {
        if (level < 0 || level > 100) {
            throw new IllegalStateException("No validate level, the value must be 0~100");
        }
        if (isDisableBlurred) {
            return;
        }
        if(level != currentBlurLevel) {
            float radius = level/4.0f;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                mBlurredImg.setRenderEffect(RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.REPEAT));
            }
            currentBlurLevel = level;
        }
    }

    /**
     * 设置图片上移的距离
     *
     * @param height 向上移动的距离
     */
    public void setBlurredTop(int height) {
        mBlurredImg.setTop(-height);
    }

    /**
     * 显示模糊图片
     */
    public void showBlurredView() {
        mBlurredImg.setVisibility(VISIBLE);
    }

    /**
     * 禁用模糊效果
     */
    public void disableBlurredView() {
        isDisableBlurred = true;
        mBlurredImg.setVisibility(INVISIBLE);
    }

    /**
     * 启用模糊效果
     */
    public void enableBlurredView() {
        isDisableBlurred = false;
        mBlurredImg.setVisibility(VISIBLE);
    }
}
