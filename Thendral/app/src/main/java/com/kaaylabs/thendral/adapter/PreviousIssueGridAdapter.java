package com.kaaylabs.thendral.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.PreviousIssuesByYearVO;
import com.kaaylabs.thendral.navigation.NavigationHomeActivity;
import com.kaaylabs.thendral.util.Thendral_URLs;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 28/02/2015.
 */
public class PreviousIssueGridAdapter extends RecyclerView.Adapter<PreviousIssueGridAdapter.ViewHolder> {

    Context context;
    ArrayList<String> result;
    List<PreviousIssuesByYearVO> previousIssuesByYearVO;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public PreviousIssueGridAdapter(Context context, ArrayList<String> itemList,List<PreviousIssuesByYearVO> previousIssuesByYear) {

        this.context = context;
        this.result = itemList;
        this.previousIssuesByYearVO = previousIssuesByYear;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.previous_issue_grid_layout, viewGroup, false);
        final int position =i;
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int i) {
        viewHolder.tvspecies.setText(previousIssuesByYearVO.get(i).getIssueName());
        String image_url= Thendral_URLs.THENDRAL_IMAGE_URL+ previousIssuesByYearVO.get(i).getIssueDate() + "/" + previousIssuesByYearVO.get(i).getThumbNail();
        System.out.println("url image_url " + image_url);
        Picasso.with(context).load(image_url).into(viewHolder.imgThumbnail);

        viewHolder.imgThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("issues id", "issues id ------" + previousIssuesByYearVO.get(i).getIssueId());
                CurrentPublishedIssueDetails.setIssueId(previousIssuesByYearVO.get(i).getIssueId());
                Intent in = new Intent(context, NavigationHomeActivity.class);
                context.startActivity(in);
            }
        });

    }

    @Override
    public int getItemCount() {

        return previousIssuesByYearVO.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgThumbnail;
        public TextView tvspecies;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.imageView1);
            tvspecies = (TextView) itemView.findViewById(R.id.textView1);

        }
    }

}
