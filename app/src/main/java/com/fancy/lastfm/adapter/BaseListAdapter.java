package com.fancy.lastfm.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * @author Oleg Mazhukin
 */
public abstract class BaseListAdapter<T, V extends BaseViewHolder<T>> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> dataList;

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindItem(getItem(position));
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    @Nullable
    protected T getItem(int position) {
        return dataList != null ? dataList.get(position) : null;
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : dataList.size();
    }
}