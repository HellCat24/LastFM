package com.fancy.lastfm.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.fancy.lastfm.CropCircleTransformation;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import butterknife.ButterKnife;

/**
 * @author Oleg Mazhukin
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected final Transformation cropCircleTransformation = new CropCircleTransformation();

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    protected void loadImage(String url, ImageView imageView) {
        Picasso.with(imageView.getContext())
                .load(url)
                .fit()
                .transform(cropCircleTransformation)
                .into(imageView);
    }

    public abstract void bindItem(T t);
}
