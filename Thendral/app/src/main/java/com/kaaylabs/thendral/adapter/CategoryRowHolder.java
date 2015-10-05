package com.kaaylabs.thendral.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kaaylabs.thendral.R;

public class CategoryRowHolder extends RecyclerView.ViewHolder {

    protected TextView title;

    public CategoryRowHolder(View view) {
        super(view);
        this.title = (TextView) view.findViewById(R.id.title);
    }

}