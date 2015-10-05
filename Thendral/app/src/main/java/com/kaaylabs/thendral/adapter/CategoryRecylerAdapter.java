package com.kaaylabs.thendral.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.activity.CategoryDetailsPage;
import com.kaaylabs.thendral.bean.CurrentPublishedIssueDetails;
import com.kaaylabs.thendral.gson_pojo.CategoryList;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guest_user on 24/4/15.
 */
public class CategoryRecylerAdapter extends RecyclerView.Adapter<CategoryRecylerAdapter.ViewHolder> {

    // IF the view under inflation and population is header or Item
    private static final int TYPE_ITEM = 1;

    public static List<CategoryList> categoryList =  new ArrayList<CategoryList>(); // String Array to store the passed titles Value from MainActivity.java
    private int mIcons[];       // Int Array to store the passed icons resource value from MainActivity.java

    private String name;        //String Resource for header View Name
    private String email;       //String Resource for header view email
    Context context;


    // Creating a ViewHolder which extends the RecyclerView View Holder
    // ViewHolder are used to to store the inflated views in order to recycle them

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        int Holderid;

        TextView textView;
        //TextView issuesId_category,categoryId_category;
        ImageView imageView;
        RelativeLayout category_child_item;
        Context context;


        public ViewHolder(View itemView, int ViewType, Context c) {                 // Creating ViewHolder Constructor with View and viewType As a parameter
            super(itemView);

            context = c;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            // Here we set the appropriate view in accordance with the the view type as passed when the holder object is created

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.title); // Creating TextView object with the id of textView from item_row.xml
                imageView = (ImageView) itemView.findViewById(R.id.counter);// Creating ImageView object with the id of ImageView from item_row.xml
                category_child_item = (RelativeLayout) itemView.findViewById(R.id.category_child_item);
                /*issuesId_category = (TextView) itemView.findViewById(R.id.issuesId_category);
                categoryId_category = (TextView) itemView.findViewById(R.id.categoryId_category);*/

                Holderid = 1;                                               // setting holder id as 1 as the object being populated are of type item row
            }
        }


        @Override
        public void onClick(View v) {
            //Toast.makeText(context, "The Item Clicked is: " + getPosition(), Toast.LENGTH_SHORT).show();
           int pos = getPosition();
            Intent in = new Intent(context,CategoryDetailsPage.class);
            in.putExtra("category_events","http://int.thendralonline.com/ThendralWS/getIsssueCategoryListPage/issueId/"+ CurrentPublishedIssueDetails.getIssueId()+"/categoryId/"+categoryList.get(pos).getCategory()+"/limitStart/0/limitCount/10");
            context.startActivity(in);
        }
    }


    public CategoryRecylerAdapter(List<CategoryList> categoryList,Context passedContext) { // MyAdapter Constructor with titles and icons parameter
        // titles, icons, name, email, profile pic are passed from the main activity as we
        this.categoryList = categoryList;

        this.context = passedContext;
        //in adapter

    }


    //Below first we ovverride the method onCreateViewHolder which is called when the ViewHolder is
    //Created, In this method we inflate the item_row.xml layout if the viewType is Type_ITEM or else we inflate header.xml
    // if the viewType is TYPE_HEADER
    // and pass it to the view holder

    @Override
    public CategoryRecylerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false); //Inflating the layout

            ViewHolder vhItem = new ViewHolder(v, viewType, context); //Creating ViewHolder and passing the object of type view

            return vhItem; // Returning the created object

            //inflate your layout and pass it to view holder

        }
        return null;

    }

    //Next we override a method which is called when the item in a row is needed to be displayed, here the int position
    // Tells us item at which position is being constructed to be displayed and the holder id of the holder object tell us
    // which view type is being created 1 for item row
    @Override
    public void onBindViewHolder(CategoryRecylerAdapter.ViewHolder holder, final int position) {
        if (holder.Holderid == 1) {                              // as the list view is going to be called after the header view so we decrement the
            // position by 1 and pass it to the holder while setting the text and image
        //    holder.textView.setTypeface(ThendralApplication.tf_tamil_font);
            String categoryName = TamilUtil.convertToTamil(TamilUtil.BAMINI, categoryList.get(position).getCategoryName());
            holder.textView.setText(categoryList.get(position).getCategoryName()); // Setting the Text with the array of our Titles

           /* holder.categoryId_category.setText(categoryList.get(position).getCategory());
            holder.issuesId_category.setText(categoryList.get(position).getIssueId());*/
            /*holder.category_child_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("category list ", "------------category-----------");
                    Intent in = new Intent(context,CategoryDetailsPage.class);
                    in.putExtra("category_events","http://int.thendralonline.com/ThendralWS/getIsssueCategoryListPage/issueId/"+categoryList.get(position).getIssueId()+"/categoryId/"+categoryList.get(position).getCategory()+"/limitStart/0/limitCount/10");
                    context.startActivity(in);
                }
            });*/
            TextView txt = holder.textView;
            /*txt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("category list ", "------------category-----------");
                    Intent in = new Intent(context,CategoryDetailsPage.class);
                    in.putExtra("category_events","http://int.thendralonline.com/ThendralWS/getIsssueCategoryListPage/issueId/"+CurrentPublishedIssueDetails.getIssueId()+"/categoryId/"+categoryList.get(position).getCategory()+"/limitStart/0/limitCount/10");
                    context.startActivity(in);
                }
            });*/

        }
        holder.itemView.setSelected(true);

    }

    // This method returns the number of items present in the list
    @Override
    public int getItemCount() {
        return categoryList.size(); // the number of items in the list will be +1 the titles including the header view.
    }


    // Witht the following method we check what type of view is being passed
    @Override
    public int getItemViewType(int position) {

        return TYPE_ITEM;
    }


}
