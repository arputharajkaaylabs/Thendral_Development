package com.kaaylabs.thendral.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.ThendralApplication;
import com.kaaylabs.thendral.activity.DetailsActivity;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.bean.EventInformationBO;
import com.kaaylabs.thendral.util.DynamicImageView;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ARaja on 29-06-2015.
 */
public class EventsInformationAdapter extends RecyclerView.Adapter<EventsInformationAdapter.PersonViewHolder>{
    List<EventInformationBO> eventInformation;
    Activity context;
    Typeface tf_tamil_font;

    public EventsInformationAdapter(List<EventInformationBO> eventInformation, Activity context){
        this.eventInformation = eventInformation;
        this.context = context;
    }
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventName;
        TextView eventDescription;
        DynamicImageView personPhoto;
        RelativeLayout eventInformation_layout;

        PersonViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.eventsCardView);

            eventName = (TextView)itemView.findViewById(R.id.person_name);
            eventDescription = (TextView)itemView.findViewById(R.id.person_age);
            personPhoto = (DynamicImageView)itemView.findViewById(R.id.person_photo);
            eventName.setTypeface(ThendralApplication.tf_tamil_font);
            eventDescription.setTypeface(ThendralApplication.tf_tamil_font);
            eventInformation_layout = (RelativeLayout)itemView.findViewById(R.id.eventInformation_layout);
        }
    }
    @Override
    public int getItemCount() {
        return eventInformation.size();
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.information_item, viewGroup, false);

        PersonViewHolder pvh = new PersonViewHolder(v);

        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        // String eventName =  URLEncoder.encode(eventInformation.get(i).eventName, "UTF-8");
        // String eventDescription =URLEncoder.encode(eventInformation.get(i).eventDescription, "UTF-8");
        //         eventName = eventName.replace(" ", "%20");
        // eventDescription = eventDescription.replace(" ", "%20");
        final int pos = i;
        String eventName = TamilUtil.convertToTamil(TamilUtil.BAMINI, eventInformation.get(i).getHeadLine());

       //Setting the new string to TextView
        personViewHolder.eventName.setText(eventName);
        DynamicImageView personPhoto_view = personViewHolder.personPhoto;
        Picasso.with(context).load("http://intimages.thendral.com.s3.amazonaws.com/hp/"+eventInformation.get(i).getArchiveImageUrl()).into(personPhoto_view);

        personViewHolder.eventInformation_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context,DetailsActivity.class);
                in.putExtra("detailsPageUrl","http://int.thendralonline.com/ThendralWS/getGetMoreArticleNew/issueId/"+ CurrentPublishedIssueDetails.getIssueId()+"/categoryID/"+eventInformation.get(pos).getCategoryId()+"/articleID/"+eventInformation.get(pos).getArticleId());
                context.startActivity(in);
            }
        });

       // personViewHolder.eventName.setText(eventInformation.get(i).eventName);
        //personViewHolder.eventDescription.setText(eventDescription);

       // personViewHolder.personPhoto.setImageResource(eventInformation.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
