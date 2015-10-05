package com.kaaylabs.thendral.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaaylabs.thendral.R;

import java.util.ArrayList;

/**
 * Created by ARaja on 13-05-2015.
 */
public class NavDrawerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItem> navDrawerItems;
    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItem> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);

        if(position == 0)
        {
            imgIcon.setImageResource(R.drawable.selector_home);
        }else if(position == 1)
        {
            imgIcon.setImageResource(R.drawable.ic_previous_issue_normal);
        }
       else if(position == 2)
        {
            imgIcon.setImageResource(R.drawable.selector_category);
        }
        else if(position == 3)
        {
            imgIcon.setImageResource(R.drawable.selector_my_topics);
        }
        else if(position == 4)
        {
            imgIcon.setImageResource(R.drawable.selector_audio);
        }
        else if(position == 5)
        {
            imgIcon.setImageResource(R.drawable.selector_aboutus);
        }
        else if(position == 6)
        {
            imgIcon.setImageResource(R.drawable.selector_settings);
        }
        else
        {
            imgIcon.setImageResource(R.drawable.signout_selector);
        }


        txtTitle.setText(navDrawerItems.get(position).getTitle());
        txtTitle.setTextSize(14);
      //  txtTitle.setTypeface(ThendralApplication.tf_segoeui_regular);

        return convertView;
    }
}
