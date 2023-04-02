package com.example.blurredsample;

import android.content.Context;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private static final int ITEM_COUNT = 10;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_IMAGE = 2;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return new HeaderHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_header, parent, false));
        } else if (viewType == TYPE_IMAGE) {
            return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_image_item, parent, false));
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ITEM_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        } else if( position == 1) {
            return TYPE_ITEM;
        } else if ( position == 2 || position == 3 || position == 4 || position == 5) {
            return TYPE_IMAGE;
        }
        return  TYPE_ITEM;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ImageViewHolder extends  RecyclerView.ViewHolder {
        public ImageViewHolder(View itemView) {
            super(itemView);
            ImageView image = (ImageView)itemView.findViewById(R.id.recyclerview_image_item);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                image.setRenderEffect(RenderEffect.createBlurEffect(20, 20, Shader.TileMode.REPEAT));
            }
        }
    }

    private class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }
}
