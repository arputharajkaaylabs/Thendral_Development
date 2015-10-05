package com.kaaylabs.thendral.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaaylabs.thendral.R;
import com.kaaylabs.thendral.ThendralApplication;
import com.kaaylabs.thendral.bean.CategoryItem;
import com.mayuonline.tamilandroidunicodeutil.TamilUtil;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryRowHolder> {


    private List<CategoryItem> categoryItemList;

    private Context mContext;

    public CategoryAdapter(Context context, List<CategoryItem> feedItemList) {
        this.categoryItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CategoryRowHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, null);
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Toast.makeText(mContext,"position = "+i,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        CategoryRowHolder mh = new CategoryRowHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(CategoryRowHolder feedListRowHolder, int i) {
        CategoryItem categoryItem = categoryItemList.get(i);
        feedListRowHolder.title.setTypeface(ThendralApplication.tf_tamil_font);
        String categoryName = TamilUtil.convertToTamil(TamilUtil.BAMINI, categoryItem.getTitle());
        feedListRowHolder.title.setText(categoryName);
    }

    @Override
    public int getItemCount() {
        return (null != categoryItemList ? categoryItemList.size() : 0);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.title);
            imageView = (ImageView) itemView.findViewById(R.id.counter);
        }
    }
}
