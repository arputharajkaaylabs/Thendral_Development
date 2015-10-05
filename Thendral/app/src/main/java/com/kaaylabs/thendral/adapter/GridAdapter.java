package com.kaaylabs.thendral.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.bean.EndangeredItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 28/02/2015.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

    List<EndangeredItem> mItems;

    public GridAdapter(Context context) {
        super();
        mItems = new ArrayList<EndangeredItem>();
        EndangeredItem species = new EndangeredItem();
        species.setName(context.getString(R.string.wanted_brides));
        species.setThumbnail(R.drawable.classifieds_1);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName(context.getString(R.string.wanted_grooms));
        species.setThumbnail(R.drawable.classifieds_2);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName(context.getString(R.string.for_rent));
        species.setThumbnail(R.drawable.classifieds_3);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName(context.getString(R.string.chennai_real_estate));
        species.setThumbnail(R.drawable.classifieds_4);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName(context.getString(R.string.service_apartment));
        species.setThumbnail(R.drawable.classifieds_5);
        mItems.add(species);

        species = new EndangeredItem();
        species.setName(context.getString(R.string.Ipbs));
        species.setThumbnail(R.drawable.classifieds_6);
        mItems.add(species);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.classifieds_grid_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        EndangeredItem nature = mItems.get(i);
        viewHolder.tvspecies.setText(nature.getName());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {

        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvspecies = (TextView)itemView.findViewById(R.id.tv_species);
        }
    }

}
